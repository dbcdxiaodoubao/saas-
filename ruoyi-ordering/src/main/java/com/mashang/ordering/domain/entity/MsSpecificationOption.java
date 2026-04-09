package com.mashang.ordering.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MsSpecificationOption {

  @ApiModelProperty(value = "规格类型值名")
  private String value;

  @ApiModelProperty(value = "规格类型值名")
  private Long addPrice;

}
