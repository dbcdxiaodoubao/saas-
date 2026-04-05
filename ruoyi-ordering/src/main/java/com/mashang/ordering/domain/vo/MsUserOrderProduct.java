package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户订单列表商品实体")
public class MsUserOrderProduct {

  @ApiModelProperty("商品id")
  private long productId;

  @ApiModelProperty("商品封面url")
  private String productCover;
}
