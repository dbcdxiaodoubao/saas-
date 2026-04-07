package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.param.create.MsProductCreate;
import com.mashang.ordering.domain.param.selete.MsProductPageQuery;
import com.mashang.ordering.domain.param.update.MsProductUpdate;
import com.mashang.ordering.domain.vo.MsProductDtlVo;
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
    ResultSet addProduct(MsProductCreate msProductCreate);

    /**
     * 查询商品详情
     */
    MsProductDtlVo selectProductDtl(Long productId);

    /**
     *修改商品
     */
    ResultSet updateProduct(MsProductUpdate msProductUpdate);

    /**
     *删除商品
     */
    ResultSet deleteProduct(Long productId);
}
