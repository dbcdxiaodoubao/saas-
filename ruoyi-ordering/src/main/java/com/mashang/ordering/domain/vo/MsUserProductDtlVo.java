package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("用户商品详情实体")
public class MsUserProductDtlVo {

  @ApiModelProperty("商品id")
  private long productId;

  @ApiModelProperty("商品分类名")
  private String categoriesName;

  @ApiModelProperty("商品名称")
  private String productName;

  @ApiModelProperty("商品价格")
  private double productPrice;

  @ApiModelProperty("商品封面url")
  private String productCover;

  @ApiModelProperty("商品轮播图url")
  private String productCarousel;

  @ApiModelProperty("商品简介")
  private String productIntroduction;

  @ApiModelProperty("商品描述")
  private String productDescription;

  @ApiModelProperty("商品积分")
  private String earnPoints;
  @ApiModelProperty("规格值及加价")
  private List<MsUserProductListVo.SpecificationType> specsAndAttrs;

  @Data
  @ApiModel("规格实体")
  public static class SpecificationType {
    @ApiModelProperty("规格名称")
    private String type; // 规格类型：份量
    @ApiModelProperty("规格值及加价")
    private List<MsUserProductListVo.SpecificationType.SpecificationValue> values;

    @Data
    @ApiModel("规格值及加价")
    public static class SpecificationValue {
      @ApiModelProperty("规格值")
      private String value;   // 1人份
      @ApiModelProperty("加价")
      private Integer addPrice; // 加价
    }
  }
}
