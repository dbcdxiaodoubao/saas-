package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.entity.MsStoreProduct;
import com.mashang.ordering.domain.param.create.MsProductCreate;
import com.mashang.ordering.domain.param.selete.MsProductPageParam;
import com.mashang.ordering.domain.param.update.MsProductUpdate;
import com.mashang.ordering.domain.vo.MsProductDtlVo;
import com.mashang.ordering.domain.vo.MsProductPageVo;
import com.mashang.ordering.mapper.*;
import com.mashang.ordering.mapping.MsProductMapping;
import com.mashang.ordering.service.IMsProductService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MsProductServiceImpl extends ServiceImpl<MsProductMapper, MsProduct> implements IMsProductService {

    @Autowired
    private MsProductMapper msProductMapper;

    @Autowired
    private MsSpecificationMapper msSpecificationMapper;

    @Autowired
    private MsStoreProductMapper msStoreProductMapper;

    @Autowired
    private MsStoreMapper msStoreMapper;

    @Autowired
    private MsCategoriesMapper msCategoriesMapper;

    @Override
    public TableDataInfo<List<MsProductPageVo>> selectProductPage(MsProductPageParam msProductPageParam) {

        PageHelper.startPage(msProductPageParam.getPageNum(), msProductPageParam.getPageSize());
        //查询分页列表
        List<MsProductPageVo> list = msProductMapper.page(msProductPageParam);

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
    public ResultSet addProduct(MsProductCreate msProductCreate) {

        //根据门店id查询该门店所有商品，进行查重
        LambdaQueryWrapper<MsStoreProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MsStoreProduct::getStoreId, msProductCreate.getStoreId());

        List<MsStoreProduct> msStoreProducts = msStoreProductMapper.selectList(wrapper);

        //门店有商品，进查重逻辑
        if(!msStoreProducts.isEmpty()){

            //获取所有商品id
            List<Long> productIds = msStoreProducts.stream()
                    .map(MsStoreProduct::getProductId)
                    .collect(Collectors.toList());

            //查询是否存在重复商品
            LambdaQueryWrapper<MsProduct> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.in(MsProduct::getProductId, productIds)
                    .eq(MsProduct::getProductCategoriesId, msProductCreate.getProductCategoriesId())
                    .eq(MsProduct::getProductName, msProductCreate.getProductName())
                    .eq(MsProduct::getSpecificationId, msProductCreate.getSpecificationId())
                    .eq(MsProduct::getDelFlag, "0");  // 未删除的商品
            Long count = msProductMapper.selectCount(wrapper1);
            if(count > 0){
                return ResultSet.fail("商品已存在");
            }

        }
        //门店无商品或门店无重复商品，进添加商品逻辑

        MsSpecification msSpecification = MsProductMapping.INSTANCE.toMsSpecification(msProductCreate);
        MsProduct msProduct = MsProductMapping.INSTANCE.toMsProduct(msProductCreate);

        //判断规格是否已经存在，是否添加新规格
        //规格不存在添加新规格
        //TODO 规格名称查重？

        if (!msProductCreate.getIsSpecSame()) {

            //查询规格表sort
            Long count= msSpecificationMapper.selectCount(new LambdaQueryWrapper<>());
            msSpecification.setSort(count + 1);
            msSpecification.setSpecsAndAttrs(msProductCreate.getSpecsAndAttrs());

            int insert = msSpecificationMapper.insert(msSpecification);
            if (insert != 1) {
                return ResultSet.fail("添加新规格失败");
            }
            msProduct.setSpecificationId(msSpecification.getSpecificationId());
        }

        //规格已存在，直接添加商品,添加门店商品表
        //添加商品
        if(msProduct.getInventory()==0){
            msProduct.setStatus("2");
        }
        int insert = msProductMapper.insert(msProduct);
        if (insert != 1) {
            return ResultSet.fail("添加商品失败");
        }

        //添加门店商品
        MsStoreProduct msStoreProduct = new MsStoreProduct();
        msStoreProduct.setStoreId(msProductCreate.getStoreId());
        msStoreProduct.setProductId(msProduct.getProductId());
        int insert1 = msStoreProductMapper.insert(msStoreProduct);
        if (insert1 != 1) {
            return ResultSet.fail("添加门店商品失败");
        }
        return ResultSet.success(insert1);

    }

    @Override
    public MsProductDtlVo selectProductDtl(Long productId) {

        MsProduct msProduct = msProductMapper.selectById(productId);
        MsProductDtlVo msProductDtlVo = MsProductMapping.INSTANCE.toMsProductDtlVo(msProduct);

        //根据商品id从门店商品表中拿门店信息
        LambdaQueryWrapper<MsStoreProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MsStoreProduct::getProductId, productId);
        Long storeId = msStoreProductMapper.selectOne(wrapper).getStoreId();
        String storeName = msStoreMapper.selectById(storeId).getStoreName();
        msProductDtlVo.setStoreId(storeId);
        msProductDtlVo.setStoreName(storeName);

        //根据商品分类id从分类表中拿分类信息
        String categoriesName = msCategoriesMapper.selectById(msProduct.getProductCategoriesId()).getCategoriesName();
        msProductDtlVo.setProductCategoriesName(categoriesName);

        //根据规格id从规格表中拿规格信息
        MsSpecification msSpecification = msSpecificationMapper.selectById(msProduct.getSpecificationId());

        msProductDtlVo.setSpecsAndAttrs(msSpecification.getSpecsAndAttrs());
        msProductDtlVo.setSpecificationName(msSpecification.getSpecificationName());

        return msProductDtlVo;
    }

    @Override
    @Transactional
    public ResultSet updateProduct(MsProductUpdate msProductUpdate) {

        //根据门店id查询该门店所有商品，进行查重
        LambdaQueryWrapper<MsStoreProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MsStoreProduct::getStoreId, msProductUpdate.getStoreId());

        List<MsStoreProduct> msStoreProducts = msStoreProductMapper.selectList(wrapper);

        //门店有商品，进查重逻辑
        if(!msStoreProducts.isEmpty()){

            //获取所有商品id
            List<Long> productIds = msStoreProducts.stream()
                    .map(MsStoreProduct::getProductId)
                    .collect(Collectors.toList());

            //查询是否存在重复商品
            LambdaQueryWrapper<MsProduct> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.in(MsProduct::getProductId, productIds)
                    .eq(MsProduct::getProductCategoriesId, msProductUpdate.getProductCategoriesId())
                    .eq(MsProduct::getProductName, msProductUpdate.getProductName())
                    .eq(MsProduct::getSpecificationId, msProductUpdate.getSpecificationId())
                    .eq(MsProduct::getDelFlag, "0");  // 未删除的商品
            Long count = msProductMapper.selectCount(wrapper1);
            if(count > 1){
                return ResultSet.fail("商品已存在");
            }

        }
        //门店无商品或门店无重复商品，进修改商品逻辑

        MsSpecification msSpecification = MsProductMapping.INSTANCE.toMsSpecification(msProductUpdate);
        MsProduct msProduct = MsProductMapping.INSTANCE.toMsProduct(msProductUpdate);

        //判断规格是否已经存在，是否添加新规格
        //规格不存在添加新规格
        if (!msProductUpdate.getIsSpecSame()) {

            //查询规格表sort
            Long count= msSpecificationMapper.selectCount(new LambdaQueryWrapper<>());
            msSpecification.setSort(count + 1);
            msSpecification.setSpecsAndAttrs(msProductUpdate.getSpecsAndAttrs());

            int insert = msSpecificationMapper.insert(msSpecification);
            if (insert != 1) {
                return ResultSet.fail("添加新规格失败");
            }
            msProduct.setSpecificationId(msSpecification.getSpecificationId());
        }

        //规格已存在，直接修改商品,修改门店商品表
        //修改商品
        if(msProduct.getInventory()==0){
            msProduct.setStatus("2");
        }
        int update = msProductMapper.updateById(msProduct);
        if (update != 1) {
            return ResultSet.fail("修改商品失败");
        }

        //修改门店商品
        MsStoreProduct msStoreProduct = new MsStoreProduct();

        msStoreProduct.setStoreId(msProductUpdate.getStoreId());
        msStoreProduct.setProductId(msProduct.getProductId());
        LambdaUpdateWrapper<MsStoreProduct> wrapper1 = new LambdaUpdateWrapper<>();
        wrapper1.eq(MsStoreProduct::getProductId, msProduct.getProductId());
        msStoreProduct.setStoreProductId(msStoreProductMapper.selectOne(wrapper1).getStoreProductId());

        int update1 = msStoreProductMapper.updateById(msStoreProduct);
        if (update1 != 1) {
            return ResultSet.fail("添加门店商品失败");
        }
        return ResultSet.success(update1);

    }

    @Override
    @Transactional
    public ResultSet deleteProduct(Long productId) {

        //删除门店商品表
        LambdaQueryWrapper<MsStoreProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MsStoreProduct::getProductId, productId);

        int delete = msStoreProductMapper.deleteById(msStoreProductMapper.selectOne(wrapper).getStoreProductId());
        if (delete != 1) {
            return ResultSet.fail("删除门店商品表失败");
        }

        //删除商品表
        int delete1 = msProductMapper.deleteById(productId);
        if (delete1 != 1) {
            return ResultSet.fail("删除商品表失败");
        }
        return ResultSet.success(delete1);
    }
}