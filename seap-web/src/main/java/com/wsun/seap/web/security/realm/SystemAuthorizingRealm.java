/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.wsun.seap.web.security.realm;

import com.wsun.seap.common.constant.SystemConst;
import com.wsun.seap.domain.po.system.Res;
import com.wsun.seap.domain.po.system.Role;
import com.wsun.seap.domain.po.system.User;
import com.wsun.seap.service.system.ResourceService;
import com.wsun.seap.service.system.RoleService;
import com.wsun.seap.service.system.UserService;
import com.wsun.seap.web.security.token.UsernamePasswordToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private ResourceService resourceService;

    @Resource
    private RoleService roleService;

    @Resource
    private CacheManager cacheManager;

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 先获取登录的用户名(身份)
        // 因为在登录的时候构建的是自定义的token,因此此处可转换为对应的类
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        User user = userService.queryUserByLoginName(username);
        if (user != null) {
            return new SimpleAuthenticationInfo(new Principal(user), user.getPassword(), getName());
        } else {
            logger.error(username + "在系统中不存在！");
            throw new UnknownAccountException("该用户在系统中不存在！");
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 查询该用户所拥有的资源
        List<Res> resources = resourceService.queryResourcesByUsername(username);
        for (Res res : resources) {
            info.addStringPermission(res.getPermission());
        }

        // 查询该用户所拥有的角色
        List<Role> roles = roleService.queryRoleByUsername(username);
        for (Role role : roles) {
            info.addRole(role.getName());
        }

        return info;
    }

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(SystemConst.DEFAULT_HASH_ALGORITHM);
        matcher.setHashIterations(SystemConst.DEFAULT_HASH_INTERATIONS);
        // setCredentialsMatcher(matcher);
    }

    public static class Principal implements Serializable {

        private Long id;

        private String loginName;

        private String realName;

        private Map<String, Object> cacheMap;

        public Principal(User user) {
            this.id = user.getId();
            this.loginName = user.getLoginName();
            this.realName = user.getRealName();
        }

        public Long getId() {
            return id;
        }

        public String getLoginName() {
            return loginName;
        }

        public String getRealName() {
            return realName;
        }

        public Map<String, Object> getCacheMap() {
            if (cacheMap == null) {
                cacheMap = new HashMap<String, Object>();
            }
            return cacheMap;
        }
    }

}


