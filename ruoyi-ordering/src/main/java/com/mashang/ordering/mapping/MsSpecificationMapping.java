package com.mashang.ordering.mapping;

import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.param.create.MsSpecificationCreate;
import com.mashang.ordering.domain.param.update.MsSpecificationUpdate;
import com.mashang.ordering.domain.vo.MsSpecificationDto;
import com.mashang.ordering.domain.vo.MsSpecificationVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MsSpecificationMapping {
    MsSpecificationMapping INSTANCE = org.mapstruct.factory.Mappers.getMapper(MsSpecificationMapping.class);

    List<MsSpecificationVo> toListVos(List<MsSpecification> msSpecifications);

    MsSpecificationDto toDto(MsSpecification msSpecification);

    MsSpecificationDto voToDto(MsSpecificationVo msSpecificationVo);

    MsSpecification fromUpdate(MsSpecificationUpdate msSpecificationUpdate);

    MsSpecification fromCreate(MsSpecificationCreate msSpecificationCreate);

}
