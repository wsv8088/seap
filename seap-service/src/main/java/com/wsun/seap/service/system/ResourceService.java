package com.wsun.seap.service.system;

import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.domain.po.system.Res;

import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/16.
 */
public interface ResourceService {
	public List<Res> queryResourceList(QueryParam param);

	public Page<Res> queryPageResourceList(QueryParam param);

	public List<Res> queryResourcesByUsername(String loginName);
}
