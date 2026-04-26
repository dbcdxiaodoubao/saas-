package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MsSpecificationType",description = "商品规格类类型")
public class MsSpecificationType extends BaseModel {

  @TableId
  @ApiModelProperty(value = "规格类型ID")
  private Long specificationTypeId;

  @ApiModelProperty(value = "规格类型名")
  private String specificationTypeName;

  @ApiModelProperty(value = "排序")
  private Long sort;

  @ApiModelProperty(value = "规格(组)id")//父节点id
  private Long specificationId;

//  @ApiModelProperty(value = "规格类型ID")
//  private List<MsSpecificationValue> values;

}
