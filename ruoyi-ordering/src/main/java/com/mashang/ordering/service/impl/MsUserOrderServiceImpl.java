package com.mashang.ordering.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.vo.MsUserOrderDtlVo;
import com.mashang.ordering.domain.vo.MsUserOrderListVo;
import com.mashang.ordering.mapper.MsUserOrderMapper;
import com.mashang.ordering.service.IMsUserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
}
