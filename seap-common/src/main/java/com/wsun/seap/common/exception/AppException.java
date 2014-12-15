package com.wsun.seap.common.exception;

/**
 * 自定义异常类：应用异常，任何业务异常都需要继承此类
 * Created by dbwangshuang on 2014/12/12.
 */
public class AppException extends RuntimeException {

	public AppException () {
		super();
	}

	public AppException (Throwable cause) {
		super(cause);
	}

	public AppException (String message, Throwable cause) {
		super(message, cause);
	}

	public AppException (String message) {
		super(message);
	}
}
