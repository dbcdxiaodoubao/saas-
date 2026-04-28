package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MsSpecificationValue",description = "商品规格值类型")
public class MsSpecificationValue extends BaseModel {
  @TableId
  @ApiModelProperty(value = "规格值ID")
  private Long specificationValueId;

  @ApiModelProperty(value = "规格类型值名")
  private String specs;

  @ApiModelProperty(value = "规格加价格")
  private Long attr;

  @ApiModelProperty(value = "排序")
  private Long sort;

  @ApiModelProperty(value = "规格值")
  private Long specificationTypeId;

}
