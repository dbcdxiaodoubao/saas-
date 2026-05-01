package com.mashang.ordering.service.impl;

import com.mashang.ordering.domain.vo.MsUserOrderDtlVo;
import com.mashang.ordering.mapper.MsUserOrderMapper;
import com.mashang.ordering.mapper.MsUserPayMapper;
import com.mashang.ordering.service.IMsUserPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MsUserPayServiceImpl implements IMsUserPayService {

    @Autowired
    MsUserPayMapper msUserPayMapper;

    @Autowired
    MsUserOrderMapper msUserOrderMapper;

    @Override
    public void pay(Long orderId, Long userId, Double totalAmount) {
        msUserPayMapper.payOrder(orderId,totalAmount);
        msUserPayMapper.payUser(userId,totalAmount);
    }

    @Override
    public Double getAccountLimit(Long userId) {
        return msUserPayMapper.getAccountLimit(userId);
    }

    @Override
    public void refund(Long orderId) {
        msUserPayMapper.refund(orderId);
        MsUserOrderDtlVo dtl = msUserOrderMapper.getDtl(orderId);
        msUserOrderMapper.userRecharge(dtl.getUserId(), new BigDecimal(dtl.getActualPay()));
    }

    @Override
    public void applyRefund(Long orderId) {
        msUserPayMapper.applyRefund(orderId);
    }
}
