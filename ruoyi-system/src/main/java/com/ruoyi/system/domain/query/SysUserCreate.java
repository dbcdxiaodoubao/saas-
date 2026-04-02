package com.ruoyi.system.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("租户添加实体")
@Data
public class SysUserCreate {

    @ApiModelProperty("用户账号")
    @NotBlank(message = "用户账号不能为空")
    private String userName;

    @ApiModelProperty("用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    @ApiModelProperty("角色ID")
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @ApiModelProperty("套餐ID")
    @NotNull(message = "套餐ID不能为空")
    private Long mealId;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("账号状态")
    @NotBlank(message = "账号不能为空")
    private String status;

    @ApiModelProperty("用户性别")
    @NotBlank(message = "用户性别不能为空")
    private String sex;

    @ApiModelProperty("生日")
    @NotBlank(message = "生日不能为空")
    private Date birthday;

    @ApiModelProperty("地区")
    private String region;

    @ApiModelProperty("学历")
    private String educationalBackground;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phonenumber;

    @ApiModelProperty("个人简介")
    private String personalProfile;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("联系人")
    @NotBlank(message = "联系人不能为空")
    private String contact;

    @ApiModelProperty("联系人电话")
    @NotBlank(message = "联系电话不能为空")
    private String contactPhonenumber;

    @ApiModelProperty("账号额度")
    private String accountLimit;

    @ApiModelProperty("过期时间")
    private Date expirationTime;

    @ApiModelProperty("绑定域名")
    private String bindDomain;

}
