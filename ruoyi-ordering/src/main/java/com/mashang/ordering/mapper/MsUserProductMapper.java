package com.mashang.ordering.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.vo.MsUserProductDtlVo;
import com.mashang.ordering.domain.vo.MsUserProductListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MsUserProductMapper extends BaseMapper<MsProduct> {


    /**
     * 根据分类，关键词查询商品列表
     * @param productCategoriesId
     * @param keyWord
     * @return
     */
    List<MsUserProductListVo> getList( @Param("productCategoriesId") Long productCategoriesId,
                                       @Param("keyWord") String keyWord);

    /**
     * 根据商品id查询详情
     * @param id
     * @return
     */
    MsUserProductDtlVo getDtl(Long id);
}
