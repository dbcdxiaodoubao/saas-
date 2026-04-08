package com.mashang.ordering.domain.param.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("新增桌号实体")
@Data
public class MsTableCreate {

    @ApiModelProperty("店铺ID")
    @NotNull(message = "店铺ID不能为空")
    private Long storeId;

    @ApiModelProperty("桌号")
    @NotNull(message = "桌号不能为空")
    private String tableNumber;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态")
    @NotNull(message = "状态不能为空")
    private Character state;
}
