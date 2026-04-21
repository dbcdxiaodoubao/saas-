package com.mashang.ordering.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MsUserPayMapper {

    /**
     * 支付时修改订单信息
     * @param orderId
     */
    void payOrder(@Param("orderId")Long orderId,
                  @Param("totalAmount")Double totalAmount);

    /**
     * 支付时修改用户信息
     * @param userId
     */
    void payUser(@Param("userId")Long userId,
                 @Param("totalAmount")Double totalAmount);

    /**
     *
     * @param userId
     * @return
     */
    Double getAccountLimit(@Param("userId")Long userId);


    /**
     * 申请退款
     * @param orderId
     */
    void refund(Long orderId);

}
