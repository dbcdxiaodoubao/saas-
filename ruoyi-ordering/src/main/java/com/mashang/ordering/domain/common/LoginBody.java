package com.mashang.ordering.domain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 邮箱验证码登录对象
 */
@ApiModel("邮箱验证码登录对象")
@Data
public class LoginBody {

    /**
     * QQ邮箱
     */
    @ApiModelProperty(value = "QQ邮箱", required = true)
    @NotBlank(message = "QQ邮箱不能为空")
    @Pattern(regexp = "^[1-9]\\d{4,10}@qq\\.com$", message = "必须是QQ邮箱")
    private String email;

    /**
     * 邮箱验证码
     */
    @ApiModelProperty(value = "邮箱验证码", required = true)
    @NotBlank(message = "验证码不能为空")
    private String code;

}
