package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.common.PageQuery;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.param.create.MsSpecificationCreate;
import com.mashang.ordering.domain.param.update.MsSpecificationUpdate;
import com.mashang.ordering.domain.vo.MsSpecificationVo;

public interface IMsSpecificationService extends IService<MsSpecification> {

    ResultSet<Object> addSpecification(MsSpecificationCreate msSpecificationCreate) throws Exception;

    ResultSet<MsSpecificationVo> getSpecificationById(Long id);

    Page<MsSpecificationVo> getSpecificationList(String name, PageQuery pageQuery);

    ResultSet<Object> updateSpecification(MsSpecificationUpdate msSpecificationUpdate) throws Exception;

    ResultSet<Object> deleteSpecification(Long id) throws Exception;
}
