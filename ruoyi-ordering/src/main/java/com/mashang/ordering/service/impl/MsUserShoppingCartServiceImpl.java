package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsShoppingCart;
import com.mashang.ordering.domain.vo.MsUserShoppingCartListVo;
import com.mashang.ordering.mapper.MsUserShoppingCartMapper;
import com.mashang.ordering.service.IMsUserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MsUserShoppingCartServiceImpl extends ServiceImpl<MsUserShoppingCartMapper, MsShoppingCart> implements IMsUserShoppingCartService {

    @Autowired
    private MsUserShoppingCartMapper msUserShoppingCartMapper;

    @Override
    public List<MsUserShoppingCartListVo> getList(Long userId, Long storeId) {
        return msUserShoppingCartMapper.getList(userId, storeId);
    }
}
