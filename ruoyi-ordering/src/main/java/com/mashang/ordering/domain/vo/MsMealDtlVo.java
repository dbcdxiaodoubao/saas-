package com.mashang.ordering.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel("租户套餐详情实体")
@Data
public class MsMealDtlVo {

  @ApiModelProperty("套餐ID")
  private long mealId;

  @ApiModelProperty("套餐名称")
  private String mealName;

  @ApiModelProperty("状态")
  private String mealStatus;

  @ApiModelProperty("备注")
  private String remark;

  @ApiModelProperty("菜单ID集合")
  private List<Long> menuIds;
}
