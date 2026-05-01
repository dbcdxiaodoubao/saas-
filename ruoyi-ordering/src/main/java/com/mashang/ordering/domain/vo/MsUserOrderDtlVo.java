package com.mashang.ordering.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("用户订单详情实体")
public class MsUserOrderDtlVo{

  @ApiModelProperty("订单详情商品列表")
  private List<MsUserOrderProductDtl> userOderProductDtlList;

  @ApiModelProperty("购买类型（'0'堂食,'1'自取,'2'预约）")
  private String buyType;

  @ApiModelProperty("门店名称")
  private String storeName;

  @ApiModelProperty("取餐号")
  private String pickupNumber;

  @ApiModelProperty("桌号")
  private String tableNumber;

  @ApiModelProperty("就餐人数")
  private String dinersNumber;

  @ApiModelProperty("订单号")
  private String orderNumber;

  @ApiModelProperty("订单状态(0为未支付，1为未出单/已支付，2为已完成，3退款单（已退款），4为已删除,5退款中)")
  private String orderStatus;

  @ApiModelProperty("商品总价")
  private Double productTotalPrice;

  @ApiModelProperty("积分折扣")
  private String pointsDeduction;

  @ApiModelProperty("实际支付")
  private String actualPay;

  @ApiModelProperty("创建订单时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  @ApiModelProperty("支付时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date payTime;

  @ApiModelProperty("支付方式")
  private String payType;

  @ApiModelProperty("用户id")
  private Long userId;

  @ApiModelProperty("商店id")
  private Long storeId;

  @ApiModelProperty("订单id")
  private Long orderId;

  @ApiModelProperty("订单详情id")
  private Long orderDetailId;

  @ApiModelProperty("预计取餐时间")
  private Date reservePickTime;

  @ApiModelProperty("制作完成时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date finishTime;

  @ApiModelProperty("备注")
  private String remark;
}
