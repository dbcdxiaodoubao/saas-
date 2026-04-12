package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MsTableOrderVO", description = "桌台订单信息返回VO")
public class MsTableOrderListVo {
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "桌台ID")
    private Long tableId;

    @ApiModelProperty(value = "门店ID")
    private Long storeId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "门店名称")
    private String storeName;

    @ApiModelProperty(value = "桌台编号前缀")
    private String tableNumberPrefix;

    @ApiModelProperty(value = "桌台编号")
    private String tableNumber;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "实际支付金额")
    private Double actualPay;

    @ApiModelProperty(value = "支付类型")
    private String payType;

    @ApiModelProperty(value = "支付时间")
    private String payTime;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "订单明细列表")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<MsTableOrderListProduct> orderDetails;
}
