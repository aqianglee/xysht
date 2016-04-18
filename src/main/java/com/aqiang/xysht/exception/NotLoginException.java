package com.aqiang.xysht.exception;

public class NotLoginException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotLoginException() {

	}

	public NotLoginException(String message) {
		super(message);
	}
}
