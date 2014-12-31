/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.wsun.seap.dao.interceptor;

import com.wsun.seap.common.context.QueryParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库分页插件，只拦截查询语句.
 *
 * @author wshuang
 * @version 2013-8-28
 */
@Intercepts({ @Signature(type = Executor.class, method = "query",
		args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class PaginationInterceptor extends BaseInterceptor {

	private static Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);

	private static final long serialVersionUID = 1L;

	@Override
	public Object intercept (Invocation invocation) throws Throwable {
		// 获取映射的语句
		final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[MAPPED_STATEMENT_INDEX];
		// 拦截需要分页的SQL
		// 获取查询方法的参数
		Object parameterObj = invocation.getArgs()[PARAMETER_INDEX];
		// 判断参数类型是否为QueryParam
		Object parameter;
		if (parameterObj instanceof QueryParam) {
			// 如果传入的参数是自定义的QueryParam,须将参数重新设置为Map类型
			parameter = ((QueryParam) parameterObj).getAllParam();
			invocation.getArgs()[PARAMETER_INDEX] = parameter;
		} else {
			parameter = parameterObj;
		}

		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		if (StringUtils.isBlank(boundSql.getSql())) {
			return null;
		}
		// 获取分页参数对象RowBounds
		RowBounds rowBounds = (RowBounds) invocation.getArgs()[ROWBOUNDS_INDEX];
		// 如果设置了分页对象，则进行分页
		if (rowBounds != null && rowBounds != RowBounds.DEFAULT) {
			String originalSql = boundSql.getSql().trim();
			// 获取物理分页sql
			String pageSql = dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit());
			invocation.getArgs()[ROWBOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
			BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
			invocation.getArgs()[MAPPED_STATEMENT_INDEX] = newMs;
		}
		return invocation.proceed();
	}


	@Override
	public Object plugin (Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties (Properties properties) {
		super.initProperties(properties);
	}

	private MappedStatement copyFromMappedStatement (MappedStatement ms,
			SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
				ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null) {
			for (String keyProperty : ms.getKeyProperties()) {
				builder.keyProperty(keyProperty);
			}
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		return builder.build();
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource (BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql (Object parameterObject) {
			return boundSql;
		}
	}
}
