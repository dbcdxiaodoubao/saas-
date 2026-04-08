package com.mashang.ordering.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("餐桌详情实体")
@Data
public class MsTableDtlVo {

    @ApiModelProperty("餐桌ID")
    private Long tableId;

    @ApiModelProperty("店铺ID")
    private Long storeId;

    @ApiModelProperty("桌号")
    private String tableNumber;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态(0禁用，1启用)")
    private Character state;

}
