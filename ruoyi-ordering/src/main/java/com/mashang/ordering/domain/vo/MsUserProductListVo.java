package com.mashang.ordering.domain.vo;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("用户订单列表查询实体")
public class MsUserProductListVo {

  @ApiModelProperty("商品id")
  private long productId;

  @ApiModelProperty("商品分类id")
  private long productCategoriesId;

  @ApiModelProperty("商品名称")
  private String productName;

  @ApiModelProperty("商品价格")
  private double productPrice;

  @ApiModelProperty("商品封面url")
  private String productCover;

  @ApiModelProperty("商品简介")
  private String productIntroduction;

  @ApiModelProperty("规格值及加价")
  private List<SpecificationType> jsonfulPrice;

  @Data
  @ApiModel("规格实体")
  public static class SpecificationType {
    @ApiModelProperty("规格名称")
    private String type; // 规格类型：份量
    @ApiModelProperty("规格值及加价")
    private List<SpecificationValue> values;

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
