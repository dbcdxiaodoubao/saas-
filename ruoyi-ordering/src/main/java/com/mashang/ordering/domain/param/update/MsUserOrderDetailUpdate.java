package com.mashang.ordering.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("订单详情更改实体")
public class MsUserOrderDetailUpdate{

  @ApiModelProperty("订单详情id")
  private Long orderDetailId;

  @ApiModelProperty("商品单价")
  private Double productPrice;

  @ApiModelProperty("商品数量")
  private Long productQuantity;

  @ApiModelProperty("出单状态（'0'待出单，'1'已出单）")
  private String issueStatus;

  @ApiModelProperty("商品单价")
  private Double totalAmount;

}
