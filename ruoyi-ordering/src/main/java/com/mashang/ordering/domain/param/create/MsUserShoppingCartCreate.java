package com.mashang.ordering.domain.param.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("购物车创建实体")
public class MsUserShoppingCartCreate {

  @ApiModelProperty(hidden = true)
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

  @ApiModelProperty(hidden = true)
  private Double totalAmount;

  @ApiModelProperty(hidden = true)
  private String productName;

  @ApiModelProperty(hidden = true)
  private String specification;

  @ApiModelProperty("规格id列表")
  private List<Long> specificationIdLIst;

  @ApiModelProperty(hidden = true)
  private double productPrice;

  @ApiModelProperty(hidden = true)
  private String productImage;
}
