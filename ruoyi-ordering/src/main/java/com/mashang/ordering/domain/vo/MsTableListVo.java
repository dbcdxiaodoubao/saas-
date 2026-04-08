package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("分页查询桌号信息列表")
@Data
public class MsTableListVo{

    @ApiModelProperty(value = "餐桌ID")
    private Long tableId;

    @ApiModelProperty(value = "店铺名称")
    private String storeName;

    @ApiModelProperty(value = "桌号")
    private String tableNumber;

    @ApiModelProperty(value = "下单数")
    private Integer orderNumber;

    @ApiModelProperty(value = "消费金额")
    private String amountSpent;

    @ApiModelProperty(value = "上次下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastOrderTime;

    @ApiModelProperty(value = "餐桌状态", example = "1-启用 0-禁用")
    private Character state;                           // 1-启用 0-禁用
}
