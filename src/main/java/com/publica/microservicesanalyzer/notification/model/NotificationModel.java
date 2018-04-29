package com.publica.microservicesanalyzer.notification.model;

public interface NotificationModel<T> {

	boolean isNotify(T model, String serviceName);
	
	String getMessage(T model, String serviceId, String serviceName);
}
