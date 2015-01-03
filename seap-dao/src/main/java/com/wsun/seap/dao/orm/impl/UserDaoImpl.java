package com.wsun.seap.dao.orm.impl;

import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.dao.common.MyBatisBaseDao;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.dao.orm.UserDao;
import com.wsun.seap.domain.po.system.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/18.
 */
@Repository("userDao")
public class UserDaoImpl extends MyBatisBaseDao implements UserDao {
	@Override
	public List<User> queryAllUsers (QueryParam param) {
		return queryForList("queryUserList", param);
	}

	@Override
	public Page<User> queryPageUsers (QueryParam param) {
		return queryForPage("queryUserList", param);
	}

	@Override
	public User queryUser (QueryParam param) {
		return queryForObject("queryUser", param);
	}

	@Cacheable(value = "cache", key = "")
	@Override
	public User queryUserByLoginName(String loginName) {
		return queryForObject("queryUserByLoginName", loginName);
	}

	@Override
	protected String getNamespace () {
		return "System.UserMapper";
	}
}
