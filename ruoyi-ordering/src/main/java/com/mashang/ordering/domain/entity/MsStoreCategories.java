package com.mashang.ordering.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mashang.ordering.domain.model.BaseModel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MsStoreCategories",description = "商品分类和门店映射项")
public class MsStoreCategories extends BaseModel {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "商品分类和门店映射项id")
    private Long storeCategoriesId;

    @ApiModelProperty(value = "商品分类id")
    private Long categoriesId;

    @ApiModelProperty(value = "门店id")
    private Long storeId;
}
