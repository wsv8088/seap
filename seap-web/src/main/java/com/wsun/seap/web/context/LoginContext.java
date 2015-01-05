package com.wsun.seap.web.context;

import com.wsun.seap.common.utils.SpringContextHolder;
import com.wsun.seap.dao.orm.ResourceDao;
import com.wsun.seap.dao.orm.RoleDao;
import com.wsun.seap.dao.orm.UserDao;
import com.wsun.seap.domain.po.system.Res;
import com.wsun.seap.domain.po.system.Role;
import com.wsun.seap.domain.po.system.User;
import com.wsun.seap.web.security.realm.SystemAuthorizingRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wsun.seap.web.security.realm.SystemAuthorizingRealm.Principal;

/**
 * Created by Administrator on 2015/1/3 0003.
 */
public class LoginContext {
    /**
     * 与ehcache.xml中定义的缓存名称一致
     */
    private final static Logger logger = LoggerFactory.getLogger(LoginContext.class);

    private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);

    private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);

    private static ResourceDao resourceDao = SpringContextHolder.getBean(ResourceDao.class);

    private final static String CACHE_USER_KEY = "user";

    private static final String CACHE_ROLE_LIST_KEY = "roleList";

    private final static String CACHE_MENU_LIST_KEY = "menuList";

    public static User getUser() {
        User user = (User) getCache(CACHE_USER_KEY);
        if (user == null) {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();
            String loginName = principal.getLoginName();
            user = userDao.queryUserByLoginName(loginName);
            putCache(CACHE_USER_KEY, user);
        }
        return user;
    }

    public static List<Role> getRoleList() {
        List<Role> roles = (List<Role>) getCache(CACHE_ROLE_LIST_KEY);
        if (roles == null) {
            User user = getUser();
            roles = roleDao.queryRolesByUsername(user.getLoginName());
            putCache(CACHE_ROLE_LIST_KEY, roles);
        }
        return roles;
    }

    public static List<Res> getMenuList() {
        List<Res> menuList = (List<Res>) getCache(CACHE_MENU_LIST_KEY);
        if (menuList == null) {
            User user = getUser();
            menuList = resourceDao.queryResourcesByUsername(user.getLoginName());
            putCache(CACHE_MENU_LIST_KEY, menuList);
        }
        return menuList;
    }

    private static Object getCache(String key) {
        return getCacheMap().get(key);
    }

    private static void putCache(String key, Object value) {
        getCacheMap().put(key, value);
    }

    private static Map<String, Object> getCacheMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();
            return principal.getCacheMap();
        } catch (Exception e) {
            logger.error("Getting principal of current Shiro==>Subject Error!!!", e);
        }
        return map;
    }
}
