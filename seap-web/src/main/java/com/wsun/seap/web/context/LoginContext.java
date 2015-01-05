package com.wsun.seap.web.context;

import com.wsun.seap.domain.po.system.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/1/3 0003.
 */
public class LoginContext {
    /**
     * 与ehcache.xml中定义的缓存名称一致
     */

    @Resource
    private static Cache cache;

    public static User getUser() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (cache.get(username) == null) {

        }
        return null;
    }
}
