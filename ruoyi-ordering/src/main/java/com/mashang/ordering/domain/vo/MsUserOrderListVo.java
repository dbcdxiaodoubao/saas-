package com.mashang.ordering.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("用户订单列表查询实体")
public class MsUserOrderListVo{

  @ApiModelProperty("订单id")
  private long orderId;

  @ApiModelProperty("订单号")
  private String orderNumber;

  @ApiModelProperty("商店id")
  private Long storeId;

  @ApiModelProperty("订单类型（0为堂食，1为外卖）")
  private String orderType;

  @ApiModelProperty("商品数")
  private long productNum;

  @ApiModelProperty("商品信息列表")
  private List<MsUserOrderProduct> productList;

  @ApiModelProperty("订单状态(0为未支付，1为未出单/已支付，2为已取餐，3退款单，4为已删除)")
  private String orderStatus;

  @ApiModelProperty("支付时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.sql.Timestamp payTime;

  @ApiModelProperty("实际支付")
  private String actualPay;

  @ApiModelProperty("商品总价")
  private double productTotalPrice;

  @ApiModelProperty("就餐人数")
  private String dinersNumber;

  @ApiModelProperty("创建订单时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;


}
