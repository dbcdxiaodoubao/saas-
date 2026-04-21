package com.mashang.ordering.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mashang.ordering.domain.common.LoginBody;
import com.mashang.ordering.domain.common.ResultSet;
import com.ruoyi.common.core.domain.entity.SysUser;

public interface IMsUserLoginService extends IService<SysUser> {

    ResultSet<String> send(String email);

    ResultSet<String> login(LoginBody loginBody);

}
