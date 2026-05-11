package com.mashang.ordering.domain.param.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("用户订单新增商品实体")
public class MsUserOrderProductCreate {

  @ApiModelProperty(value = "商品id",required = true)
  @NotNull(message = "商品id不能为空")
  private long productId;

  @ApiModelProperty(hidden = true)
  private String productName;

  @ApiModelProperty(value = "商品数量",required = true)
  @NotNull(message = "商品数量不能为空")
  private long productQuantity;

  @ApiModelProperty(hidden = true)
  private String productImage;

  @ApiModelProperty(hidden = true)
  private String specification;

  @ApiModelProperty("规格id列表")
  private List<Long> specificationIdLIst;

  @ApiModelProperty(hidden = true)
  private double productPrice;

  @ApiModelProperty(hidden = true)
  private long cumulativeAddCount;

}
