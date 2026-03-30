package com.mashang.ordering.domain.entity;


import com.mashang.ordering.domain.model.BaseModel;
import lombok.Data;

@Data
public class MsOrder extends BaseModel {

  private long orderId;
  private long userId;
  private long tableNumberId;
  private String orderNumber;
  private String orderType;
  private String orderStatus;
  private java.sql.Timestamp payTime;
  private String pointsDeduction;
  private String actualPay;
  private String buyType;
  private java.sql.Timestamp reservePickTime;
  private double productTotalPrice;
  private String dinersNumber;
}
