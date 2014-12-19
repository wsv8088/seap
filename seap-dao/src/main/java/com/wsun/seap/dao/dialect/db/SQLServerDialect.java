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
 * MSSQLServer 数据库实现分页方言
 * @author poplar.yfyang
 * @version 1.0 2010-10-10 下午12:31
 * @since JDK 1.5
 */
public class SQLServerDialect implements Dialect {

	static int getAfterSelectInsertPoint (String sql) {
		int selectIndex = sql.toLowerCase().indexOf("select");
		final int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
		return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
	}

	public String getLimitString (String sql, int offset, int limit) {
		return getLimit(sql, offset, limit);
	}

	/**
	 * 将sql变成分页sql语句,提供将offset及limit使用占位符号(placeholder)替换.
	 * <pre>
	 * 如mysql
	 * dialect.getLimitString("select * from user", 12, ":offset",0,":limit") 将返回
	 * select * from user limit :offset,:limit
	 * </pre>
	 *
	 * @param sql    实际SQL语句
	 * @param offset 分页开始纪录条数
	 * @param limit  分页每页显示纪录条数
	 * @return 包含占位符的分页sql
	 */
	public String getLimit (String sql, int offset, int limit) {
		if (offset > 0) {
			throw new UnsupportedOperationException("sql server has no offset");
		}
		return new StringBuffer(sql.length() + 8)
				.append(sql)
				.insert(getAfterSelectInsertPoint(sql), " top " + limit)
				.toString();
	}

	@Override
	public String getOrderString (String originalSql, LinkedHashMap<String, String> orderItems) {
		String ex = "";
		StringBuffer stringBuffer = new StringBuffer();
		if (orderItems != null && orderItems.size() > 0) {
			Iterator<Map.Entry<String, String>> it = orderItems.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				//ex = ex + "," + entry.getKey() + " " + entry.getValue();
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
