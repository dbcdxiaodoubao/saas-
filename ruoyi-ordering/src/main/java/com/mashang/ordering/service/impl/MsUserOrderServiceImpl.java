package com.mashang.ordering.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.param.create.MsUserOrderAdd;
import com.mashang.ordering.domain.param.create.MsUserOrderCreate;
import com.mashang.ordering.domain.param.create.MsUserOrderProductCreate;
import com.mashang.ordering.domain.vo.*;
import com.mashang.ordering.mapper.*;
import com.mashang.ordering.service.IMsUserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MsUserOrderServiceImpl extends ServiceImpl<MsUserOrderMapper, MsOrder> implements IMsUserOrderService {

    @Autowired
    private MsUserOrderMapper msUserOrderMapper;

    @Override
    public List<MsUserOrderListVo> getList(Long userId) {
        return msUserOrderMapper.getList(userId);
    }

    @Override
    public MsUserOrderDtlVo getDtl(long orderId) {
        MsUserOrderDtlVo dtl = msUserOrderMapper.getDtl(orderId);

        dtl.setTableNumber(msUserOrderMapper.getLabelNumber(orderId));
        return dtl;
    }


    public void insertOrder(MsUserOrderCreate create) {

        String orderNumber = String.valueOf(System.currentTimeMillis());

        String pickupNumber = generate4DigitDailyOrderNo(create.getStoreId());

        create.setOrderNumber(orderNumber);
        create.setPickupNumber(pickupNumber);
        List<MsUserOrderProductCreate> productList = create.getMsUserOderProductCreateList();
        double total = 0;
        for (MsUserOrderProductCreate product : productList) {
            total += product.getProductPrice() * product.getProductQuantity();
        }
        create.setProductTotalPrice(total);

        msUserOrderMapper.insertOrder(create);

        Long orderId = msUserOrderMapper.getOrderIdByOrderNumberLong(orderNumber);

        msUserOrderMapper.batchInsertOrderDetail(orderId, productList);
    }

    @Override
    public void addProduct(MsUserOrderAdd msUserOrderAdd) {
        Long addCount = msUserOrderMapper.selectAddCount(msUserOrderAdd.getOrderId())+1;

        List<MsUserOrderProductCreate> productList = msUserOrderAdd.getMsUserOderProductCreateList();

        for (MsUserOrderProductCreate product : productList) {
            product.setCumulativeAddCount(addCount);
        }

        msUserOrderMapper.batchInsertOrderDetail(msUserOrderAdd.getOrderId(), productList);
    }

    @Override
    public void pay(Long orderId,String totalAmount) {
        msUserOrderMapper.pay(orderId,totalAmount);
    }

    @Override
    public void updateOrderStatusToRefund(Long orderId) {
        msUserOrderMapper.updateOrderStatusToRefund(orderId);
    }

    @Override
    public List<MsUserTableListVo> getLabelId(Long storeId) {
        return msUserOrderMapper.getLabelId(storeId);
    }

    @Override
    public void userRecharge(Long userId, BigDecimal money) {
        msUserOrderMapper.userRecharge(userId,money);
    }


    private String generate4DigitDailyOrderNo(Long storeId) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LambdaQueryWrapper<MsOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.apply("DATE(create_time) = {0}", today)
                .eq(MsOrder::getStoreId, storeId)  // 关键：按当前商店筛选
                .orderByDesc(MsOrder::getOrderNumber)
                .last("LIMIT 1");

        MsOrder last = msUserOrderMapper.selectOne(wrapper);
        int nextSeq = 1;

        if (last != null && last.getOrderNumber() != null) {
            try {
                int maxSeq = Integer.parseInt(last.getOrderNumber());
                nextSeq = maxSeq + 1;
            } catch (Exception ignored) {}
        }

        if (nextSeq > 9999) nextSeq = 1;
        return String.format("%04d", nextSeq);
    }

}
