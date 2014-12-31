/* 
 * Copyright (c) 2013, 360buy Group and/or its affiliates. All rights reserved.
 */
package com.wsun.seap.common.context;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;


/**
 * 将WebRequest中参数设置到QueryParam中
 * 
 * @version 2013-6-18
 * @author daichen
 */
public class QueryParamMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType() == QueryParam.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		Map<String, String[]> parameterMap = webRequest.getParameterMap();
		QueryParam param = new QueryParam();
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			if (entry.getValue().length > 0) {
				param.addParam(entry.getKey(), entry.getValue()[0]);
			}
		}
		return param;
	}

}
