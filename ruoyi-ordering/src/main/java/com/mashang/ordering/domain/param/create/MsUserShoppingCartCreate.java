package com.mashang.ordering.domain.param.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("购物车创建实体")
public class MsUserShoppingCartCreate {

  @ApiModelProperty(value = "用户id",required = true)
  @NotNull(message = "用户id不能为空")
  private Long userId;

  @ApiModelProperty(value = "商店id",required = true)
  @NotNull(message = "商店id不能为空")
  private Long storeId;

  @ApiModelProperty(value = "商品id",required = true)
  @NotNull(message = "商品id不能为空")
  private Long productId;

  @ApiModelProperty(value = "商品数量",required = true)
  @NotNull(message = "商品数量不能为空")
  private double productQuantity;

  @ApiModelProperty(value = "商品总额",required = true)
  @NotNull(message = "商品总额不能为空")
  private Long totalAmount;

  @ApiModelProperty(value = "商品名称",required = true)
  @NotBlank(message = "商品名称不能为空")
  private String productName;

  @ApiModelProperty(value = "规格",required = true)
  @NotBlank(message = "规格不能为空")
  private String specification;

  @ApiModelProperty(value = "商品单价",required = true)
  @NotNull(message = "商品单价不能为空")
  private double productPrice;

  @ApiModelProperty(value = "商品封面url",required = true)
  @NotBlank(message = "商品封面url不能为空")
  private String productImage;
}
