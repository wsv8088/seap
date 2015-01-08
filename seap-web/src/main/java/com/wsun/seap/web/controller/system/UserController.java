package com.wsun.seap.web.controller.system;

import com.wsun.seap.domain.po.system.Res;
import com.wsun.seap.domain.po.system.User;
import com.wsun.seap.service.system.ResourceService;
import com.wsun.seap.service.system.RoleService;
import com.wsun.seap.service.system.UserService;
import com.wsun.seap.web.context.LoginContext;
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
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;


    @RequestMapping(value = "/system/user", method = RequestMethod.GET)
    public String index(Model model) {
        return "modules/main";
    }

}
