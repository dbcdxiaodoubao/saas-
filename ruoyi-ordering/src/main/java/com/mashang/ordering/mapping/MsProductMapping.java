package com.mashang.ordering.mapping;

import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.param.create.MsProductCreate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MsProductMapping {

    MsProductMapping INSTANCE = Mappers.getMapper(MsProductMapping.class);

    MsProduct toMsProduct(MsProductCreate msProductCreate);

    MsSpecification toMsSpecification(MsProductCreate msProductCreate);

}
