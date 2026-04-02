package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.vo.MsUserProductDtlVo;
import com.mashang.ordering.domain.vo.MsUserProductListVo;

import java.util.List;


public interface IMsUserProductService extends IService<MsProduct> {

    /**
     * 根据分类，关键词查询商品列表
     * @param productCategoriesId
     * @param keyWord
     * @return
     */
    List<MsUserProductListVo> getList(Long productCategoriesId, String keyWord);

    /**
     * 根据商品id查询详情
     * @param id
     * @return
     */
    MsUserProductDtlVo getDtl(Long id);
}
