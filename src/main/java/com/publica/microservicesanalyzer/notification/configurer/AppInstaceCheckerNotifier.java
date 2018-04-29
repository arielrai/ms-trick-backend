package com.publica.microservicesanalyzer.notification.configurer;

import org.springframework.stereotype.Service;

import com.publica.microservicesanalyzer.mapping.app.Application;
import com.publica.microservicesanalyzer.notification.model.AbstractApplicationNotificationModel;

@Service
public class AppInstaceCheckerNotifier extends AbstractApplicationNotificationModel {

	@Override
	public boolean isNotify(Application model, String serviceName) {
		return model.getInstance().size() < 2 && !serviceName.equals("EUREKA-SERVER");
	}

	@Override
	public String getMessage(Application model, String serviceId, String serviceName) {
		return String.format("Há apenas uma instância de %s executando", serviceName);
	}

}
