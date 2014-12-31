package com.wsun.seap.dao.orm;

import com.wsun.seap.domain.po.system.Resource;

import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/31.
 */
public interface ResourceDao {
	public List<Resource> queryAllResources();

	public List<Resource> queryResourcesByUsername(String loginName);
}
