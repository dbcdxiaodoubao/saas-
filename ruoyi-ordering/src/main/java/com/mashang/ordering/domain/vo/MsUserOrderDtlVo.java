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

  @ApiModelProperty("订单id")
  private long orderId;
  @ApiModelProperty("用户id")
  private long userId;
  @ApiModelProperty("桌号")
  private String tableNumber;
  @ApiModelProperty("商店名称")
  private String storeName;
  @ApiModelProperty("订单号")
  private String orderNumber;
  @ApiModelProperty("取餐号")
  private String pickupNumber;
  @ApiModelProperty("订单详情商品列表")
  private List<MsUserOrderProductDtl> userOderProductDtlList;

  @ApiModelProperty("订单状态(0为未支付，1为未出单/已支付，2为已取餐，3退款单，4为已删除)")
  private String orderStatus;
  @ApiModelProperty("支付时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date payTime;
  @ApiModelProperty("积分折扣")
  private String pointsDeduction;
  @ApiModelProperty("实际支付")
  private String actualPay;
  @ApiModelProperty("购买类型（'0'堂食,'1'自取,'2'预约）")
  private String buyType;
  @ApiModelProperty("预计取餐时间")
  private Date reservePickTime;
  @ApiModelProperty("商品总价")
  private double productTotalPrice;
  @ApiModelProperty("就餐人数")
  private String dinersNumber;
  @ApiModelProperty("制作完成时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date finishTime;
  @ApiModelProperty("创建订单时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @ApiModelProperty("备注")
  private String remark;
}
