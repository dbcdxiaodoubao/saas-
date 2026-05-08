package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsCategories;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.entity.MsStoreCategories;
import com.mashang.ordering.domain.param.create.MsCategoriesCreate;
import com.mashang.ordering.domain.param.selete.MsCategoriesParam;
import com.mashang.ordering.domain.param.update.MsCategoriesUpdate;
import com.mashang.ordering.domain.vo.MsCategoriesDto;
import com.mashang.ordering.domain.vo.MsCategoriesListVo;
import com.mashang.ordering.mapper.MsCategoriesMapper;
import com.mashang.ordering.mapper.MsProductMapper;
import com.mashang.ordering.mapper.MsStoreCategoriesMapper;
import com.mashang.ordering.mapping.MsCategoriesMapping;
import com.mashang.ordering.mapping.MsStoreCategoriesMapping;
import com.mashang.ordering.service.IMsCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MsCategoriesServiceImpl extends ServiceImpl<MsCategoriesMapper, MsCategories> implements IMsCategoriesService {

    @Autowired
    private MsCategoriesMapper msCategoriesMapper;

    @Autowired
    private MsStoreCategoriesMapper msStoreCategoriesMapper;

    @Autowired
    private MsProductMapper msProductMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultSet<Object> addCategoriesWithStore(MsCategoriesCreate msCategoriesCreate) throws Exception {
        boolean right = true;
        String msg = "";
        LambdaQueryWrapper<MsCategories> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MsCategories::getCategoriesName, msCategoriesCreate.getCategoriesName());
        if (msCategoriesMapper.selectCount(lqw) > 0) {
            right = false;
            msg += "当前分类名称已存在";
        }
        if(!right){
            return ResultSet.fail(msg);
        }

        Long msStoreId = msCategoriesCreate.getMsStoreId();
        MsCategories msCategories = MsCategoriesMapping.INSTANCE.fromCreate(msCategoriesCreate);
        int insertRes1 = msCategoriesMapper.insert(msCategories);
        //Long categoriesId = msCategoriesMapper.getLastInsertId();
        Long categoriesId = msCategories.getCategoriesId();//ai说会自动回填 我先信一下吧
        MsStoreCategories msStoreCategories = new MsStoreCategories();
        msStoreCategories.setCategoriesId(categoriesId);
        msStoreCategories.setStoreId(msStoreId);
        int insertRes2 = msStoreCategoriesMapper.insert(msStoreCategories);
        if(insertRes1 == 0 || insertRes2 == 0){
            throw new Exception("添加失败");
        }
        return ResultSet.success(null,"添加成功");
    }

    @Override
    public Page<MsCategoriesListVo> getCategoriesList(MsCategoriesParam msCategoriesParam, PageQuery pageQuery) {
        if (msCategoriesParam.getMsCategoriesName()==null) {
            msCategoriesParam.setMsCategoriesName("");
        }
        if (msCategoriesParam.getMsStoreName()==null) {
            msCategoriesParam.setMsStoreName("");
        }
        List<MsCategoriesListVo> msCategoriesListVoList = msCategoriesMapper.getAllCategories(
                msCategoriesParam.getMsCategoriesName(), msCategoriesParam.getMsStoreName());
        Page<MsCategoriesListVo> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize(), msCategoriesListVoList.size());
        page.setRecords(msCategoriesListVoList);
        return page;
    }

    @Override
    public ResultSet<MsCategoriesDto> getCategoriesById(Long id) {
        return ResultSet.success(msCategoriesMapper.getCategoriesById(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultSet<Object> updateCategories(MsCategoriesUpdate msCategoriesUpdate) throws Exception {
        //查询原映射
        MsStoreCategories oriPo = msStoreCategoriesMapper.selectById(msCategoriesUpdate.getStoreCategoriesId());
        if(oriPo == null){
            return ResultSet.fail("不存在此分类和门店的关系");
        }
        //先改映射
        MsStoreCategories updatePo1 = new MsStoreCategories();
        updatePo1.setCategoriesId(oriPo.getCategoriesId());
        updatePo1.setStoreId(msCategoriesUpdate.getMsStoreId());
        int updateRes1 = msStoreCategoriesMapper.updateById(updatePo1);
        //再改分类
        MsCategories updatePo2 = MsCategoriesMapping.INSTANCE.fromUpdate(msCategoriesUpdate);
        updatePo2.setCategoriesId(oriPo.getCategoriesId());
        int updateRes2 = msCategoriesMapper.updateById(updatePo2);
        //只要映射和分类有一个修改成功
        if(updateRes1 == 0 && updateRes2 == 0){
            throw new Exception("修改失败");
        }
        return ResultSet.success(null,"修改成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultSet<Object> deleteCategoriesById(Long msCategoriesid) throws Exception {
        //todo 保留意见……
        LambdaQueryWrapper<MsProduct> selectLqw0 = new LambdaQueryWrapper<>();
        selectLqw0.eq(MsProduct::getProductCategoriesId, msCategoriesid);
        if(msProductMapper.selectCount(selectLqw0) > 0) {
            return ResultSet.fail("当前分类下有商品，不能删除");
        }
        int deleteRes2 = msCategoriesMapper.deleteById(msCategoriesid);
        if(deleteRes2 != 1){
            throw new Exception("删除失败");
        }
        return ResultSet.success(null,"删除成功");
    }
}
