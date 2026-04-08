package com.mashang.ordering.domain.param.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("批量新增桌号实体")
@Data
public class MsTableBatchCreate {

    @ApiModelProperty("店铺ID")
    @NotNull(message = "店铺ID不能为空")
    private Long storeId;

    @ApiModelProperty("桌号前缀")
    @NotNull(message = "桌号前缀不能为空")
    private String tableNumberPrefix;

    @ApiModelProperty("桌号起始值")
    @NotNull(message = "桌号起始值不能为空")
    private Integer startTableNumber;

    @ApiModelProperty("桌号结束值")
    @NotNull(message = "桌号结束值不能为空")
    private Integer endTableNumber;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态")
    @NotNull(message = "状态不能为空")
    private Character state;
}
