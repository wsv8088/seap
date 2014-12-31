package com.wsun.seap.dao.orm;


import com.wsun.seap.dao.context.Page;
import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.domain.po.system.User;

import java.util.List;

/**
 * Created by Administrator on 2014/10/5 0005.
 */
public interface UserDao {
	public List<User> queryAllUsers (QueryParam param);

	public Page<User> queryPageUsers (QueryParam param);

	public User queryUser (QueryParam param);

	public User queryUserByLoginName(String loginName);
}
