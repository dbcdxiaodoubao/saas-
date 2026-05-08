package com.mashang.ordering.controller.manage;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.entity.MsOrderDetail;
import com.mashang.ordering.domain.param.selete.MsOrderPageParam;
import com.mashang.ordering.domain.param.update.MsUserOrderUpdate;
import com.mashang.ordering.domain.vo.MsOrderDTO;
import com.mashang.ordering.domain.vo.MsUserOrderDtlVo;
import com.mashang.ordering.domain.vo.MsOrderListPageVo;
import com.mashang.ordering.mapper.MsOrderDetailMapper;
import com.mashang.ordering.mapper.MsOrderMapper;
import com.mashang.ordering.service.IMsOrderService;
import com.mashang.ordering.service.IMsUserOrderService;
import com.mashang.ordering.service.IMsUserPayService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.mapper.SysNoticeMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/home")
@Api(tags = "管理端-主页，租户端-订单中心")
public class MsOrderController {

    @Autowired
    private IMsOrderService msOrderService;

    @Autowired
    private SysNoticeMapper sysNoticeMapper;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private IMsUserOrderService msUserOrderService;

    @Autowired
    private IMsUserPayService msUserPayService;

    @Autowired
    private MsOrderDetailMapper msOrderDetailMapper;

    @Autowired
    private MsOrderMapper msOrderMapper;

    @ApiOperation("获取首页统计数据")
    @GetMapping("/getDate")
    public R<MsOrderDTO> getDate(){
        MsOrderDTO msOrderDate = msOrderService.getMsOrderDate();
        return R.ok(msOrderDate);
    }


    @ApiOperation("获取首页最新公告列表")
    @GetMapping("/homeList")
    public R<List<SysNotice>> homeList() {
        LambdaQueryWrapper<SysNotice> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(
                        SysNotice::getNoticeId,
                        SysNotice::getNoticeTitle,
                        SysNotice::getNoticeContent,
                        SysNotice::getNoticeType,
                        SysNotice::getStatus,
                        SysNotice::getCreateTime,
                        SysNotice::getRemark
                )
                .eq(SysNotice::getStatus, "0")  // 正常公告
                .orderByDesc(SysNotice::getCreateTime)
                .last("LIMIT 5");

        List<SysNotice> list = sysNoticeMapper.selectList(wrapper);
        return R.ok(list);
    }

    @ApiOperation("分页模糊查询订单列表")
    @GetMapping("/getOrderList")
    public TableDataInfo<List<MsOrderListPageVo>> selectOrderPage(MsOrderPageParam msOrderPageParam){
        return msOrderService.selectOrderPage(msOrderPageParam);
    }

    @ApiOperation("查询订单详情")
    @PutMapping("/getOrderDtl/{orderId}")
    public R<MsUserOrderDtlVo> getOrderDtl(@Validated @PathVariable Long orderId){
        return R.ok(msUserOrderService.getDtl(orderId));
    }

    @ApiOperation("修改订单详情")
    @PutMapping("/updateOrderDtl")
    public R updateOrderDtl(@RequestBody @Validated MsUserOrderUpdate msUserOrderUpdate){

        ResultSet resultSet = msOrderService.updateOrderDtl(msUserOrderUpdate);

        if (resultSet.isSuccess()) {
            return R.ok(resultSet.getData(),"修改商品成功");
        }
        return R.fail(resultSet.getMessage());
    }

    @ApiOperation("删除订单")
    @DeleteMapping("delete/{orderId}")
    public R updateOrderDtl(@PathVariable Long orderId){

        //查询订单详情表，进行删除
        LambdaQueryWrapper<MsOrderDetail> msOrderDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        msOrderDetailLambdaQueryWrapper.eq(MsOrderDetail::getOrderId, orderId);
        msOrderDetailMapper.delete(msOrderDetailLambdaQueryWrapper);

        //删除订单，即修改订单状态
        LambdaUpdateWrapper<MsOrder> msOrderLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        msOrderLambdaUpdateWrapper.eq(MsOrder::getOrderId, orderId)
                        .set(MsOrder::getOrderStatus,"4");
        int update = msOrderMapper.update(null, msOrderLambdaUpdateWrapper);
        if(update <= 0){
            return R.fail("修改订单状态失败");
        }

        return R.ok(null,"删除订单成功");
    }

    @GetMapping("/userRefund/{orderId}")
    @ApiOperation("确认退款")
    public R refund(@PathVariable @ApiParam(value = "订单ID", required = true) Long orderId) {
        try {
            if (orderId == null) {
                return R.fail("订单ID不能为空");
            }

            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

            MsUserOrderDtlVo dtl = msUserOrderService.getDtl(orderId);

            System.out.println(dtl);

            if (dtl == null) {
                return R.fail("该订单不存在");
            }

            if (!dtl.getOrderStatus().equals("5")){
                return R.fail("该订单不是申请退款的订单");
            }

            if(dtl.getPayType().equals("0")){
                msUserPayService.refund(orderId);
                return R.ok();
            }


            String bizContent = "{\"out_trade_no\":\"" + orderId + "\","
                    + "\"refund_amount\":\"" + dtl.getActualPay() + "\","
                    + "\"out_request_no\":\"REFUND_" + System.currentTimeMillis() + "\"}";

            request.setBizContent(bizContent);

            //调用支付宝退款
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
