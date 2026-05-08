package com.mashang.ordering.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("租户菜单子级数据列表")
@Data
public class MsMenuChildListVo {

    @ApiModelProperty("子级菜单id")
    private Long menuId;

    @ApiModelProperty("子级菜单名称")
    private String menuName;

}
