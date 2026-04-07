package com.mashang.ordering.mapping;

import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.entity.MsShoppingCart;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.param.create.MsProductCreate;
import com.mashang.ordering.domain.param.create.MsUserShoppingCartCreate;
import com.mashang.ordering.domain.param.update.MsProductUpdate;
import com.mashang.ordering.domain.vo.MsProductDtlVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MsShoppingCartMapping {

    MsShoppingCartMapping INSTANCE = Mappers.getMapper(MsShoppingCartMapping.class);

    MsShoppingCart toCreate(MsUserShoppingCartCreate msUserShoppingCartCreate);

}
