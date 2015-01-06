package com.wsun.seap.web.controller;

import com.wsun.seap.domain.po.system.Res;
import com.wsun.seap.domain.po.system.Role;
import com.wsun.seap.domain.po.system.User;
import com.wsun.seap.service.system.ResourceService;
import com.wsun.seap.service.system.RoleService;
import com.wsun.seap.service.system.UserService;
import com.wsun.seap.web.context.LoginContext;
import com.wsun.seap.web.security.realm.SystemAuthorizingRealm;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2014/12/14 0014.
 */
@Controller
public class IndexController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private UserService userService;

    @Resource
    private ResourceService resourceService;

    @Resource
    private RoleService roleService;

    @Resource
    private CacheManager cacheManager;

    @RequestMapping(value = "/index/head", method = RequestMethod.POST)
    @ResponseBody
    public User queryRole(Model model) {
        User currentUser = LoginContext.getUser();
        String username = currentUser.getLoginName();
        logger.info(username + "获取当前用户基本信息,信息用于header显示=================>");
        return currentUser;
    }

    @RequestMapping(value = "/index/menu", method = RequestMethod.POST)
    @ResponseBody
    public List<Res> queryMenuList(Model model) {
        List<Res> resList = LoginContext.getMenuList();
        logger.info("读取当前用户的权限菜单,共" + resList.size() + "条");
        return resList;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        return "modules/main";
    }

}
