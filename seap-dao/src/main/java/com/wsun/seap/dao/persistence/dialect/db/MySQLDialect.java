/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.wsun.seap.dao.persistence.dialect.db;


import com.wsun.seap.dao.persistence.dialect.Dialect;

/**
 * Mysql方言的实现
 *
 * @author poplar.yfyang
 * @version 1.0 2010-10-10 下午12:31
 * @since JDK 1.5
 */
public class MySQLDialect implements Dialect {


    /**
     * 获取mysql的分页查询SQL
     * @param sql    实际SQL语句
     * @param offset 分页开始纪录条数
     * @return 包含占位符的分页sql
     */
    @Override
    public String getLimitString(String sql, int offset, int limit) {
        StringBuilder stringBuilder = new StringBuilder(sql);
        stringBuilder.append(" limit ");
        if (offset > 0) {
            stringBuilder.append(offset).append(",").append(limit);
        } else {
            stringBuilder.append(limit);
        }
        return stringBuilder.toString();
    }

    public boolean supportsLimit() {
        return true;
    }

}