package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MsSpecificationTypeVo",description = "商品规格类类型列表视图")
public class MsSpecificationTypeVo {

  @ApiModelProperty(value = "规格类型ID")
  private Long typeId;

  @ApiModelProperty(value = "规格类型名")
  private String typeName;

  @ApiModelProperty(value = "规格值列表")
  @TableField(typeHandler = JacksonTypeHandler.class)
  private List<MsSpecificationValueVo> values;
}
