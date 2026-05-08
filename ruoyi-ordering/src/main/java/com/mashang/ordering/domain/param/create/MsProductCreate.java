package com.mashang.ordering.domain.param.create;

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
  @NotBlank(message = "请输入状态（'0'下架，'1'上架）")
  private String status;

  @ApiModelProperty(value = "商品简介")
  private String productIntroduction;

  @ApiModelProperty(value = "规格种类（'0'单规格默认，'1'多规格）")
  @NotBlank(message = "请输入规格种类（'0'单规格默认，'1'多规格）")
  private String specificationKind;

  @ApiModelProperty(value = "规格Id")
  @NotNull(message = "请输入规格Id")
  private Long specificationId;

  @ApiModelProperty(value = "产品描述")
  private String productDescription;

  @ApiModelProperty(value = "获得积分")
  private String earnPoints;

}