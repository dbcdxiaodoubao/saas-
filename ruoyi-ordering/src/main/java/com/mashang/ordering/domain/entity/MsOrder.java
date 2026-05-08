package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("订单实体")
public class MsOrder extends BaseEntity {

  @ApiModelProperty("订单ID")
  @TableId(type = IdType.AUTO)
  private Long orderId;

  @ApiModelProperty("用户ID")
  private Long userId;

  @ApiModelProperty("桌位ID")
  private Long tableId;

  @ApiModelProperty("订单号")
  private String orderNumber;

  @ApiModelProperty("订单状态(0为未支付，1为未出单/已支付，2为已完成，3退款单（已退款），4为已删除,5退款中)")
  private String orderStatus;

  @ApiModelProperty("支付时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date payTime;

  @ApiModelProperty("积分折扣")
  private String pointsDeduction;

  @ApiModelProperty("实际支付金额")
  private Double actualPay;

  @ApiModelProperty("购买类型（0堂食,1自取,2预约）")
  private String buyType;

  @ApiModelProperty("预计取餐时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date reservePickTime;

  @ApiModelProperty("商品总价")
  private Double productTotalPrice;

  @ApiModelProperty("就餐人数")
  private Long dinersNumber;

  @ApiModelProperty("备注")
  private String remark;

  @ApiModelProperty("制作完成时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date finishTime;

  @ApiModelProperty("门店ID")
  private Long storeId;

  @ApiModelProperty("支付方式（0余额支付，1支付宝支付）")
  private String payType;

  @ApiModelProperty("取餐号")
  private String pickupNumber;

  @ApiModelProperty("删除标志（0代表存在 1代表删除）")
  private String delFlag;
}