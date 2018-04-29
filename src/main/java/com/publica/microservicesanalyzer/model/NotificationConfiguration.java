package com.publica.microservicesanalyzer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notification_configuration")
public class NotificationConfiguration {

	@Id
	@Column(name = "ds_notification_class")
	private String notificationClass;

	@Column(name = "fl_notification_on")
	private boolean notificationOn;


	public String getNotificationClass() {
		return notificationClass;
	}

	public void setNotificationClass(String notificationClass) {
		this.notificationClass = notificationClass;
	}

	public boolean isNotificationOn() {
		return notificationOn;
	}

	public void setNotificationOn(boolean notificationOn) {
		this.notificationOn = notificationOn;
	}
}
