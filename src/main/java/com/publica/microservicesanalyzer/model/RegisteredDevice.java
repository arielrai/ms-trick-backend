package com.publica.microservicesanalyzer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device")
public class RegisteredDevice {

	@Id
	@Column(name="id_device")
	private String deviceId;
	
	@Column(name="fl_receive_notification")
	private boolean receiveNotification; 

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public boolean isReceiveNotification() {
		return receiveNotification;
	}

	public void setReceiveNotification(boolean receiveNotification) {
		this.receiveNotification = receiveNotification;
	}

}
