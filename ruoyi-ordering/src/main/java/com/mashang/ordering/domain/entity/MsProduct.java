package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MsProduct {

  @ApiModelProperty(value = "商品ID")
  @TableId(type = IdType.AUTO)
  private Long productId;

  @ApiModelProperty(value = "商品分类ID")
  private Long productCategoriesId;

  @ApiModelProperty(value = "商品名称")
  private String productName;

  @ApiModelProperty(value = "关键词")
  private String keyword;

  @ApiModelProperty(value = "规格类型（单规格0，多规格1）")
  private String specificationType;

  @ApiModelProperty(value = "单位名称")
  private String unitName;

  @ApiModelProperty(value = "商品价格")
  private Double productPrice;

  @ApiModelProperty(value = "市场价格")
  private Double marketPrice;

  @ApiModelProperty(value = "库存")
  private Integer inventory;

  @ApiModelProperty(value = "商品封面图")
  private String productCover;

  @ApiModelProperty(value = "商品轮播图")
  private String productCarousel;

  @ApiModelProperty(value = "商品介绍")
  private String productIntroduction;

  @ApiModelProperty(value = "状态（'0'下架，'1'上架）")
  private String status;

  @ApiModelProperty(value = "商品描述")
  private String productDescription;

  @ApiModelProperty(value = "获得积分")
  private String earnPoints;

  @ApiModelProperty(value = "销量")
  private Long sales;

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

  @ApiModelProperty(value = "备注")
  private String remark;

  @ApiModelProperty(value = "删除标志")
  private String delFlag;

  @ApiModelProperty(value = "规格id")
  private Long specificationId;

}