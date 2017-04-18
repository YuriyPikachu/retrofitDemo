package com.goach.web.vo;

import java.util.Date;

public class BaseResult<D> {
	private int resultCode;
	private Date responseTime;
	private D data;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	public D getData() {
		return data;
	}
	public void setData(D data) {
		this.data = data;
	}
	

	
}
