package com.wsun.seap.web.controller;

import com.wsun.seap.common.context.JsonResult;
import com.wsun.seap.common.utils.DateUtil;
import com.wsun.seap.dao.context.Page;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * Created by dbwangshuang on 2015/1/13.
 */
public class BaseController {
	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);


	public JsonResult success (Page page) {
		JsonResult result = new JsonResult();
		result.setRows(page.getRows());
		result.setRecords(page.getTotal());
		result.setTotal(page.getPageCount());
		return result;
	}

	public JsonResult success (List list) {
		JsonResult result = new JsonResult();
		result.setRows(list);
		result.setRecords(list.size());
		result.setTotal(1);
		return result;
	}

	public JsonResult success (String message) {
		JsonResult result = new JsonResult();
		result.setSuccess(true);
		result.setMessage(message);
		return result;
	}

	public JsonResult fail (String message) {
		JsonResult result = new JsonResult();
		result.setMessage(message);
		return result;
	}

	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder (WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText (String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText () {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText (String text) {
				setValue(DateUtil.parseDate(text));
			}
		});
	}
}
