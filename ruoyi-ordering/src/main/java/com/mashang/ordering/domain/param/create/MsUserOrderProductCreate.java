package com.mashang.ordering.domain.param.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户订单新增商品实体")
public class MsUserOrderProductCreate {

  @ApiModelProperty("商品id")
  private long productId;

  @ApiModelProperty("商品名称")
  private String productName;

  @ApiModelProperty("商品数量")
  private long productQuantity;

  @ApiModelProperty("规格")
  private String specification;

  @ApiModelProperty("商品价格")
  private double productPrice;

  @ApiModelProperty(hidden = true)
  private long cumulativeAddCount;

}
