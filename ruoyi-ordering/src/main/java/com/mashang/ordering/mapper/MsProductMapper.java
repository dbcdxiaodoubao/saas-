package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.param.selete.MsProductPageQuery;
import com.mashang.ordering.domain.vo.MsProductPageVo;

import java.util.List;

public interface MsProductMapper extends BaseMapper<MsProduct> {

    /**
     * 分页查询商品列表
     */
    List<MsProductPageVo> page(MsProductPageQuery query);



}
