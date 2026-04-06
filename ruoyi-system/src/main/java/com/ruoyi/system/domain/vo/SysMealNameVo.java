package com.ruoyi.system.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("套餐名称实体")
@Data
public class SysMealNameVo {

  @ApiModelProperty("套餐ID")
  private long mealId;

  @ApiModelProperty("套餐名称")
  private String mealName;

}
