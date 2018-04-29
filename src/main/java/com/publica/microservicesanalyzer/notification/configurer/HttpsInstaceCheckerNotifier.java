package com.publica.microservicesanalyzer.notification.configurer;

import org.springframework.stereotype.Service;

import com.publica.microservicesanalyzer.mapping.app.Application;
import com.publica.microservicesanalyzer.notification.model.AbstractApplicationNotificationModel;

@Service
public class HttpsInstaceCheckerNotifier extends AbstractApplicationNotificationModel {

	@Override
	public boolean isNotify(Application model, String serviceName) {
		return false;
	}

	@Override
	public String getMessage(Application model, String serviceId, String serviceName) {
		return null;
	}

}
