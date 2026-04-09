package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("桌号列表")
public class MsUserTableListVo {

    @ApiModelProperty("桌号id")
    private Long tableId;
    @ApiModelProperty("桌号")
    private String tableName;
}
