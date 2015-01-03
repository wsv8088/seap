package com.wsun.seap.web.controller;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2014/12/14 0014.
 */
@Controller
public class LoginController {
	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index () {
		logger.info("<=================访问首页=================>");
		return "modules/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail (@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		logger.info("<=================未成功登录重新返回登录页=================>");
		return "modules/login";
	}

}
