package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.param.create.MsProductCreate;
import com.mashang.ordering.domain.param.selete.MsProductPageQuery;
import com.mashang.ordering.domain.vo.MsProductPageVo;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

public interface IMsProductService extends IService<MsProduct> {

    /**
     * 分页查询商品列表
     */
    TableDataInfo<List<MsProductPageVo>> selectProductPage(MsProductPageQuery msProductPageQuery);

    /**
     * 添加商品
     */
    Integer addProduct(MsProductCreate msProductCreate);

}
