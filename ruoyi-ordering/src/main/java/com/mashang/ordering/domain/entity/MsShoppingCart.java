package com.mashang.ordering.domain.entity;

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
@ApiModel("购物车实体")
public class MsShoppingCart {

  @ApiModelProperty("购物车id")
  @TableId(type = IdType.AUTO)
  private Long shoppingCartId;
  @ApiModelProperty("用户id")
  private Long userId;
  @ApiModelProperty("商品id")
  private Long productId;
  @ApiModelProperty("商店id")
  private Long storeId;
  @ApiModelProperty("商品数量")
  private double productQuantity;
  @ApiModelProperty("商品总额")
  private Double totalAmount;
  @ApiModelProperty("商品名称")
  private String productName;
  @ApiModelProperty("规格")
  private String specification;
  @ApiModelProperty("商品单价")
  private double productPrice;
  @ApiModelProperty("商品封面url")
  private String productImage;
  @ApiModelProperty("创建时间")
  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

}
