package com.wsun.seap.web.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2014/12/14 0014.
 */
@Controller
public class IndexController {
	private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index () {
		String userName = (String) SecurityUtils.getSubject().getPrincipal();
		logger.info(userName + "登录成功，进入主页面=================>");

		return "modules/main";
	}

}
