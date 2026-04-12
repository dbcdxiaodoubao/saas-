package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.entity.MsShoppingCart;
import com.mashang.ordering.domain.vo.MsUserShoppingCartListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMsUserShoppingCartService extends IService<MsShoppingCart> {

    /**
     * 根据用户id与商店id查询购物车
     * @param userId
     * @param storeId
     * @return
     */
    List<MsUserShoppingCartListVo> getList(@Param("userId")Long userId, @Param("storeId") Long storeId);

    /**
     * 清空购物车
     * @param userId
     * @return
     */
    void deletAll(Long userId);
}
