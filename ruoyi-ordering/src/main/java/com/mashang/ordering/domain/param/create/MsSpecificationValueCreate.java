package com.mashang.ordering.domain.param.create;

import com.mashang.ordering.domain.model.BaseModel;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "MsSpecificationValueCreate",description = "商品规格值类型添加参数")
public class MsSpecificationValueCreate {
  @ApiModelProperty(value = "规格类型值名")
  @NotBlank(message = "规格类型值名不能为空")
  private String specs;

  @ApiModelProperty(value = "规格加价格名")
  @NotNull(message = "规格加价格名不能为空")
  private Long attr;

}
