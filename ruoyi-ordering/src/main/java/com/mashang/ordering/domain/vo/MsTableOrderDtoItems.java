package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MsTableOrderDtoItems", description = "每次下单订单项VO")
public class MsTableOrderDtoItems {

    @ApiModelProperty(value = "本次加菜数 0表示第一次下单", example = "0")
    private Long cumulativeAddCount;

    @ApiModelProperty(value = "商品明细列表")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<MsTableOrderDtoProducts> products;
}