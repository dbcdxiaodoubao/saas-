package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class MsOrderDetail extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long orderDetailId;
  private Long orderId;
  private Double totalAmount;
  private Long productQuantity;
  private Long productSkuId;
  private Long cumulativeAddCount;
  private Long productId;
  private String productName;
  private String specification;
  private String productImage;
  private Double productPrice;
  private String issueStatus;

  
}
