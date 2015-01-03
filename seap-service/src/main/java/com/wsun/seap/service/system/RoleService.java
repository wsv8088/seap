package com.wsun.seap.service.system;

import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.domain.po.system.Res;
import com.wsun.seap.domain.po.system.Role;

import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/16.
 */
public interface RoleService {
	public List<Role> queryRoleList(QueryParam param);

	public Page<Role> queryPageRoleList(QueryParam param);

	public List<Role> queryRoleByUsername(String loginName);
}
