/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.wsun.seap.dao.context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 分页类
 *
 * @param <T>
 * @author ThinkGem
 * @version 2013-7-2
 */
public class Page<T> {

	private int page; // 当前页码

	private static final int DEFAULT_PAGE_SIZE = 10;

	private int pageSize; // 页面大小，设置为“-1”表示不进行分页（分页无效）

	private int total;// 总记录数，设置为“-1”表示不查询总数

	private int pageCount; // 总页数

	private List<T> rows;

	public Page () {
		this(0, DEFAULT_PAGE_SIZE, 0);
	}

	/**
	 * 构造方法
	 *
	 * @param page   当前页码
	 * @param pageSize 分页大小
	 */
	public Page (int page, int pageSize) {
		this(page, pageSize, 0);
	}

	/**
	 * 构造方法
	 *
	 * @param page   当前页码
	 * @param pageSize 分页大小
	 * @param total    数据条数
	 */
	public Page (int page, int pageSize, int total) {
		if (page <= 0) {
			page = 1;
		}
		if (pageSize <= 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		if (total < 0) {
			total = 0;
		}
		this.page = page;
		this.pageSize = pageSize;
		this.total = total;
		this.pageCount = (int) Math.ceil(total / pageSize);
		this.rows = new ArrayList<T>(pageSize);
	}

	public List<T> getRows () {
		return rows;
	}

	public void setRows (List<T> rows) {
		this.rows = rows;
	}

	public int getPageCount () {
		return pageCount;
	}

	public void setPageCount (int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotal () {
		return total;
	}

	public void setTotal (int total) {
		this.total = total;
	}

	public int getPage () {
		return page;
	}

	public void setPage (int page) {
		this.page = page;
	}

	/**
	 * 获取页面大小
	 *
	 * @return
	 */
	public int getPageSize () {
		return pageSize;
	}

	/**
	 * 设置页面大小（最大500）
	 *
	 * @param pageSize
	 */
	public void setPageSize (int pageSize) {
		this.pageSize = pageSize <= 0 ? 10 : pageSize;
	}

	public int getStartIndex () {
		return (page - 1) * pageSize + 1;
	}

	public int getEndIndex () {
		int end = page * pageSize;
		if (end > total) {
			end = total;
		}
		return end;
	}

}
