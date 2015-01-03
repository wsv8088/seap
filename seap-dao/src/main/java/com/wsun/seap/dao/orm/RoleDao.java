package com.wsun.seap.dao.orm;

import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.domain.po.system.Res;
import com.wsun.seap.domain.po.system.Role;

import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/31.
 */
public interface RoleDao {
	public List<Role> queryAllRoles(QueryParam queryParam);

	public List<Role> queryRolesByUsername(String loginName);

	public Page<Role> queryPageRoles(QueryParam queryParam);

}
