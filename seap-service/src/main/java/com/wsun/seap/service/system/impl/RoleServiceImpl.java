package com.wsun.seap.service.system.impl;

import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.dao.orm.ResourceDao;
import com.wsun.seap.dao.orm.RoleDao;
import com.wsun.seap.domain.po.system.Res;
import com.wsun.seap.domain.po.system.Role;
import com.wsun.seap.service.system.ResourceService;
import com.wsun.seap.service.system.RoleService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/1/3 0003.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public List<Role> queryRoleList(QueryParam param) {
        return roleDao.queryAllRoles(param);
    }

    @Override
    public Page<Role> queryPageRoleList(QueryParam param) {
        return roleDao.queryPageRoles(param);
    }

    @Override
    @Cacheable(value = "cache", key = "'cache_user_resource_' + #loginName")
    public List<Role> queryRoleByUsername(String loginName) {
        return roleDao.queryRolesByUsername(loginName);
    }
}
