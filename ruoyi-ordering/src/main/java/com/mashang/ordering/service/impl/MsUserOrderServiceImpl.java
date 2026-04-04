package com.mashang.ordering.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.entity.MsOrderDetail;
import com.mashang.ordering.domain.param.create.MsUserOrderCreate;
import com.mashang.ordering.domain.param.create.MsUserOrderProductCreate;
import com.mashang.ordering.domain.vo.MsUserOrderDtlVo;
import com.mashang.ordering.domain.vo.MsUserOrderListVo;
import com.mashang.ordering.domain.vo.MsUserOrderProduct;
import com.mashang.ordering.mapper.MsUserOrderMapper;
import com.mashang.ordering.service.IMsUserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
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
        return msUserOrderMapper.getDtl(orderId);
    }


    public void insertOrder(MsUserOrderCreate create) {

        long orderId = System.currentTimeMillis();

        String orderNumber = generate4DigitDailyOrderNo();

        create.setOrderId(orderId);
        create.setOrderNumber(orderNumber);
        List<MsUserOrderProductCreate> productList = create.getMsUserOderProductCreateList();
        double total = 0;
        for (MsUserOrderProductCreate product : productList) {
            total += product.getProductPrice() * product.getProductQuantity();
        }
        create.setProductTotalPrice(total);

        msUserOrderMapper.insertOrder(create);

        msUserOrderMapper.batchInsertOrderDetail(orderId, productList);

    }


    private String generate4DigitDailyOrderNo() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LambdaQueryWrapper<MsOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.apply("DATE(create_time) = {0}", today)
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
