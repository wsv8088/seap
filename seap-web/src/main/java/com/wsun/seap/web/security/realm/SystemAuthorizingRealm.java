/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.wsun.seap.web.security.realm;

import com.wsun.seap.common.constant.SystemConst;
import com.wsun.seap.domain.po.User;
import com.wsun.seap.web.security.token.UsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 系统安全认证实现类
 *
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		if (LoginController.isValidateCodeLogin(token.getUsername(), false, false)) {
			// 判断验证码
			Session session = SecurityUtils.getSubject().getSession();
			String code = (String) session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
			if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)) {
				throw new CaptchaException("验证码错误.");
			}
		}

		User user = getSystemService().getUserByLoginName(token.getUsername());
		if (user != null) {
			byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
			return new SimpleAuthenticationInfo(new Principal(user),
					user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}*/
        // 先获取登录的用户名(身份)
        // 因为在登录的时候构建的是自定义的token,因此此处可转换为对应的类
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = null;
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
        // 设置盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return null;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*Principal principal = (Principal) getAvailablePrincipal(principals);
        User user = getSystemService().getUserByLoginName(principal.getLoginName());
        if (user != null) {
            UserUtils.putCache("user", user);
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Menu> list = UserUtils.getMenuList();
            for (Menu menu : list) {
                if (StringUtils.isNotBlank(menu.getPermission())) {
                    // 添加基于Permission的权限信息
                    for (String permission : StringUtils.split(menu.getPermission(), ",")) {
                        info.addStringPermission(permission);
                    }
                }
            }
            // 更新登录IP和时间
            getSystemService().updateUserLoginInfo(user.getId());
            return info;
        } else {
            return null;
        }*/
        return null;
    }

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(SystemConst.DEFAULT_HASH_ALGORITHM);
        matcher.setHashIterations(SystemConst.DEFAULT_HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }




}
