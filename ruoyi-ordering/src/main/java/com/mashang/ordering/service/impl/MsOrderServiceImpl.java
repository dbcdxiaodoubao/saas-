package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.param.selete.MsTableOrderParam;
import com.mashang.ordering.domain.vo.MsOrderDTO;
import com.mashang.ordering.domain.vo.MsTableOrderDto;
import com.mashang.ordering.domain.vo.MsTableOrderListVo;
import com.mashang.ordering.mapper.MsOrderMapper;
import com.mashang.ordering.mapper.MsProductMapper;
import com.mashang.ordering.service.IMsOrderService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MsOrderServiceImpl extends ServiceImpl<MsOrderMapper, MsOrder> implements IMsOrderService{


    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired MsOrderMapper msOrderMapper;

    @Autowired
    private MsProductMapper msProductMapper;

    @Override
    public MsOrderDTO getMsOrderDate() {
        MsOrderDTO dto = new MsOrderDTO();

        /**
         * 会员总数
         */
        Long memberTotal = sysUserMapper.selectCount(
                Wrappers.lambdaQuery(SysUser.class)
                        .eq(SysUser::getDelFlag, 0)
        );
        dto.setMemberTotal(memberTotal);

        /**
         * 订单总数
          */
        Long orderTotal = msOrderMapper.selectCount(
                Wrappers.lambdaQuery(MsOrder.class)
                        .eq(MsOrder::getDelFlag, 0)
        );
        dto.setOrderTotal(orderTotal);

        /**
         * 商品总数
         */
        Long productTotal = msProductMapper.selectCount(
                Wrappers.lambdaQuery(MsProduct.class)
                        .eq(MsProduct::getDelFlag, 0)
        );
        dto.setProductTotal(productTotal);

        /**
         * 总金额
         */
        BigDecimal amountTotal = msOrderMapper.getTotalAmount();
        dto.setAmountTotal(amountTotal == null ? BigDecimal.ZERO : amountTotal);

        /**
         * 今日订单
         */
        LocalDateTime today = LocalDate.now().atStartOfDay();
        Long todayOrder = msOrderMapper.selectCount(
                Wrappers.lambdaQuery(MsOrder.class)
                        .ge(MsOrder::getCreateTime, today)
                        .eq(MsOrder::getDelFlag, 0)
        );
        dto.setTodayOrder(todayOrder);

        /**
         * 昨日订单
         */
        LocalDateTime yesterday = LocalDate.now().minusDays(1).atStartOfDay();
        Long yesterdayOrder = msOrderMapper.selectCount(
                Wrappers.lambdaQuery(MsOrder.class)
                        .between(MsOrder::getCreateTime, yesterday, today)
                        .eq(MsOrder::getDelFlag, 0)
        );
        dto.setYesterdayOrder(yesterdayOrder);

        /**
         * 昨日订单
         */
        LocalDateTime last7Day = LocalDate.now().minusDays(7).atStartOfDay();
        Long last7DaysOrder = msOrderMapper.selectCount(
                Wrappers.lambdaQuery(MsOrder.class)
                        .ge(MsOrder::getCreateTime, last7Day)
                        .eq(MsOrder::getDelFlag, 0)
        );
        dto.setLast7DaysOrder(last7DaysOrder);

        /**
         * 本月
         */
        LocalDateTime monthStart = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        Long thisMonthOrder = msOrderMapper.selectCount(
                Wrappers.lambdaQuery(MsOrder.class)
                        .ge(MsOrder::getCreateTime, monthStart)
                        .eq(MsOrder::getDelFlag, 0)
        );
        dto.setThisMonthOrder(thisMonthOrder);

        return dto;
    }

    @Override
    public ResultSet<Page<MsTableOrderListVo>> getMsTableOrderListVo(MsTableOrderParam msTableOrderParam, PageQuery pageQuery) {
        QueryWrapper<Object> qw = Wrappers.query();
        qw.eq("t3.table_id", msTableOrderParam.getTableId());
        //todo 问清楚订单状态枚举值
        String orderStatus = msTableOrderParam.getOrderStatus();
        if(orderStatus != null){
            if(orderStatus.length() != 1) {
                return ResultSet.fail("订单状态参数错误");
            }
        }
        String payType = msTableOrderParam.getPayType();
        if(payType != null){
            if(!"0".equals(payType) && !"1".equals(payType)) {
                return ResultSet.fail("支付方式参数错误");}
        }
        qw.eq("t3.table_id", msTableOrderParam.getTableId());
        qw.eq(msTableOrderParam.getOrderStatus()!=null,"t1.order_status", msTableOrderParam.getOrderStatus());
        qw.eq(msTableOrderParam.getPayType()!=null,"t1.pay_type", msTableOrderParam.getPayType());
        qw.like(msTableOrderParam.getOrderNumber()!=null,"t1.order_number", msTableOrderParam.getOrderNumber());
        qw.like(msTableOrderParam.getUserName()!=null,"t5.user_name", msTableOrderParam.getUserName());
        qw.like(msTableOrderParam.getUserTel()!=null,"t5.user_tel", msTableOrderParam.getUserTel());
        List<MsTableOrderListVo> list = msOrderMapper.getMsTableOrderListVo(qw);
        Page<MsTableOrderListVo> page = page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize(), list.size());
        page.setRecords(list);
        return ResultSet.success(page);
    }

    @Override
    public ResultSet<MsTableOrderDto> getMsTableOrderDto(Long orderId,Long tableId) {
        QueryWrapper<Object> qw = Wrappers.query();
        qw.eq("t1.order_id", orderId);
        qw.eq("t3.table_id", tableId);
        return ResultSet.success(msOrderMapper.getMsTableOrderDto(qw));
    }


}
