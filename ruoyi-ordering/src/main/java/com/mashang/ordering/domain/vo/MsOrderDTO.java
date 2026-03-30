package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.math.BigDecimal;

@Data
@ApiModel("管理端主页统计数据实体")
public class MsOrderDTO {
    @ApiModelProperty(value = "会员总数")
    private Long memberTotal;

    @ApiModelProperty(value = "今日订单数")
    private Long todayOrder;

    @ApiModelProperty(value = "订单总数")
    private Long orderTotal;

    @ApiModelProperty(value = "昨日订单数")
    private Long yesterdayOrder;

    @ApiModelProperty(value = "总金额")
    private BigDecimal amountTotal;

    @ApiModelProperty(value = "近七天订单数")
    private Long last7DaysOrder;

    @ApiModelProperty(value = "商品总数")
    private Long productTotal;

    @ApiModelProperty(value = "本月订单数")
    private Long thisMonthOrder;
}
