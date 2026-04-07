package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsCategories;
import com.mashang.ordering.domain.entity.MsStoreCategories;
import com.mashang.ordering.domain.param.create.MsCategoriesCreate;
import com.mashang.ordering.domain.param.selete.MsCategoriesParam;
import com.mashang.ordering.domain.vo.MsCategoriesListVo;
import com.mashang.ordering.mapper.MsCategoriesMapper;
import com.mashang.ordering.mapper.MsStoreCategoriesMapper;
import com.mashang.ordering.mapping.MsCategoriesMapping;
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
        List<MsCategoriesListVo> msCategoriesListVoList = msCategoriesMapper.getAllCategories(
                msCategoriesParam.getMsCategoriesName(), msCategoriesParam.getMsStoreName());
        Page<MsCategoriesListVo> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize(), msCategoriesListVoList.size());
        page.setRecords(msCategoriesListVoList);
        return page;
    }
}
