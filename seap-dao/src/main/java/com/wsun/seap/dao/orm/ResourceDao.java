package com.wsun.seap.dao.orm;

import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.domain.po.system.Res;

import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/31.
 */
public interface ResourceDao {
	public List<Res> queryAllResources(QueryParam queryParam);

	public List<Res> queryResourcesByUsername(String loginName);

	public Page<Res> queryPageResources(QueryParam queryParam);

}
