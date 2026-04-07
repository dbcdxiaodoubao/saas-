package com.mashang.ordering.domain.param.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("用户订单新增商品实体")
public class MsUserOrderProductCreate {

  @ApiModelProperty(value = "商品id",required = true)
  @NotNull(message = "商品id不能为空")
  private long productId;

  @ApiModelProperty(value = "商品名称",required = true)
  @NotBlank(message = "商品名称不能为空")
  private String productName;

  @ApiModelProperty(value = "商品数量",required = true)
  @NotNull(message = "商品数量不能为空")
  private long productQuantity;

  @ApiModelProperty("规格")
  private String specification;

  @ApiModelProperty(value = "商品价格",required = true)
  @NotNull(message = "商品价格不能为空")
  private double productPrice;

  @ApiModelProperty(hidden = true)
  private long cumulativeAddCount;

}
