package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MsUserOrderProductInfoVo {

    @ApiModelProperty(value = "商品封面")
    private String productImage;

    @ApiModelProperty(value = "商品名称 - 规格")
    private String productNameAndSpecification;

    @ApiModelProperty(value = "￥商品单价 * 数量")
    private String productPriceAndQuantity;

}
