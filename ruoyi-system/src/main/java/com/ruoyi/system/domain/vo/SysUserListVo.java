package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("分页查询租户信息列表")
@Data
public class SysUserListVo {

    @ApiModelProperty("租户ID")
    private String userId;

    @ApiModelProperty("租户名称")
    private String nickName;

    @ApiModelProperty("套餐ID")
    private String mealId;

    @ApiModelProperty("联系人")
    private String contact;

    @ApiModelProperty("联系人电话")
    private String contactPhonenumber;

    @ApiModelProperty("账号额度")
    private String accountLimit;

    @ApiModelProperty("过期时间")
    private Date expirationTime;

    @ApiModelProperty("绑定域名")
    private String bindDomain;

    @ApiModelProperty("租户状态")
    private String status;

}
