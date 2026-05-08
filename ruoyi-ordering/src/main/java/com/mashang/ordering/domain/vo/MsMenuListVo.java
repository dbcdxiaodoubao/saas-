package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("租户菜单父级数据列表")
@Data
public class MsMenuListVo {

    @ApiModelProperty("父级菜单id")
    private Long menuId;

    @ApiModelProperty("父级菜单名称")
    private String menuName;

    @ApiModelProperty("租户菜单子级数据列表")
    private List<MsMenuChildListVo> msMenuChildListVo;
}
