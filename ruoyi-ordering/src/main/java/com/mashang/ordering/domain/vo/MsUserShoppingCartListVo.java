package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("查询购物车列表实体")
public class MsUserShoppingCartListVo {

  @ApiModelProperty("购物车id")
  private Long shoppingCartId;
  @ApiModelProperty("商品id")
  private Long productId;
  @ApiModelProperty("商品数量")
  private double productQuantity;
  @ApiModelProperty("商品总额")
  private Long totalAmount;
  @ApiModelProperty("商品名称")
  private String productName;
  @ApiModelProperty("规格")
  private String specification;
  @ApiModelProperty("商品单价")
  private double productPrice;
  @ApiModelProperty("商品封面url")
  private String productImage;
}
