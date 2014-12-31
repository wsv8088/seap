package com.wsun.seap.dao.orm.impl;

import com.wsun.seap.dao.common.MyBatisBaseDao;
import com.wsun.seap.dao.orm.ResourceDao;
import com.wsun.seap.domain.po.system.Resource;

import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/31.
 */
public class ResourceDaoImpl extends MyBatisBaseDao implements ResourceDao {

	@Override
	public List<Resource> queryAllResources () {
		return queryAll("queryAllResource", null);
	}

	@Override
	public List<Resource> queryResourcesByUsername (String loginName) {
		return queryAll("queryResourcesByUsername", loginName);
	}

	@Override
	protected String getNamespace () {
		return "System.ResourceMapper";
	}
}
