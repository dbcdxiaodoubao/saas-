package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MsSpecification {

  @ApiModelProperty(value = "规格ID")
  @TableId(type = IdType.AUTO)
  private Long specificationId;

  @ApiModelProperty(value = "规格名称")
  private String specificationName;

  @ApiModelProperty(value = "排序")
  private Long sort;

  @ApiModelProperty(value = "规格及属性值")
  @TableField(typeHandler = FastjsonTypeHandler.class)
  private String specsAndAttrs;

  @ApiModelProperty(value = "商品ID")
  private Date createTime;

  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty(value = "创建时间")
  private Date updateTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @ApiModelProperty(value = "更新时间")
  private String remark;

}
