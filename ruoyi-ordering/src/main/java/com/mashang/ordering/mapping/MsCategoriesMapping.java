package com.mashang.ordering.mapping;

import com.mashang.ordering.domain.entity.MsCategories;
import com.mashang.ordering.domain.param.create.MsCategoriesCreate;
import com.mashang.ordering.domain.param.update.MsCategoriesUpdate;
import com.mashang.ordering.domain.vo.MsCategoriesListVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MsCategoriesMapping {

    MsCategoriesMapping INSTANCE = org.mapstruct.factory.Mappers.getMapper(MsCategoriesMapping.class);

    //@Mapping(target = "msStoreId", ignore = true)
    MsCategories fromCreate(MsCategoriesCreate msCategoriesCreate);

    //List<MsCategoriesListVo> toListVos(List<MsCategories> msCategoriesList);

    MsCategories fromUpdate(MsCategoriesUpdate msCategoriesUpdate);
}
