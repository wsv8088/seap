package com.wsun.seap.web.security.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义的默认realm实现
 * Created by wshuang on 2014/12/12.
 */
public class DefaultPermissionRealm extends AuthorizingRealm {

	/**
	 * 认证回调函数
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo (PrincipalCollection principalCollection) {
		return null;
	}



	@Override
	protected AuthenticationInfo doGetAuthenticationInfo (AuthenticationToken authenticationToken) throws AuthenticationException {
		return null;
	}
}
