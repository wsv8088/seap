/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.wsun.seap.dao.interceptor;

import com.wsun.seap.common.utils.ReflectionsUtil;
import com.wsun.seap.dao.context.QueryParam;
import com.wsun.seap.dao.dialect.db.MySQLDialect;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.dao.dialect.Dialect;
import com.wsun.seap.dao.dialect.db.OracleDialect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.dozer.DozerBeanMapper;

import java.io.Serializable;
import java.util.Properties;

/**
 * Mybatis分页拦截器基类
 * @author wshuang
 * @version 2013-8-28
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {

	private static final long serialVersionUID = 1L;

	protected static final String PAGE = "page";

	protected static final String DELEGATE = "delegate";

	protected static final String MAPPED_STATEMENT = "mappedStatement";

	protected static int MAPPED_STATEMENT_INDEX = 0;

	protected static int PARAMETER_INDEX = 1;

	protected static int ROWBOUNDS_INDEX = 2;

	protected Log log = LogFactory.getLog(BaseInterceptor.class);

	protected Dialect dialect;

	/**
	 * 对参数进行转换和检查
	 *
	 * @param parameterObject 参数对象
	 * @return 分页对象
	 * @throws NoSuchFieldException 无法找到参数
	 */
	@SuppressWarnings("unchecked")
	protected static QueryParam convertParameter (Object parameterObject) {
		try {
			if (parameterObject instanceof QueryParam) {
				QueryParam qp = (QueryParam) parameterObject;
				return qp;
			} else {
				// 转换处理
				return new QueryParam();
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 设置属性，支持自定义方言类和制定数据库的方式
	 * <code>dialectClass</code>,自定义方言类。可以不配置这项
	 * <ode>dbms</ode> 数据库类型，插件支持的数据库
	 * <code>sqlPattern</code> 需要拦截的SQL ID
	 *
	 * @param p 属性
	 */
	protected void initProperties (Properties p) {
		Dialect dialect = null;
		String dbType = "mysql";
		if ("mysql".equals(dbType)) {
			dialect = new MySQLDialect();
		} else if ("oracle".equals(dbType)) {
			dialect = new OracleDialect();
		}
		if (dialect == null) {
			throw new RuntimeException("mybatis dialect error.");
		}
		this.dialect = dialect;
	}
}
