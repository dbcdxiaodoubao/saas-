package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MsStoreProduct {

  @ApiModelProperty(value = "门店商品ID")
  @TableId(type = IdType.AUTO)
  private Long storeProductId;

  @ApiModelProperty(value = "商品ID")
  private Long productId;

  @ApiModelProperty(value = "门店ID")
  private Long storeId;

  @ApiModelProperty(value = "删除标志")
  @TableLogic
  private String delFlag;

  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty(value = "创建者")
  private String createBy;

  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty(value = "创建时间")
  private Date createTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @ApiModelProperty(value = "更新者")
  private String updateBy;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @ApiModelProperty(value = "更新时间")
  private Date updateTime;


}
