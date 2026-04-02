package com.mashang.ordering.mapping;

import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.vo.MsStoreListVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.mashang.ordering.domain.param.create.MsStoreCreate;

import java.util.List;

@Mapper
public interface MsStoreMapping {
    //从数据库实体转出为其他实体用to 用其他实体构造数据库实体用from
    MsStoreMapping INSTANCE = Mappers.getMapper(MsStoreMapping.class);

    List<MsStoreListVo> toListVos(List<MsStore> msStores);

    @Mapping(target = "businessStartTime", dateFormat = "HH:mm:ss")
    @Mapping(target = "businessEndTime", dateFormat = "HH:mm:ss")
    MsStore fromCreate(MsStoreCreate msStoreCreate);
}
