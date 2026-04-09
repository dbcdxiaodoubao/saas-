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
import java.util.HashMap;
import java.util.Map;

import static com.mashang.ordering.config.AlipayConfig.*;

@RestController
@RequestMapping("/alipay")
@Api(tags = "用户端-支付接口")
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
    public String pay(@ApiParam(value = "商户订单号（唯一）", required = true, example = "ORDER20260407001")
                          Long orderId) throws Exception {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);

        MsUserOrderDtlVo dtl = msUserOrderService.getDtl(orderId);

        // 拼接支付参数
        String bizContent = "{\"out_trade_no\":\"" + orderId + "\","
                + "\"total_amount\":\"" + dtl.getProductTotalPrice() + "\","
                + "\"subject\":\"" + "OrderId:"+orderId + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";

        request.setBizContent(bizContent);
        return alipayClient.pageExecute(request,"utf-8").getBody();
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

        // 4. 验签成功 → 处理业务
        String tradeStatus = params.get("trade_status");
        String orderId = params.get("out_trade_no");
        String totalAmount = params.get("total_amount");

        System.out.println("【支付宝回调】验签成功！订单号：" + orderId+"价格为："+totalAmount);

        if ("TRADE_SUCCESS".equals(tradeStatus)) {

            msUserOrderService.pay(Long.valueOf(orderId),totalAmount);

            System.out.println("订单支付成功！");
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
    @ApiOperation("支付宝订单退款")
    public R refund(@ApiParam(value = "订单ID", required = true) Long orderId) {
        try {
            // 1. 构建退款请求
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

            MsUserOrderDtlVo dtl = msUserOrderService.getDtl(orderId);

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