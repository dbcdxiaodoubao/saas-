package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("购物车查询实体")
public class MsUserShoppingCartVo {

    @ApiModelProperty("总价格")
    Double totalPrice;

    @ApiModelProperty("购物车列表")
    List<MsUserShoppingCartListVo> msUserShoppingCartListVos;
}
