package com.wsun.seap.service.system.impl;

import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.dao.orm.ResourceDao;
import com.wsun.seap.domain.po.system.Res;
import com.wsun.seap.domain.po.system.User;
import com.wsun.seap.service.system.ResourceService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/1/3 0003.
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceDao resourceDao;

    @Override
    public List<Res> queryResourceList(QueryParam param) {
        return resourceDao.queryAllResources(param);
    }

    @Override
    public Page<Res> queryPageResourceList(QueryParam param) {
        return resourceDao.queryPageResources(param);
    }

    @Override
    @Cacheable(value = "cache", key = "'cache_user_role_' + #loginName")
    public List<Res> queryResourcesByUsername(String loginName) {
        return resourceDao.queryResourcesByUsername(loginName);
    }
}
