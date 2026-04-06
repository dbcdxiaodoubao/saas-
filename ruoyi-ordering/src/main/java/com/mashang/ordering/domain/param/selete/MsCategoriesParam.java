package com.mashang.ordering.domain.param.selete;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "MsCategoriesParam", description = "分类模糊查询参数")
public class MsCategoriesParam {
    private String msStoreName;
    private String msCategoriesName;
}
