package com.wsun.seap.service.system.impl;

import com.wsun.seap.dao.persistence.orm.Page;
import com.wsun.seap.dao.persistence.orm.UserDao;
import com.wsun.seap.domain.po.User;
import com.wsun.seap.service.system.UserService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/16.
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao<User> userDao;

	@Override
	public List<User> queryPageUserList (User user) {
		return userDao.queryUserList(user, new Page());
	}

	@Override
	public List<User> queryUserList (User user) {
		return userDao.queryUserList(user, null);
	}

	@Override
	public User queryUser (User user) {
		return userDao.queryUser(user);
	}
}
