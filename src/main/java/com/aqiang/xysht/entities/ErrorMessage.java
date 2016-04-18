package com.aqiang.xysht.entities;

public class ErrorMessage {

	private String key;
	private Object[] args;

	public ErrorMessage(String key) {
		this(key, new Object[] {});
	}

	public ErrorMessage(String key, Object[] args) {
		this.key = key;
		this.args = args;
	}

	public String getKey() {
		return key;
	}

	public Object[] getArgs() {
		return args;
	}
}
