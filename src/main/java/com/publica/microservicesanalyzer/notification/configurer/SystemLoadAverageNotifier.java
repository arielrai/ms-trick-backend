package com.publica.microservicesanalyzer.notification.configurer;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.publica.microservicesanalyzer.notification.model.AbstractMetricsNotificationModel;

@Service
public class SystemLoadAverageNotifier extends AbstractMetricsNotificationModel {

	@Override
	public boolean isNotify(JSONObject model, String serviceName) {
		try {
			return model.getDouble("systemload.average") > 6d;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getMessage(JSONObject model, String serviceId, String serviceName) {
		try {
			return String.format("%s - %s, System load average %s", serviceName, serviceId, model.getDouble("systemload.average"));
		} catch (JSONException e) {
			return "Erro ao criar a notificação";
		}
	}

}
