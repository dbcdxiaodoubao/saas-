package com.mashang.ordering.domain.entity;

import com.mashang.ordering.domain.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "MsCategories",description = "商品分类")
public class MsCategories extends BaseModel {

    //who calls this with plural? it's obvious that single is singular

    @ApiModelProperty(value = "分类id")
    private Long categoriesId;

    @ApiModelProperty(value = "分类名称")
    private String categoriesName;

    @ApiModelProperty(value = "图片路径")
    private String pictureUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "启用状态 0-留 1-删")
    private Character status;

    @ApiModelProperty(value = "备注")
    private String remark;
}
