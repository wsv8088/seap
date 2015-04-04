package com.wsun.seap.web.controller.system;

import com.wsun.seap.common.context.JsonResult;
import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.domain.po.system.User;
import com.wsun.seap.service.system.UserService;
import com.wsun.seap.web.controller.BaseController;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.BufferedInputStream;

/**
 * Created by Administrator on 2014/12/14 0014.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserService userService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index (Model model) {
		return "modules/system/user/user_list";
	}


	@RequestMapping(value = "/user_list", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult user_list (QueryParam queryParam) {
		Page<User> page = userService.queryPageUserList(queryParam);
		return this.success(page);

	}

}