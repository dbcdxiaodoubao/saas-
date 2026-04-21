package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MsTableOrderListProduct", description = "桌台订单列表商品信息")
public class MsTableOrderListProduct {
    @ApiModelProperty(value = "订单明细ID")
    private Long orderDetailId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品封面URL")
    private String productCoverUrl;

    @ApiModelProperty(value = "商品数量")
    private Integer productQuantity;

    @ApiModelProperty(value = "商品单价")
    private Double productPrice;

    @ApiModelProperty(value = "商品规格")
    private String specification;
}
