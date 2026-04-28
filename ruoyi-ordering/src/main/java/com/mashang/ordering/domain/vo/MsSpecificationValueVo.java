package com.mashang.ordering.domain.vo;

import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MsSpecificationValue",description = "商品规格值类型")
public class MsSpecificationValueVo {

  @ApiModelProperty(value = "规格值ID")
  private Long specificationValueId;

  @ApiModelProperty(value = "规格类型值名")
  private String specs;

  @ApiModelProperty(value = "规格加价格")
  private Long attr;

}
