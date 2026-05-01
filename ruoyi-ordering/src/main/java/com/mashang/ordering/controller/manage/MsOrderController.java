package com.mashang.ordering.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.entity.MsOrderDetail;
import com.mashang.ordering.domain.param.selete.MsUserOrderPageParam;
import com.mashang.ordering.domain.param.update.MsUserOrderUpdate;
import com.mashang.ordering.domain.vo.MsOrderDTO;
import com.mashang.ordering.domain.vo.MsUserOrderListPageVo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/home")
@Api(tags = "管理端-主页")
public class MsOrderController {

    @Autowired
    private IMsOrderService msOrderService;

    @Autowired
    private SysNoticeMapper sysNoticeMapper;

    @Autowired
    private IMsUserPayService userPayService;

    @Autowired
    private IMsUserOrderService userOrderService;

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
    public TableDataInfo<List<MsUserOrderListPageVo>> selectOrderPage(MsUserOrderPageParam msUserOrderPageParam){
        return msOrderService.selectOrderPage(msUserOrderPageParam);
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
                        .eq(MsOrder::getOrderStatus,"5");
        int update = msOrderMapper.update(null, msOrderLambdaUpdateWrapper);
        if(update <= 0){
            return R.fail("修改订单状态失败");
        }

        return R.ok(null,"删除订单成功");
    }

    @GetMapping("/userRefund/{orderId}")
    @ApiOperation("确认退款")
    public R refund(@PathVariable @Validated Long orderId){

        if (userOrderService.getDtl(orderId)==null){
            return R.fail("该订单号不存在");
        }

        userPayService.refund(orderId);

        return R.ok();
    }
}
