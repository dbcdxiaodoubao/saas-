package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.ordering.domain.model.BaseModel;
import com.mashang.ordering.utils.JsonSpecificationTypeHandle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MsSpecificationTypeVo",description = "商品规格类类型列表视图")
public class MsSpecificationTypeVo {

  @ApiModelProperty(value = "规格类型ID")
  private Long specificationTypeId;

  @ApiModelProperty(value = "规格类型名")
  private String specificationTypeName;

  @ApiModelProperty(value = "规格值列表")
  @TableField(typeHandler = JsonSpecificationTypeHandle.class)
  private List<MsSpecificationValueVo> specificationValues;
}
