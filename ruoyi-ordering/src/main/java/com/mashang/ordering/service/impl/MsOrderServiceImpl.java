package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.vo.MsOrderDTO;
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
}
