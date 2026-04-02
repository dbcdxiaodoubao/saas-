package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("租户详情实体")
@Data
public class SysUserDtlVo {

    @ApiModelProperty("租户ID")
    private Long userId;

    @ApiModelProperty("用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    @ApiModelProperty("套餐ID")
    @NotNull(message = "套餐ID不能为空")
    private Long mealId;

    @ApiModelProperty("账号状态")
    @NotBlank(message = "账号不能为空")
    private String status;
    
    @ApiModelProperty("联系人")
    @NotBlank(message = "联系人不能为空")
    private String contact;

    @ApiModelProperty("联系人电话")
    @NotBlank(message = "联系电话不能为空")
    private String contactPhonenumber;

    @ApiModelProperty("账号额度")
    private String accountLimit;

    @ApiModelProperty("过期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expirationTime;

    @ApiModelProperty("绑定域名")
    private String bindDomain;

}
