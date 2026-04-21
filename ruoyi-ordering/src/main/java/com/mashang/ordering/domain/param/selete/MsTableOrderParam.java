package com.mashang.ordering.domain.param.selete;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("餐桌订单查询参数")
public class MsTableOrderParam {
    @ApiModelProperty("餐桌id")
    @NotNull(message = "餐桌id不能为空")
    private Long tableId;

    @ApiModelProperty("订单状态")
    private String orderStatus;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("用户手机号")
    private String userTel;

    @ApiModelProperty("订单号")
    private String orderNumber;

    @ApiModelProperty("支付方式")
    private String payType;
}
