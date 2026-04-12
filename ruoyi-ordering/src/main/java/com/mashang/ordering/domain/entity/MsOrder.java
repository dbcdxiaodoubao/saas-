package com.mashang.ordering.domain.entity;


import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class MsOrder extends BaseEntity {

  private Long orderId;
  private Long userId;
  private Long tableId;
  private String orderNumber;
  private String orderStatus;
  private Date payTime;
  private String pointsDeduction;
  private Double actualPay;
  private String buyType;
  private Date reservePickTime;
  private Double productTotalPrice;
  private Long dinersNumber;
  private String createBy;
  private Date createTime;
  private String updateBy;
  private Date updateTime;
  private String remark;
  private String delFlag;
  private Date finishTime;
  private Long storeId;
  private String payType;
  private String pickupNumber;

}
