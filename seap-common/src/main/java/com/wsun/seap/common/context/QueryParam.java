/* 
 * Copyright (c) 2013, 360buy Group and/or its affiliates. All rights reserved.
 */
package com.wsun.seap.common.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 携带分页查询条件的参数类 
 * 
 * @version 2013-6-19
 * @author daichen
 */
public class QueryParam {
	
	private static final String PAGE_NO_KEY = "page";
	private static final String PAGE_SIZE_KEY = "rows";
	
	private Map<String, Object> paramMap = new HashMap<String, Object>();
	
	public void addParam(String key, Object value) {
		paramMap.put(key, value);
	}
	
	public Object getParam(String key) {
		return paramMap.get(key);
	}
	
	public int getInt(String key) {
		if(null == paramMap.get(key)){
			return 0;
		}
		return Integer.parseInt(String.valueOf(paramMap.get(key)));
	}
	
	public long getLong(String key) {
		if(null == paramMap.get(key)){
			return 0;
		}
		return Long.parseLong(String.valueOf(paramMap.get(key)));
	}
	
	public Map<String, Object> getAllParam() {
		return paramMap;
	}
	
	public int getPageNo() {
		if(null == getParam(PAGE_NO_KEY)){
			return 0;
		}
		return Integer.parseInt(String.valueOf(getParam(PAGE_NO_KEY)));
	}
	
	public int getPageSize() {
		if(null == getParam(PAGE_SIZE_KEY)){
			return 10;
		}
		return Integer.parseInt(String.valueOf(getParam(PAGE_SIZE_KEY)));
	}
	
	public Map<String, Object> getParamMap() {
		return paramMap;
	}
}
