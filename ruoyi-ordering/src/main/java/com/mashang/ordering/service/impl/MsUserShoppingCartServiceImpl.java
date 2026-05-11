package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsShoppingCart;
import com.mashang.ordering.domain.param.create.MsUserOrderProductCreate;
import com.mashang.ordering.domain.vo.MsUserShoppingCartListVo;
import com.mashang.ordering.mapper.MsUserShoppingCartMapper;
import com.mashang.ordering.service.IMsUserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public void deletAll(Long userId) {
        msUserShoppingCartMapper.deletAll(userId);
        return;
    }

    public List<MsUserOrderProductCreate> getProductList(List<Long> ids) {

        List<MsUserOrderProductCreate> list = new ArrayList<MsUserOrderProductCreate>();

        for (Long id : ids) {
            MsShoppingCart msShoppingCart = msUserShoppingCartMapper.selectById(id);
            MsUserOrderProductCreate tmp = new MsUserOrderProductCreate();
            tmp.setProductId(msShoppingCart.getProductId());
            tmp.setProductName(msShoppingCart.getProductName());
            tmp.setProductPrice(msShoppingCart.getProductPrice());
            tmp.setProductImage(msShoppingCart.getProductImage());
            tmp.setProductQuantity((long) msShoppingCart.getProductQuantity());
            tmp.setSpecification(msShoppingCart.getSpecification());

            list.add(tmp);
        }

        return list;
    }
}
