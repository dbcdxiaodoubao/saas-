package com.mashang.ordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.param.create.MsSpecificationCreate;

public interface IMsSpecificationService extends IService<MsSpecification> {

    ResultSet<Object> addSpecification(MsSpecificationCreate msSpecificationCreate);
}
