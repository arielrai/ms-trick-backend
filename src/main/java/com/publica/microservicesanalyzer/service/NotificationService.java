package com.publica.microservicesanalyzer.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publica.microservicesanalyzer.model.Notification;
import com.publica.microservicesanalyzer.model.NotificationConfiguration;
import com.publica.microservicesanalyzer.model.RegisteredDevice;
import com.publica.microservicesanalyzer.notification.model.NotificationModel;
import com.publica.microservicesanalyzer.repo.DeviceRepository;
import com.publica.microservicesanalyzer.repo.NotificationConfigurationRepository;
import com.publica.microservicesanalyzer.repo.NotificationRepository;

@Service
public class NotificationService {

	public final static String AUTH_KEY_FCM = "AAAAEOuS7qA:APA91bHVn3uosj88C9mcM8Bl_XCTAfTbTK9TnRSOhbNvHUHKb1tL2XqwZDhRnqCfI_JZN45XDLs6koAe0fQInN4WcBCj7ix22RvM1dmtnj-l2F1SYXWtrAUQy7cN1jL7ck0bQvRYam9d";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
	@Autowired
	private List<NotificationModel<?>> notificationModels;
	@Autowired
	private DeviceRepository repo;
	@Autowired
	private NotificationRepository notificationRepo;
	@Autowired
	private NotificationConfigurationRepository notificationConfigurationRepo;
	private Logger logger = LoggerFactory.getLogger(NotificationService.class);

	@Transactional(rollbackOn = Throwable.class)
	public void saveNotificationConfiguration(String token, boolean on) {
		RegisteredDevice registeredDevice = repo.findOne(token);
		if (registeredDevice != null) {
			registeredDevice.setReceiveNotification(on);
		} else {
			registeredDevice = new RegisteredDevice();
			registeredDevice.setDeviceId(token);
			registeredDevice.setReceiveNotification(on);
		}
		repo.save(registeredDevice);
	}

	public void sendNotification(String serviceName, String text) {

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(API_URL_FCM);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", String.format("key=%s", AUTH_KEY_FCM));

		if (getDevices() != null && !getDevices().isEmpty()) {
			JSONObject message = new JSONObject();
			try {
				message.put("registration_ids", new JSONArray(getDevices()));
				message.put("priority", "high");

				JSONObject notification = new JSONObject();
				notification.put("title", serviceName);
				notification.put("body", text);
				notification.put("sound", "notification");
				notification.put("vibrate", 1);
				notification.put("icon", "ionic");

				message.put("notification", notification);

				post.setEntity(new StringEntity(message.toString(), "UTF-8"));
				HttpResponse response = client.execute(post);

				Notification sentNotification = new Notification();
				sentNotification.setDevices(repo.findAll());
				sentNotification.setMessage(String.format("Title: %s; Message: %s", serviceName, text));
				sentNotification.setDateSent(new Date());
				notificationRepo.save(sentNotification);

				System.out.println(response);
				System.out.println(message);
			} catch (JSONException | IOException e) {
				logger.error("Parsing", e);
			}
		}
	}

	public void registerNotificationService() {
		for (NotificationModel<?> notificationModel : notificationModels) {
			if (!notificationConfigurationRepo.exists(notificationModel.getClass().getName())) {
				NotificationConfiguration notificationConfiguration = new NotificationConfiguration();
				notificationConfiguration.setNotificationClass(notificationModel.getClass().getName());
				notificationConfiguration.setNotificationOn(true);
				notificationConfigurationRepo.save(notificationConfiguration);
			}

		}
	}

	public List<String> getDevices() {
		return repo.findAll().stream().filter(d -> d.isReceiveNotification()).map(d -> d.getDeviceId())
				.collect(Collectors.toList());
	}
}
