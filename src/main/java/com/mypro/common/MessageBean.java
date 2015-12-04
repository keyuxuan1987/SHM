package com.mypro.common;

public class MessageBean {

	private String code;
	
	private String ret;
	
	public MessageBean() {
	}

	public MessageBean(String code, String ret) {
		super();
		this.code = code;
		this.ret = ret;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}
	
}
