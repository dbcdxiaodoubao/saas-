package com.ruoyi.common.core.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("分页条件")
public class PageQuery {
    @ApiModelProperty(value = "页码",required = true)
    @NotNull(message = "页码不能为空")
    private Integer pageNum;

    @ApiModelProperty(value = "当前页大小",required = true)
    @NotNull(message = "当前页大小不能为空")
    private Integer pageSize;
}
