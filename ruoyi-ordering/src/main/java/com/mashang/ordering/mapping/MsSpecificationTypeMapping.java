package com.mashang.ordering.mapping;

import com.mashang.ordering.domain.entity.MsSpecificationType;
import com.mashang.ordering.domain.param.create.MsSpecificationTypeCreate;
import com.mashang.ordering.domain.param.update.MsSpecificationTypeUpdate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MsSpecificationTypeMapping {
    MsSpecificationTypeMapping INSTANCE = org.mapstruct.factory.Mappers.getMapper(MsSpecificationTypeMapping.class);

    MsSpecificationType fromCreate(MsSpecificationTypeCreate msSpecificationTypeCreate);

    MsSpecificationType fromUpdate(MsSpecificationTypeUpdate msSpecificationTypeUpdate);

    List<MsSpecificationType> fromCreates(List<MsSpecificationTypeCreate> msSpecificationTypeCreateList);
}
