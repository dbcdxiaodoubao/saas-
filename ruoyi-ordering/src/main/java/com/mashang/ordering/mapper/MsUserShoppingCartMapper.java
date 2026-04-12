package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsShoppingCart;
import com.mashang.ordering.domain.vo.MsUserShoppingCartListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MsUserShoppingCartMapper extends BaseMapper<MsShoppingCart> {

    /**
     * 根据用户id与商店id查询购物车
     * @param userId
     * @param storeId
     * @return
     */
    List<MsUserShoppingCartListVo> getList(@Param("userId")Long userId,@Param("storeId") Long storeId);

    /**
     * 清空购物车
     * @param userId
     * @return
     */
    void deletAll(Long userId);
}
