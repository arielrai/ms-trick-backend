package com.publica.microservicesanalyzer.service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.spi.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.publica.microservicesanalyzer.mapping.app.Application;
import com.publica.microservicesanalyzer.mapping.app.EurekaApps;
import com.publica.microservicesanalyzer.mapping.app.Instance;
import com.publica.microservicesanalyzer.model.NotificationConfiguration;
import com.publica.microservicesanalyzer.notification.model.AbstractApplicationNotificationModel;
import com.publica.microservicesanalyzer.notification.model.AbstractMetricsNotificationModel;
import com.publica.microservicesanalyzer.repo.NotificationConfigurationRepository;

/**
 * Agenda as tarefas
 * 
 * @author Ariel Rai Rodrigues(arielrairodrigues@gmail.com)
 *
 */
@Service
public class SchedulerService {

	private ScheduledExecutorService schedulePool = Executors
			.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() - 1);

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SchedulerService.class);
	@Value("${eureka.server.url}")
	private String eurekaServerUrl;
	@Value("${scheduler.minutes}")
	private Integer minutes;
	private RestTemplate restTemplate;
	@Autowired
	private List<AbstractMetricsNotificationModel> metricsNotificatiors;
	@Autowired
	private List<AbstractApplicationNotificationModel> appsNotificatiors;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private NotificationConfigurationRepository notificationConfigurationRepo;

	public RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}
		return restTemplate;
	}

	public void runNotifications() {
		if (isNotify()) {
			schedulePool.scheduleAtFixedRate(() -> {
				try {
					
					EurekaApps eurekaApps = getRestTemplate().getForObject(String.format("%s/eureka/apps", eurekaServerUrl),
							EurekaApps.class);
					for (Application app : eurekaApps.getApplications().getApplication()) {
						for (AbstractApplicationNotificationModel appNotifier : appsNotificatiors) {
							NotificationConfiguration notificationConfiguration = notificationConfigurationRepo.findOne(appNotifier.getClass().getName());
							if (notificationConfiguration.isNotificationOn()) {
								if (appNotifier.isNotify(app, app.getName())) {
									notificationService.sendNotification(app.getName(),
											appNotifier.getMessage(app, null, app.getName()));
								}
							}
						}
						for (Instance instance : app.getInstance()) {
							String metrics = getRestTemplate().getForObject(
									instance.getHomePageUrl().substring(0, instance.getHomePageUrl().lastIndexOf(":")) + "/metrics",
									String.class);
							for (AbstractMetricsNotificationModel metricNotifier : metricsNotificatiors) {
								NotificationConfiguration notificationConfiguration = notificationConfigurationRepo.findOne(metricNotifier.getClass().getName());
								if (notificationConfiguration.isNotificationOn()) {
									try {
										JSONObject jsonObject;
										jsonObject = new JSONObject(metrics);
										if (metricNotifier.isNotify(jsonObject, app.getName())) {
											notificationService.sendNotification(app.getName(), metricNotifier.getMessage(
													jsonObject, app.getName(), String.valueOf(instance.getInstanceId())));
										}
									} catch (JSONException e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, 0, minutes, TimeUnit.MINUTES);
		}
	}
	

	@Value("${notify}") private String notify;
	
	public boolean isNotify() {
		if (notify == null) {
			return true;
		} else {
			return Boolean.valueOf(notify);
		}
	}
}
