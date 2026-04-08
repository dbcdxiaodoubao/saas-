package com.mashang.ordering.domain.param.selete;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("餐桌检索条件")
@Data
public class MsTableParam {

    @ApiModelProperty("店铺名称")
    private String storeName;

    @ApiModelProperty("桌号")
    private String tableNumber;
}
