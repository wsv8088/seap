package com.wsun.seap.web.controller;

import com.wsun.seap.domain.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2014/12/14 0014.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user) {

        String username = user.getName();
        String password = user.getPassword();
        System.out.println(username + "===>" + password);

        return "modules/main";
    }
}
