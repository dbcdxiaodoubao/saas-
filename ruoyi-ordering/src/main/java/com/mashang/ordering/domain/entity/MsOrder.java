package com.mashang.ordering.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mashang.ordering.domain.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class MsOrder extends BaseModel {

  @TableId(type = IdType.AUTO)
  private long orderId;
  private long userId;
  private long tableId;
  private String orderNumber;
  private String orderType;
  private String orderStatus;
  private java.sql.Timestamp payTime;
  private String pointsDeduction;
  private String actualPay;
  private String buyType;
  private Date reservePickTime;
  private double productTotalPrice;
  private String dinersNumber;
  private Long storeId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date finishTime;
}
