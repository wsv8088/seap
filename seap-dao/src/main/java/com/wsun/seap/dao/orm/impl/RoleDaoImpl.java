package com.wsun.seap.dao.orm.impl;

import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.dao.common.MyBatisBaseDao;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.dao.orm.RoleDao;
import com.wsun.seap.domain.po.system.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/1/3 0003.
 */
@Repository("roleDao")
public class RoleDaoImpl extends MyBatisBaseDao implements RoleDao {
    @Override
    public List<Role> queryAllRoles(QueryParam queryParam) {
        return queryForList("queryRoleList", queryParam);
    }

    @Override
    public List<Role> queryRolesByUsername(String loginName) {
        return queryForList("queryRoleListByUsername", loginName);
    }

    @Override
    public Page<Role> queryPageRoles(QueryParam queryParam) {
        return queryForPage("queryRoleList", queryParam);
    }

    @Override
    protected String getNamespace() {
        return "System.RoleMapper";
    }
}
