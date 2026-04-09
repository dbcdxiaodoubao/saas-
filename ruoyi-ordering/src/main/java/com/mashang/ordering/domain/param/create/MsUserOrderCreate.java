package com.mashang.ordering.domain.param.create;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("用户新建订单实体")
public class MsUserOrderCreate {

  @ApiModelProperty(value = "用户id",required = true,example = "1")
  @NotNull(message = "用户id不能为空")
  private Long userId;
  @ApiModelProperty(value = "桌号id",required = true,example = "1")
  @NotNull(message = "桌号id不能为空")
  private Long tableId;
  @ApiModelProperty(value = "商店id",required = true,example = "1")
  @NotNull(message = "商店id不能为空")
  private Long storeId;
  @ApiModelProperty(value = "积分减额",required = true,example = "1")
  @NotNull(message = "积分剪额不能为空")
  private String pointsDeduction;
  @ApiModelProperty(hidden = true)
  private double productTotalPrice;
  @ApiModelProperty(value = "就餐人数",required = true,example = "2")
  @NotBlank(message = "就餐人数不能为空")
  private String dinersNumber;
  @ApiModelProperty(value = "购买类型（'0'堂食,'1'自取,'2'预约）",required = true,example = "0")
  @NotBlank(message = "购买类型不能为空")
  private String buyType;
  @ApiModelProperty(value = "备注",required = false,example = "多加米饭")
  private String remark;

  @ApiModelProperty(value = "商品列表",required = true)
  private List<MsUserOrderProductCreate> msUserOderProductCreateList;

  @ApiModelProperty(hidden = true)
  private Long orderId;

  @ApiModelProperty(hidden = true)
  private String orderNumber;

  @ApiModelProperty(hidden = true)
  private String pickupNumber;
}
