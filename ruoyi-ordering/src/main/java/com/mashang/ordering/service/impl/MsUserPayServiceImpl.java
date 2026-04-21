package com.mashang.ordering.service.impl;

import com.mashang.ordering.mapper.MsUserPayMapper;
import com.mashang.ordering.service.IMsUserPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsUserPayServiceImpl implements IMsUserPayService {

    @Autowired
    MsUserPayMapper msUserPayMapper;

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
    }
}
