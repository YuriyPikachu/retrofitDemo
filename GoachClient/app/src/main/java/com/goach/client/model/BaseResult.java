package com.goach.client.model;

import java.util.Date;

public class BaseResult {
	private int resultCode;
	private Date date;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
