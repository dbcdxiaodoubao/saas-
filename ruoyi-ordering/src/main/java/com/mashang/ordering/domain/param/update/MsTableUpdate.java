package com.mashang.ordering.domain.param.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("餐桌详情实体")
@Data
public class MsTableUpdate {

    @ApiModelProperty("餐桌ID")
    @NotNull(message = "餐桌ID不能为空")
    private Long tableId;

    @ApiModelProperty("店铺ID")
    @NotNull(message = "店铺ID不能为空")
    private Long storeId;

    @ApiModelProperty("桌号")
    @NotBlank(message = "桌号不能为空")
    private String tableNumber;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态(0禁用，1启用)")
    @NotNull(message = "状态不能为空")
    private Character state;

}
