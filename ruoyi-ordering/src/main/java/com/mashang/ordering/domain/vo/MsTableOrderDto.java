package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "OrderDetailVO", description = "订单详情VO")
public class MsTableOrderDto {

    @ApiModelProperty(value = "订单ID", example = "1")
    private Long orderId;

    @ApiModelProperty(value = "桌位ID", example = "10")
    private Long tableId;

    @ApiModelProperty(value = "门店ID", example = "1")
    private Long storeId;

    @ApiModelProperty(value = "用户ID", example = "1001")
    private Long userId;

    @ApiModelProperty(value = "门店名", example = "XX餐厅")
    private String storeName;

    @ApiModelProperty(value = "取餐号", example = "A01")
    private String pickupNumber;

    @ApiModelProperty(value = "桌位号", example = "A-10")
    private String tableNumber;

    @ApiModelProperty(value = "就餐人数", example = "4")
    private Integer dinersNumber;

    @ApiModelProperty(value = "订单号", example = "202505200001")
    private String orderNumber;

    @ApiModelProperty(value = "商品总数", example = "8")
    private Integer productTotal;

    @ApiModelProperty(value = "商品总价", example = "200.00")
    private Double productTotalPrice;

    @ApiModelProperty(value = "积分抵扣", example = "10.00")
    private Double pointsDeduction;

    @ApiModelProperty(value = "实际支付", example = "190.00")
    private Double actualPay;

    @ApiModelProperty(value = "创建时间", example = "2025-05-20 12:30:00")
    private String createTime;

    @ApiModelProperty(value = "支付时间", example = "2025-05-20 12:35:00")
    private String payTime;

    @ApiModelProperty(value = "支付类型 1-微信 2-支付宝 3-现金", example = "1")
    private Integer payType;

    @ApiModelProperty(value = "每次下单订单项列表")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<MsTableOrderDtoItems> orderItems;
}
