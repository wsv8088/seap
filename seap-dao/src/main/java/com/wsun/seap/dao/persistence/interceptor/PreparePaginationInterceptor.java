/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.wsun.seap.dao.persistence.interceptor;

import com.wsun.seap.common.utils.ReflectionsUtil;
import com.wsun.seap.dao.interceptor.BaseInterceptor;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;
import java.util.Properties;

/**
 * Mybatis数据库分页插件，拦截StatementHandler的prepare方法
 *
 * @author poplar.yfyang / thinkgem
 * @version 2013-8-28
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})
})
public class PreparePaginationInterceptor extends BaseInterceptor {

    private static final long serialVersionUID = 1L;

    public PreparePaginationInterceptor() {
        super();
    }

    @Override
    public Object intercept(Invocation ivk) throws Throwable {
        if (ivk.getTarget().getClass().isAssignableFrom(RoutingStatementHandler.class)) {
            final RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            final BaseStatementHandler delegate = (BaseStatementHandler) ReflectionsUtil.getFieldValue(statementHandler, DELEGATE);
            final MappedStatement mappedStatement = (MappedStatement) ReflectionsUtil.getFieldValue(delegate, MAPPED_STATEMENT);

            BoundSql boundSql = delegate.getBoundSql();
            //分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject == null) {
                log.error("参数未实例化");
                throw new NullPointerException("parameterObject尚未实例化！");
            } else {
                final Connection connection = (Connection) ivk.getArgs()[MAPPED_STATEMENT_INDEX];
                final String sql = boundSql.getSql();
                //记录统计
            }

            if (boundSql.getSql() == null || "".equals(boundSql.getSql())) {
                return null;
            }
        }
        return ivk.proceed();
    }


    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        initProperties(properties);
    }
}
