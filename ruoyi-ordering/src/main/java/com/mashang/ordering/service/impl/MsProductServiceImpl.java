package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.param.create.MsProductCreate;
import com.mashang.ordering.domain.param.selete.MsProductPageQuery;
import com.mashang.ordering.domain.vo.MsProductPageVo;
import com.mashang.ordering.mapper.MsProductMapper;
import com.mashang.ordering.mapper.MsSpecificationMapper;
import com.mashang.ordering.mapping.MsProductMapping;
import com.mashang.ordering.service.IMsProductService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MsProductServiceImpl extends ServiceImpl<MsProductMapper, MsProduct> implements IMsProductService {

    @Autowired
    private MsProductMapper msProductMapper;

    @Autowired
    private MsSpecificationMapper msSpecificationMapper;

    @Override
    public TableDataInfo<List<MsProductPageVo>> selectProductPage(MsProductPageQuery msProductPageQuery) {

        PageHelper.startPage(msProductPageQuery.getPageNum(), msProductPageQuery.getPageSize());
        //查询分页列表
        List<MsProductPageVo> list = msProductMapper.page(msProductPageQuery);

        TableDataInfo<List<MsProductPageVo>> dataInfo = new TableDataInfo<>();
        dataInfo.setTotal(list.size());
        dataInfo.setRows(list);

        if(!list.isEmpty()){
            dataInfo.setCode(200);
            dataInfo.setMsg("查询成功");
            return dataInfo;
        }
        dataInfo.setCode(500);
        dataInfo.setMsg("查询失败");
        return dataInfo;
    }

    @Override
    @Transactional
    public Integer addProduct(MsProductCreate msProductCreate) {

        MsProduct msProduct = MsProductMapping.INSTANCE.toMsProduct(msProductCreate);

        //判断规格是否已经存在，是否添加新规格
        if (msProductCreate.getIsSpecSame()) {
            //规格已存在，添加商品
            return msProductMapper.insert(msProduct);
        }

        //规格不存在，先添加新规格
        MsSpecification msSpecification = MsProductMapping.INSTANCE.toMsSpecification(msProductCreate);
        int insert = msSpecificationMapper.insert(msSpecification);

        //添加商品
        msProduct.setSpecificationId(msSpecification.getSpecificationId());
        return msProductMapper.insert(msProduct);

    }

}