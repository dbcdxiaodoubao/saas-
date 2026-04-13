package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户订单详情商品实体")
public class MsUserOrderProductDtl {

  @ApiModelProperty("商品封面url")
  private String productImage;

  @ApiModelProperty("商品名称")
  private String productName;

  @ApiModelProperty("商品单价")
  private Double productPrice;

  @ApiModelProperty("商品数量")
  private Long productQuantity;

  @ApiModelProperty("出单状态（'0'待出单，'1'已出单）")
  private String issueStatus;

  @ApiModelProperty("规格")
  private String specification;

  @ApiModelProperty("累计加菜次数")
  private Long cumulativeAddCount;

  @ApiModelProperty("商品id")
  private Long productId;

  @ApiModelProperty("订单详情id")
  private Long orderDetailId;
}
