package com.ruoyi.system.domain.query;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@ApiModel("租户信息检索条件")
@Data
public class SysUserQuery {

    @ApiModelProperty("租户名称")
    private String nickName;

    @ApiModelProperty("联系人")
    private String contact;

    @ApiModelProperty("联系人电话")
    private String contactPhonenumber;

    @ApiModelProperty("租户状态")
    private String status;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

   /* @ApiModelProperty(value = "角色ID",required = true)
    @NotBlank(message = "角色ID不能为空")
    private Long roleId;*/

}
