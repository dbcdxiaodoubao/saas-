package com.mashang.ordering.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("订单更改实体")
public class MsUserOrderUpdate{

  @ApiModelProperty("订单id")
  private Long orderId;

  @ApiModelProperty("订单详情商品更改列表")
  private List<MsUserOrderDetailUpdate> msUserOrderDetailUpdates;

  @ApiModelProperty("商品总价")
  private Double productTotalPrice;

}
