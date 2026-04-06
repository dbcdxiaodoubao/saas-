package com.mashang.ordering.domain.param.create;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("用户新建订单实体")
public class MsUserOrderCreate {

  @ApiModelProperty(value = "用户id",required = true,example = "1")
  private Long userId;
  @ApiModelProperty(value = "桌号id",required = true,example = "1")
  private Long tableId;
  @ApiModelProperty(value = "商店id",required = true,example = "1")
  private Long storeId;
  @ApiModelProperty(value = "订单类型（0为堂食，1为外卖）",required = true,example = "0")
  private String orderType;
  @ApiModelProperty(value = "积分减额",required = true,example = "1")
  private String pointsDeduction;
  @ApiModelProperty(hidden = true)
  private double productTotalPrice;
  @ApiModelProperty(value = "就餐人数",required = true,example = "2")
  private String dinersNumber;
  @ApiModelProperty(value = "备注",required = true,example = "多加米饭")
  private String remark;

  @ApiModelProperty(value = "商品列表",required = true)
  private List<MsUserOrderProductCreate> msUserOderProductCreateList;

  @ApiModelProperty(hidden = true)
  private Long orderId;

  @ApiModelProperty(hidden = true)
  private String orderNumber;
}
