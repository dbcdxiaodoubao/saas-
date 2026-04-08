package com.mashang.ordering.domain.param.update;

import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "MsCategoriesUpdate",description = "商品分类修改参数")
public class MsCategoriesUpdate{

    @ApiModelProperty(value = "商品分类和门店映射项id")
    @NotNull(message = "商品分类和门店映射项id不能为空,此项为必填项")
    private Long storeCategoriesId;

    @ApiModelProperty(value = "门店id")
    @NotNull(message = "门店id不能为空,此项为必填项")
    private Long msStoreId;

    @ApiModelProperty(value = "分类名称")
    @NotBlank(message = "分类名称不能为空,此项为必填项")
    private String categoriesName;

    @ApiModelProperty(value = "图片路径")
    private String pictureUrl;

    @ApiModelProperty(value = "排序")
    @NotBlank(message = "排序不能为空,此项为必填项")
    private Integer sort;

    @ApiModelProperty(value = "启用状态 0-留 1-删")
    @NotBlank(message = "启用状态不能为空,此项为必填项")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;
}
