package com.mashang.ordering.controller.user;

import com.mashang.ordering.domain.vo.MsUserOrderDtlVo;
import com.mashang.ordering.service.IMsUserOrderService;
import com.mashang.ordering.service.IMsUserPayService;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-pay")
@Api(tags = "用户端-支付及退款")
public class MsUserPayController {

    @Autowired
    IMsUserPayService userPayService;

    @Autowired
    IMsUserOrderService userOrderService;

    @GetMapping("/{orderId}")
    @ApiOperation("余额支付")
    public R pay(@PathVariable @Validated Long orderId){
        MsUserOrderDtlVo dtl = userOrderService.getDtl(orderId);

        if(userPayService.getAccountLimit(dtl.getUserId())
                <dtl.getProductTotalPrice()-Double.valueOf(dtl.getPointsDeduction())){
            R.fail("余额不足请及时充值");
        }

        userPayService.pay(orderId,dtl.getUserId(),
                dtl.getProductTotalPrice()-Double.valueOf(dtl.getPointsDeduction()));

        return R.ok();
    }

    @GetMapping("/applyRefund/{orderId}")
    @ApiOperation("申请退款")
    public R applyRefund(@PathVariable @Validated Long orderId){
        MsUserOrderDtlVo dtl = userOrderService.getDtl(orderId);
        if(!(dtl.getOrderStatus().equals("2")||dtl.getOrderStatus().equals("1"))){
            return R.fail("该订单所处状态不能退款");
        }

        userPayService.applyRefund(orderId);

        return R.ok();
    }
}
