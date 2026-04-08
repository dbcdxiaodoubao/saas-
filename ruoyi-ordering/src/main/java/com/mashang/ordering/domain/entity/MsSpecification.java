package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mashang.ordering.domain.model.BaseModel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MsSpecification extends BaseModel {

  @ApiModelProperty(value = "规格ID")
  @TableId(type = IdType.AUTO)
  private Long specificationId;

  @ApiModelProperty(value = "规格名称")
  private String specificationName;

  @ApiModelProperty(value = "排序")
  private Long sort;

  @ApiModelProperty(value = "规格及属性值")
  @TableField(typeHandler = JacksonTypeHandler.class)
  private String specsAndAttrs;


}
