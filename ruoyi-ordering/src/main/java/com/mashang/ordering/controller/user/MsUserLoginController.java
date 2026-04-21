package com.mashang.ordering.controller.user;

import com.mashang.ordering.domain.common.LoginBody;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.service.IMsUserLoginService;
import com.ruoyi.common.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@Validated
@RequestMapping("/user")
@Api(tags = "Saas用户登录")
public class MsUserLoginController {

    @Autowired
    private IMsUserLoginService msUserLoginService;

    /**
     * 发送QQ邮箱验证码（无需登录）
     */
    @Anonymous
    @ApiOperation("发送验证码")
    @GetMapping("/sendCaptcha")
    public ResultSet<String> sendCaptcha(
            @RequestParam
            @Pattern(regexp = "^[1-9]\\d{4,10}@qq\\.com$", message = "必须是QQ邮箱")
            String email) {
        return msUserLoginService.send(email);
    }

    /**
     * 邮箱验证码登录（验证码正确则自动注册并返回token，无需登录）
     */
    @Anonymous
    @ApiOperation("邮箱验证码登录")
    @PostMapping("/login")
    public ResultSet<String> login(@RequestBody @Valid LoginBody loginBody) {
        return msUserLoginService.login(loginBody);
    }
}
