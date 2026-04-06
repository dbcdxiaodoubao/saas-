package com.mashang.ordering.domain.param.update;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel("租户套餐修改实体")
@Data
public class MsMealUpdate {

  @ApiModelProperty("套餐ID")
  @NotNull(message = "套餐ID不能为空")
  private long mealId;

  @ApiModelProperty("套餐名称")
  @NotBlank(message = "套餐名称不能为空")
  private String mealName;

  @ApiModelProperty("状态")
  @NotBlank(message = "状态")
  private String mealStatus;

  @ApiModelProperty("备注")
  private String remark;

  @ApiModelProperty("菜单ID集合")
  private List<Long> menuIds;
}
