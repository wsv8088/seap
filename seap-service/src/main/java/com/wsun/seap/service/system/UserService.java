package com.wsun.seap.service.system;

import com.wsun.seap.dao.context.Page;
import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.domain.po.system.User;

import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/16.
 */
public interface UserService {
	public List<User> queryUserList(QueryParam param);

	public Page<User> queryPageUserList(QueryParam param);

	public User queryUserByLoginName(String loginName);
}
