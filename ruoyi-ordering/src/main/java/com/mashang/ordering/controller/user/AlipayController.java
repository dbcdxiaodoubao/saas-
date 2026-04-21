package com.mashang.ordering.controller.user;

import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.mashang.ordering.config.AlipayConfig;
import com.mashang.ordering.domain.vo.MsUserOrderDtlVo;
import com.mashang.ordering.service.IMsUserOrderService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.mashang.ordering.config.AlipayConfig.*;

@RestController
@RequestMapping("/alipay")
@Api(tags = "支付宝支付接口")
public class AlipayController {

    @Resource
    private AlipayClient alipayClient;

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private IMsUserOrderService msUserOrderService;

    @Value("${alipay.notify-url}")
    private String notifyUrl;

    @Value("${alipay.return-url}")
    private String returnUrl;

    // 支付接口（绝对不报错）
    @GetMapping("/pay")
    @ApiOperation("支付")
    public R pay(@ApiParam(value = "订单id", required = true) Long orderId
                      ) throws Exception {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);

        MsUserOrderDtlVo dtl = msUserOrderService.getDtl(orderId);

        if (dtl == null) {
            R.fail("该订单不存在");
        }


        // 拼接支付参数
        String bizContent = "{\"out_trade_no\":\"" + orderId + "\","
                + "\"total_amount\":\"" + (dtl.getProductTotalPrice()-Double.valueOf(dtl.getPointsDeduction())) + "\","
                + "\"subject\":\"" + "OrderId:"+orderId + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";

        request.setBizContent(bizContent);
        String form = alipayClient.pageExecute(request,"utf-8").getBody();

        return R.ok(form);
    }

    @GetMapping("/recharge")
    @ApiOperation("余额充值")
    public String recharge(
            @ApiParam(value = "充值金额", required = true) BigDecimal amount,
            @ApiParam(value = "当前登录用户ID", required = true) Long userId
    ) throws Exception {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(notifyUrl);       // 复用同一个异步回调
        request.setReturnUrl(returnUrl);

        // 生成一个唯一的充值订单号（规则：RECHARGE_用户ID_时间戳）
        String outTradeNo = "RECHARGE_" + userId + "_" + System.currentTimeMillis();

        // 拼接充值支付参数
        String bizContent = "{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"total_amount\":\"" + amount + "\","
                + "\"subject\":\"recharge\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";

        request.setBizContent(bizContent);
        return alipayClient.pageExecute(request, "utf-8").getBody();
    }

    // 异步回调
    @PostMapping("/notify")
    @Anonymous
    public String notify(HttpServletRequest request) throws Exception {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();

        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        // 2. 执行支付宝官方验签（核心！）
        boolean signVerified = AlipaySignature.rsaCheckV1(
                params,                 // 回调参数
                alipayConfig.alipayPublicKey,        // 支付宝公钥
                alipayConfig.charset,                // 编码
                "RSA2"                // 签名类型 RSA2
        );

        // 3. 验签失败 → 直接返回
        if (!signVerified) {
            System.out.println("【支付宝回调】验签失败！");
            return "fail";
        }
        System.out.println("【支付宝回调】验签！");
        // 4. 验签成功 → 处理业务
        String tradeStatus = params.get("trade_status");
        String orderId = params.get("out_trade_no");
        String totalAmount = params.get("total_amount");

        System.out.println("【支付宝回调】验签成功！订单号：" + orderId+"价格为："+totalAmount);

        if ("TRADE_SUCCESS".equals(tradeStatus)) {
            String outTradeNo = params.get("out_trade_no");
            if (outTradeNo.startsWith("RECHARGE_")){

                String[] arr = outTradeNo.split("_");
                Long userId = Long.valueOf(arr[1]);
                BigDecimal money = new BigDecimal(params.get("total_amount"));

                msUserOrderService.userRecharge(userId, money);
                System.out.println("【余额充值成功】用户ID：" + userId + " 金额：" + money);
            }
            else {
                msUserOrderService.pay(Long.valueOf(orderId),totalAmount);
                System.out.println("订单支付成功！");
            }

        }

        return "success";
    }

    @GetMapping("/return")
    @Anonymous
    @ApiOperation("支付成功跳转页")
    public String returnPage() {
        return "支付成功！请关闭页面";
    }


    @GetMapping("/refund")
    @Anonymous
    public R refund(@ApiParam(value = "订单ID", required = true) Long orderId) {
        try {
            // 1. 构建退款请求
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

            MsUserOrderDtlVo dtl = msUserOrderService.getDtl(orderId);

            if (dtl == null) {
                return R.fail("该订单不存在");
            }

            // 退款参数（必须：订单号 + 退款金额 + 唯一退款流水号）
            String bizContent = "{\"out_trade_no\":\"" + orderId + "\","
                    + "\"refund_amount\":\"" + dtl.getActualPay() + "\","
                    + "\"out_request_no\":\"REFUND_" + System.currentTimeMillis() + "\"}";

            request.setBizContent(bizContent);

            // 2. 调用支付宝退款
            AlipayTradeRefundResponse response = alipayClient.execute(request);

            if (response.isSuccess()) {
                System.out.println("退款成功，订单号：" + orderId);

                msUserOrderService.updateOrderStatusToRefund(Long.valueOf(orderId));

                return R.ok("退款成功，订单号：" + orderId);
            } else {
                System.out.println("退款失败：" + response.getMsg());
                return R.fail("退款失败：" + response.getMsg());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("退款异常：" + e.getMessage());
        }
    }
}