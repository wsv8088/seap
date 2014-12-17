/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.wsun.seap.web.security.realm;

import com.wsun.seap.common.constant.SystemConst;
import com.wsun.seap.domain.po.User;
import com.wsun.seap.service.system.UserService;
import com.wsun.seap.web.security.token.UsernamePasswordToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 系统安全认证实现类
 *
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {
	private final static Logger logger = LoggerFactory.getLogger(SystemAuthorizingRealm.class);

	@Resource
	private UserService userService;

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo (AuthenticationToken authenticationToken) throws AuthenticationException {
		// 先获取登录的用户名(身份)
		// 因为在登录的时候构建的是自定义的token,因此此处可转换为对应的类
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		User param = new User();
		param.setLoginName(username);
		User user = userService.queryUser(param);
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());
		} else {
			logger.error(username + "在系统中不存在！");
			throw new UnknownAccountException("该用户在系统中不存在！");
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo (PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();

		return null;
	}

	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher () {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(SystemConst.DEFAULT_HASH_ALGORITHM);
		matcher.setHashIterations(SystemConst.DEFAULT_HASH_INTERATIONS);
		// setCredentialsMatcher(matcher);
	}


}


