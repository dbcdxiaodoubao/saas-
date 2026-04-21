package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "OrderProductVO", description = "订单商品明细VO")
public class MsTableOrderDtoProducts {

    @ApiModelProperty(value = "订单详情ID", example = "1001")
    private Long orderDetailId;

    @ApiModelProperty(value = "商品数量", example = "2")
    private Integer productQuantity;

    @ApiModelProperty(value = "商品总价", example = "50.00")
    private Double totalAmount;

    @ApiModelProperty(value = "商品ID", example = "101")
    private Long productId;

    @ApiModelProperty(value = "商品名称", example = "招牌红烧肉")
    private String productName;

    @ApiModelProperty(value = "商品规格", example = "大份")
    private String specification;

    @ApiModelProperty(value = "商品单价", example = "25.00")
    private Double productPrice;

    @ApiModelProperty(value = "商品总价（单价×数量）", example = "50.00")
    private Double productTotalPrice;

    @ApiModelProperty(value = "创建时间", example = "2025-05-20 12:30:00")
    private String createTime;
}