package com.mashang.ordering.mapping;

import com.mashang.ordering.domain.entity.MsOrder;
import com.mashang.ordering.domain.entity.MsOrderDetail;
import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.entity.MsSpecification;
import com.mashang.ordering.domain.param.create.MsProductCreate;
import com.mashang.ordering.domain.param.update.MsProductUpdate;
import com.mashang.ordering.domain.param.update.MsUserOrderDetailUpdate;
import com.mashang.ordering.domain.param.update.MsUserOrderUpdate;
import com.mashang.ordering.domain.vo.MsProductDtlVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MsProductMapping {

    MsProductMapping INSTANCE = Mappers.getMapper(MsProductMapping.class);

    MsProduct toMsProduct(MsProductCreate msProductCreate);

    MsSpecification toMsSpecification(MsProductCreate msProductCreate);

    MsProductDtlVo toMsProductDtlVo(MsProduct msProduct);

    MsProduct toMsProduct(MsProductUpdate msProductUpdate);

    MsSpecification toMsSpecification(MsProductUpdate msProductUpdate);

    List<MsOrderDetail> toMsOrderDetailList(List<MsUserOrderDetailUpdate> msUserOrderDetailUpdates);

    MsOrder toMsOrder(MsUserOrderUpdate update);

}
