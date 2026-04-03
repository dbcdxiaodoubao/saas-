package com.mashang.ordering.domain.param.create;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("用户新建订单实体")
public class MsUserOrderCreate {

  @ApiModelProperty(value = "用户id",required = true,example = "1")
  private long userId;
  @ApiModelProperty(value = "桌号id",required = true,example = "1")
  private long tableId;
  @ApiModelProperty(value = "订单类型（0为堂食，1为外卖）",required = true,example = "0")
  private String orderType;
  @ApiModelProperty(value = "积分减额",required = true,example = "1")
  private String pointsDeduction;
  private String actualPay;
  private String buyType;
  private Date reservePickTime;
  private double productTotalPrice;
  private String dinersNumber;
  private String remark;
}
