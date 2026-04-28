package com.mashang.ordering.mapping;

import com.mashang.ordering.domain.entity.MsStoreCategories;
import org.mapstruct.Mapper;

@Mapper
public interface MsStoreCategoriesMapping{
    MsStoreCategoriesMapping INSTANCE = org.mapstruct.factory.Mappers.getMapper(MsStoreCategoriesMapping.class);

    MsStoreCategories fromMidUpdate(MsStoreCategories msStoreCategories);
}
