package com.wsun.seap.web.controller;

import com.wsun.seap.domain.po.system.Res;
import com.wsun.seap.domain.po.system.Role;
import com.wsun.seap.domain.po.system.User;
import com.wsun.seap.service.system.ResourceService;
import com.wsun.seap.service.system.RoleService;
import com.wsun.seap.service.system.UserService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        logger.info(username + "登录成功，进入主页面=================>");
        User user = userService.queryUserByLoginName(username);
        model.addAttribute("userinfo", user);

        List<Res> resources = resourceService.queryResourcesByUsername(username);
        model.addAttribute("reslist", resources);

        List<Role> roles = roleService.queryRoleByUsername(username);
        model.addAttribute("roles", roles);

        return "modules/main";
    }

}
