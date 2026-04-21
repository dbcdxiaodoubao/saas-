package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MsCategoriesType",description = "商品规格类型")
public class MsSpecificationType {

  @ApiModelProperty(value = "规格类型")
  private String type;

  @ApiModelProperty(value = "规格类型ID")
  private List<MsSpecificationOption> values;

}
