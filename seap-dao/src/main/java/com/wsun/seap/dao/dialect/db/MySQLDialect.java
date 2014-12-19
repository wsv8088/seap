/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.wsun.seap.dao.dialect.db;


import com.wsun.seap.dao.dialect.Dialect;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

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
	public String getLimitString (String sql, int offset, int limit) {
		StringBuilder stringBuilder = new StringBuilder(sql);
		stringBuilder.append(" limit ");
		if (offset > 0) {
			stringBuilder.append(offset).append(",").append(limit);
		} else {
			stringBuilder.append(limit);
		}
		return stringBuilder.toString();
	}

	@Override
	public String getOrderString (String originalSql, LinkedHashMap<String, String> orderItems) {
		String ex = "";
		StringBuffer stringBuffer = new StringBuffer();
		if (orderItems != null && orderItems.size() > 0) {
			Iterator<Map.Entry<String, String>> it = orderItems.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				stringBuffer.append(entry.getKey()).append(" ").append(entry.getValue());
			}
		}
		ex = String.valueOf(stringBuffer);
		if (ex.trim().length() > 0) {
			if (ex.startsWith(",")) {
				ex = ex.substring(1);
			}
			return originalSql + " order by " + ex;
		}

		return originalSql;
	}

}