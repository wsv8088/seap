package com.wsun.seap.dao.orm.impl;

import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.dao.common.MyBatisBaseDao;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.dao.orm.ResourceDao;
import com.wsun.seap.domain.po.system.Res;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/31.
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends MyBatisBaseDao implements ResourceDao {

    @Override
    public List<Res> queryAllResources(QueryParam queryParam) {
        return queryForList("queryResourceList", queryParam);
    }

    @Override
    public Page<Res> queryPageResources(QueryParam queryParam) {
        return queryForPage("queryResourceList", queryParam);
    }


    @Override
    public List<Res> queryResourcesByUsername(String loginName) {
        return queryForList("queryResourceListByUsername", loginName);
    }


    @Override
    protected String getNamespace() {
        return "System.ResourceMapper";
    }
}
