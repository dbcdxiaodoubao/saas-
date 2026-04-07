package com.mashang.ordering.domain.param.selete;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MsCategoriesParam", description = "分类模糊查询参数")
public class MsCategoriesParam {
    @ApiModelProperty(value = "门店名称", example = "店名")
    private String msStoreName;

    @ApiModelProperty(value = "分类名称", example = "分类名")
    private String msCategoriesName;
}
