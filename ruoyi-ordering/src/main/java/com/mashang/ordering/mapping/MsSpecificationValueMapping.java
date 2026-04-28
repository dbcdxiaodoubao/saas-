package com.mashang.ordering.mapping;

import com.mashang.ordering.domain.entity.MsSpecificationValue;
import com.mashang.ordering.domain.param.create.MsSpecificationValueCreate;
import com.mashang.ordering.domain.param.update.MsSpecificationValueUpdate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MsSpecificationValueMapping {
    MsSpecificationValueMapping INSTANCE = org.mapstruct.factory.Mappers.getMapper(MsSpecificationValueMapping.class);

    MsSpecificationValue fromCreate(MsSpecificationValueCreate msSpecificationValueCreate);

    List<MsSpecificationValue> fromCreates(List<MsSpecificationValueCreate> msSpecificationValueCreateList);

    MsSpecificationValue fromUpdate(MsSpecificationValueUpdate valueUpdate);
}
