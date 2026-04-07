package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户订单详情商品实体")
public class MsUserOrderProductDtl {

  @ApiModelProperty("商品id")
  private long productId;

  @ApiModelProperty("商品名称")
  private String productName;

  @ApiModelProperty("商品数量")
  private long productQuantity;

  @ApiModelProperty("规格")
  private String specification;

  @ApiModelProperty("商品价格")
  private long productPrice;

  @ApiModelProperty("累计加菜次数")
  private long cumulativeAddCount;

  @ApiModelProperty("商品封面url")
  private String productImage;
}
