package com.mashang.ordering.domain.param.selete;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MsStoreListParam",description = "门店列表查询参数")
public class MsStoreListParam {
    @ApiModelProperty(value = "门店名称",example = "测试门店")
    private String storeName;

    @ApiModelProperty(value = "门店电话",example = "172XXXXYYYY")
    private String storeTel;
}
