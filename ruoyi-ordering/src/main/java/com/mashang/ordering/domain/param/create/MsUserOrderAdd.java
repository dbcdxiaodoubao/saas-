package com.mashang.ordering.domain.param.create;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("用户新加菜实体")
public class MsUserOrderAdd {

  @ApiModelProperty(value = "订单id",required = true)
  @NotNull(message = "订单id不能为空")
  private Long orderId;

  @ApiModelProperty(value = "商品列表",required = true)
  private List<MsUserOrderProductCreate> msUserOderProductCreateList;

}
