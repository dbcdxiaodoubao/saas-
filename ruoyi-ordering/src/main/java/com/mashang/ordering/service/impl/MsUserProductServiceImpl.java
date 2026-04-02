package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.vo.MsUserProductDtlVo;
import com.mashang.ordering.domain.vo.MsUserProductListVo;
import com.mashang.ordering.mapper.MsUserProductMapper;
import com.mashang.ordering.service.IMsUserProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class MsUserProductServiceImpl extends ServiceImpl<MsUserProductMapper, MsProduct> implements IMsUserProductService {

    @Autowired
    private MsUserProductMapper msUserProductMapper;


    @Override
    public List<MsUserProductListVo> getList(Long productCategoriesId, String keyWord) {
        return msUserProductMapper.getList(productCategoriesId, keyWord);
    }

    @Override
    public MsUserProductDtlVo getDtl(Long id) {
        return msUserProductMapper.getDtl(id);
    }
}
