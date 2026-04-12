package com.mashang.ordering.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MsUserOrderListPageVo extends BaseModel {

  @ApiModelProperty(value = "订单id")
  private Long orderId;

  @ApiModelProperty(value = "门店")
  private String storeName;

  @ApiModelProperty(value = "订单号")
  private String orderNumber;

  @ApiModelProperty(value = "桌号")
  private String tableNumber;

  @ApiModelProperty(value = "取餐号")
  private String pickUpNumber;

  @ApiModelProperty(value = "用户id|昵称")
  private String userIdAndNickname;

  @ApiModelProperty(value = "用户姓名|电话")
  private String usernameAndPhone;

  @ApiModelProperty(value = "商品信息")
  private List<MsUserOrderProductInfoVo> productInfoList;

  @ApiModelProperty(value = "实际支付")
  private Double actualPay;

  @ApiModelProperty(value = "支付方式")
  private String payType;

  @ApiModelProperty(value = "购买类型（0堂食,1自取,2预约）")
  private String buyType;

  @ApiModelProperty(value = "预约取餐时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date reservePickTime;

  @ApiModelProperty(value = "支付时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date payTime;

  @ApiModelProperty(value = "订单状态（'0'未支付,'1'待出单,'2'已收货/已取餐,'3'退款单,'4'已删除）")
  private String orderStatus;

  @ApiModelProperty(value = "添加时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

}
