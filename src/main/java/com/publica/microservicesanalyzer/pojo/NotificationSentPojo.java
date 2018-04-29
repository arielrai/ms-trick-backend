package com.publica.microservicesanalyzer.pojo;

import java.util.Date;

public class NotificationSentPojo {

	private String deviceIds;
	private String message;
	private Date date;

	public String getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(String deviceIds) {
		this.deviceIds = deviceIds;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
