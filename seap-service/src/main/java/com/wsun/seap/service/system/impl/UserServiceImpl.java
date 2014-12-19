package com.wsun.seap.service.system.impl;

import com.wsun.seap.dao.context.Page;
import com.wsun.seap.dao.context.QueryParam;
import com.wsun.seap.dao.orm.UserDao;
import com.wsun.seap.domain.po.system.User;
import com.wsun.seap.service.system.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/16.
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public Page<User> queryPageUserList (QueryParam param) {
		return userDao.queryPageUsers(param);
	}

	@Override
	public List<User> queryUserList (QueryParam param) {
		return userDao.queryAllUsers(param);
	}

	@Override
	public User queryUser (QueryParam param) {
		return userDao.queryUser(param);
	}
}
