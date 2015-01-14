package com.wsun.seap.common.context;

import java.util.List;
import java.util.Map;

/**
 * Created by dbwangshuang on 2015/1/13.
 */
public class JsonResult {
	//需要显示的数据集
	private List<?> rows;

	//每页显示数量
	private int page;

	//数据总数
	private int records;

	//可显示的页数
	private int total;

	// 是否处理成功
	private boolean success;

	// 成功/失败的提示消息
	private String message;

	//自定义数据
	private Map<?, ?> map;

	public List<?> getRows () {
		return rows;
	}

	public void setRows (List<?> rows) {
		this.rows = rows;
	}

	public int getPage () {
		return page;
	}

	public void setPage (int page) {
		this.page = page;
	}

	public int getRecords () {
		return records;
	}

	public void setRecords (int records) {
		this.records = records;
	}

	public int getTotal () {
		return total;
	}

	public void setTotal (int total) {
		this.total = total;
	}

	public boolean isSuccess () {
		return success;
	}

	public void setSuccess (boolean success) {
		this.success = success;
	}

	public String getMessage () {
		return message;
	}

	public void setMessage (String message) {
		this.message = message;
	}

	public Map<?, ?> getMap () {
		return map;
	}

	public void setMap (Map<?, ?> map) {
		this.map = map;
	}
}
