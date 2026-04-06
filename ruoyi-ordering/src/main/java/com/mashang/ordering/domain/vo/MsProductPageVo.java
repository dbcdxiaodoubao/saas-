package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("商品分页列表")
public class MsProductPageVo {

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "商品封面图")
    private String productCover;

    @ApiModelProperty(value = "所属门店")
    private String belongStore;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private Double productPrice;

    @ApiModelProperty(value = "销量")
    private Long sales;

    @ApiModelProperty(value = "关键词")
    private String keyword;

    @ApiModelProperty(value = "规格类型（单规格0，多规格1）")
    private String specificationType;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "库存")
    private String inventory;

    @ApiModelProperty(value = "状态（'0'下架，'1'上架）")
    private String status;

    @ApiModelProperty(value = "添加时间")
    private Date createTime;

}
