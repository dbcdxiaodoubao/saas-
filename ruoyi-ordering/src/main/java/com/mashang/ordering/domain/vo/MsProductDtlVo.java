package com.mashang.ordering.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@ApiModel("商品详情")
public class MsProductDtlVo {

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "商品分类ID")
    private Long productCategoriesId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "门店名称")
    private String storeName;

    @ApiModelProperty(value = "商品分类名称")
    private String productCategoriesName;

    @ApiModelProperty(value = "关键词")
    private String keyword;

    @ApiModelProperty(value = "规格类型（单规格0，多规格1）")
    private String specificationType;

    @ApiModelProperty(value = "规格")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String specsAndAttrs;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "商品价格")
    private Double productPrice;

    @ApiModelProperty(value = "市场价格")
    private Double marketPrice;

    @ApiModelProperty(value = "库存")
    private Integer inventory;

    @ApiModelProperty(value = "商品封面图")
    private String productCover;

    @ApiModelProperty(value = "商品轮播图")
    private String productCarousel;

    @ApiModelProperty(value = "商品介绍")
    private String productIntroduction;

    @ApiModelProperty(value = "状态（'0'下架，'1'上架）")
    private String status;

    @ApiModelProperty(value = "商品描述")
    private String productDescription;

    @ApiModelProperty(value = "获得积分")
    private String earnPoints;

    @ApiModelProperty(value = "销量")
    private Long sales;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "规格id")
    private Long specificationId;

    @ApiModelProperty(value = "门店id")
    private Long storeId;
}
