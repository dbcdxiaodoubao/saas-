package com.mashang.ordering.service;

import org.apache.ibatis.annotations.Param;

public interface IMsUserPayService {

    void pay(Long orderId,Long userId,Double totalAmount);

    /**
     *
     * @param userId
     * @return
     */
    Double getAccountLimit(@Param("userId")Long userId);

    /**
     * 余额退款
     * @param orderId
     */
    void refund(Long orderId);

    /**
     * 申请退款
     * @param orderId
     */
    void applyRefund(Long orderId);
}
