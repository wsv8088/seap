package com.wsun.seap.web.controller;

import com.wsun.seap.domain.po.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2014/12/14 0014.
 */
@Controller
public class LoginController {
	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index () {
		logger.debug(SecurityUtils.getSubject().getPrincipal() + "访问首页=================>");
		return "modules/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login (User user, Model model) {
		System.out.println("==================>");
		return "modules/login";
	}

	/**
	 * 未认证需要跳转的入口
	 * @return
	 */
	@RequestMapping(value = "/unauthenticated", method = RequestMethod.GET)
	public String unauthenticated () {
		logger.info("未认证=========================");
		return "error/nopermission";
	}
}
