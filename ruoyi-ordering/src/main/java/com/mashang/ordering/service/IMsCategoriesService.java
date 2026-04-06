package com.mashang.ordering.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsCategories;
import com.mashang.ordering.domain.param.create.MsCategoriesCreate;
import com.mashang.ordering.domain.param.selete.MsCategoriesParam;
import com.mashang.ordering.domain.vo.MsCategoriesListVo;

public interface IMsCategoriesService extends IService<MsCategories> {
    ResultSet<Object> addCategoriesWithStore(MsCategoriesCreate msCategoriesCreate) throws Exception;
    Page<MsCategoriesListVo> getCategoriesList(MsCategoriesParam msCategoriesParam, PageQuery pageQuery);
}
