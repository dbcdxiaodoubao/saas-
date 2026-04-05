package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
public class MsOrderDetail {

  @TableId(type = IdType.AUTO)
  private Long orderDetailId;
  private Long orderId;
  private double totalAmount;
  private Long productQuantity;
  private Long productSkuId;
  private Long cumulativeAddCount;
  private Long productId;
  private String productName;
  private String specification;
  private String productImage;

}
