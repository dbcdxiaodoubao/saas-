package com.mashang.ordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.ordering.domain.common.LoginBody;
import com.mashang.ordering.domain.common.ResultSet;
import com.mashang.ordering.mapper.MsUserLoginMapper;
import com.mashang.ordering.service.IMsUserLoginService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MsUserLoginServiceImpl extends ServiceImpl<MsUserLoginMapper, SysUser> implements IMsUserLoginService {

    /** 验证码有效期（分钟） */
    private static final int CODE_EXPIRATION = 5;

    /** Redis中验证码key前缀 */
    private static final String EMAIL_CODE_KEY = "email_code:";

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MsUserLoginMapper msUserLoginMapper;

    @Autowired
    private OrderingTokenService tokenService;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public ResultSet<String> send(String email) {
        // 生成6位数字验证码
        String code = generateCode();

        // 发送QQ邮箱验证码
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(fromEmail);
            mailMessage.setSubject("码上SaaS验证码邮件");
            mailMessage.setText("您的登录验证码为：" + code + "，" + CODE_EXPIRATION + "分钟内有效，请勿泄露给他人。");
            mailMessage.setTo(email);
            mailSender.send(mailMessage);
        } catch (Exception e) {
            log.error("发送验证码邮件失败，邮箱：{}，原因：{}", email, e.getMessage());
            return ResultSet.fail("发送验证码失败，请检查邮箱地址是否正确");
        }

        // 将验证码存入Redis，以邮箱为key，有效期5分钟
        redisCache.setCacheObject(EMAIL_CODE_KEY + email, code, CODE_EXPIRATION, TimeUnit.MINUTES);
        return ResultSet.success(null, "验证码发送成功，请在" + CODE_EXPIRATION + "分钟内使用");
    }

    @Override
    public ResultSet<String> login(LoginBody loginBody) {
        String email = loginBody.getEmail();
        String inputCode = loginBody.getCode();

        // 1. 校验验证码
        String cachedCode = redisCache.getCacheObject(EMAIL_CODE_KEY + email);
        if (StringUtils.isEmpty(cachedCode)) {
            throw new CaptchaExpireException();
        }
        // 验证通过或失败都删除Redis中的验证码，防止重复使用
        redisCache.deleteObject(EMAIL_CODE_KEY + email);

        //在忽略大小写情况下验证码是否相等
        if (!inputCode.equalsIgnoreCase(cachedCode)) {
            throw new CaptchaException();
        }

        // 2. 查询该邮箱用户是否已注册
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getEmail, email);
        SysUser sysUser = msUserLoginMapper.selectOne(queryWrapper);

        // 3. 未注册则自动注册
        if (sysUser == null) {
            sysUser = new SysUser();
            // 取邮箱@前部分作为用户名，若重复则追加随机后缀
            String baseUserName = email.split("@")[0];
            sysUser.setUserName(generateUniqueUserName(baseUserName));
            sysUser.setNickName(baseUserName);
            sysUser.setEmail(email);
            // 设置一个随机密码（邮箱验证码登录无需密码）
            sysUser.setPassword(SecurityUtils.encryptPassword(email + System.currentTimeMillis()));
            sysUser.setUserType("00"); // 00表示系统用户
            sysUser.setStatus("0");   // 0正常
            sysUser.setCreateBy(email);
            sysUser.setRemark("用户");
            sysUser.setAccountLimit("0");
            int rows = msUserLoginMapper.insert(sysUser);
            if (rows < 1) {
                return ResultSet.fail("添加用户失败，自动注册失败，请稍后重试");
            }
            //设置用户角色为租户
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(100L);
            int insert = sysUserRoleMapper.insert(sysUserRole);
            if (insert < 1) {
                return ResultSet.fail("设置用户角色失败，自动注册失败，请稍后重试");
            }
        }

        // 4. 构造LoginUser并生成JWT Token
        LoginUser loginUser = new LoginUser(sysUser.getUserId(), sysUser.getDeptId(), sysUser, Collections.emptySet());
        String token = tokenService.createToken(loginUser);

        return ResultSet.success(token, "登录成功");
    }

    /**
     * 生成6位随机数字验证码
     */
    private String generateCode() {
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        return String.valueOf(code);
    }

    /**
     * 生成不重复的用户名
     */
    private String generateUniqueUserName(String baseUserName) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName, baseUserName);
        Long count = msUserLoginMapper.selectCount(queryWrapper);
        //没有重名直接使用
        if (count == 0) {
            return baseUserName;
        }
        // 加随机四位后缀避免重名
        return baseUserName + "_" + (int) (Math.random() * 9000 + 1000);
    }
}
