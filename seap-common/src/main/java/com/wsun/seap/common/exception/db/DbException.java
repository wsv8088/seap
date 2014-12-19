package com.wsun.seap.common.exception.db;


import com.wsun.seap.common.exception.AppException;

@SuppressWarnings("serial")
public class DbException extends AppException {

	public DbException () {
		super();
	}

	public DbException (String message) {
		super(message);
	}

	public DbException (String message, Throwable cause) {
		super(message, cause);
	}

	public DbException (Throwable cause) {
		super(cause);
	}


}
