package com.mashang.ordering.mapping;

import com.mashang.ordering.domain.entity.MsStore;
import com.mashang.ordering.domain.vo.MsStoreListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

//todo 这个互转的接口目前无法构造 晚点再说……
@Mapper
public interface MsStoreMapping {
    //从数据库实体转出为其他实体用to 用其他实体构造数据库实体用from
    MsStoreMapping INSTANCE = Mappers.getMapper(MsStoreMapping.class);

    List<MsStoreListVo> toListVos(List<MsStore> msStores);
}
