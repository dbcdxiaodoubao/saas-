package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MsCategoriesListVo", description = "分类列表vo")
public class MsCategoriesListVo {
    @ApiModelProperty(value = "分类id")
    private Long categoriesId;

    @ApiModelProperty(value = "分类名称")
    private String categoriesName;

    @ApiModelProperty(value = "门店名称",example = "测试门店")
    private String storeName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "启用状态 0-留 1-删")
    private Character status;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
