package com.mashang.ordering.domain.param.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "MsSpecificationCreate",description = "商品规格添加参数")
public class MsSpecificationCreate {

  @ApiModelProperty(value = "规格名称")
  @NotBlank(message = "规格名称不能为空")
  private String specificationName;

  @ApiModelProperty(value = "排序")
  @NotNull(message = "排序不能为空")
  private Long sort;

  @ApiModelProperty(value = "规格及属性值")
  @NotNull(message = "规格及属性值不能为空")
  private List<MsSpecificationTypeCreate> specsAndAttrs;


}
