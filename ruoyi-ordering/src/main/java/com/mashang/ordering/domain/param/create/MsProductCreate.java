package com.mashang.ordering.domain.param.create;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("商品添加实体")
public class MsProductCreate {

  @ApiModelProperty(value = "展示店铺Id")
  @NotNull(message = "请输入展示店铺Id")
  private Long storeId;

  @ApiModelProperty(value = "商品名称")
  @NotBlank(message = "请输入商品名称")
  private String productName;

  @ApiModelProperty(value = "商品分类ID")
  @NotNull(message = "请输入商品分类ID")
  private Long productCategoriesId;

  @ApiModelProperty(value = "关键字")
  private String keyword;

  @ApiModelProperty(value = "单位名")
  private String unitName;

  @ApiModelProperty(value = "商品价格")
  @NotNull(message = "请输入商品价格")
  private Double productPrice;

  @ApiModelProperty(value = "市场价")
  private Double marketPrice;

  @ApiModelProperty(value = "库存")
  private Integer inventory;

  @ApiModelProperty(value = "商品封面图")
  @NotBlank(message = "请插入商品封面图")
  private String productCover;

  @ApiModelProperty(value = "商品轮播图")
  @NotBlank(message = "请插入商品轮播图")
  private String productCarousel;

  @ApiModelProperty(value = "状态（'0'下架，'1'上架）")
  @NotBlank(message = "请插入状态（'0'下架，'1'上架）")
  private String status;

  @ApiModelProperty(value = "商品简介")
  private String productIntroduction;

  @ApiModelProperty(value = "规格类型（单规格0，多规格1）")
  @NotBlank(message = "请输入规格类型")
  private String specificationType;

  @ApiModelProperty(value = "规格名称")
  @NotBlank(message = "请输入规格名称")
  private String specificationName;

  @ApiModelProperty(value = "规格")
  @NotBlank(message = "请输入规格")
  @TableField(typeHandler = JacksonTypeHandler.class)
  private String specsAndAttrs;

  @ApiModelProperty(value = "产品描述")
  private String productDescription;

  @ApiModelProperty(value = "获得积分")
  private String earnPoints;

  @ApiModelProperty(value = "规格id")
  private Long specificationId;

  @ApiModelProperty(value = "规格是否相同")
  @NotNull(message = "请选择规格是否相同")
  private Boolean isSpecSame;

}