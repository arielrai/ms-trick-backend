package com.publica.microservicesanalyzer.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publica.microservicesanalyzer.model.Notification;
import com.publica.microservicesanalyzer.model.NotificationConfiguration;
import com.publica.microservicesanalyzer.pojo.NotificationConfigPojo;
import com.publica.microservicesanalyzer.pojo.NotificationSentPojo;
import com.publica.microservicesanalyzer.pojo.RegisterPojoToken;
import com.publica.microservicesanalyzer.repo.NotificationConfigurationRepository;
import com.publica.microservicesanalyzer.repo.NotificationRepository;
import com.publica.microservicesanalyzer.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired private NotificationService notificationService;
	@Autowired private NotificationConfigurationRepository notificationConfigRepo;
	@Autowired private NotificationRepository notificationRepo;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterPojoToken registerPojoToken) {
		notificationService.saveNotificationConfiguration(registerPojoToken.getDevice_token(), registerPojoToken.isOn());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public final static String AUTH_KEY_FCM = "AAAAEOuS7qA:APA91bHVn3uosj88C9mcM8Bl_XCTAfTbTK9TnRSOhbNvHUHKb1tL2XqwZDhRnqCfI_JZN45XDLs6koAe0fQInN4WcBCj7ix22RvM1dmtnj-l2F1SYXWtrAUQy7cN1jL7ck0bQvRYam9d";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	/**
	 * Endpoint para push teste
	 * @param request
	 * @param text
	 * @return
	 */
	@GetMapping("/push/{text}")
	public ResponseEntity<?> push(HttpServletRequest request, @PathVariable("text")String text) {

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(API_URL_FCM);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", String.format("key=%s", AUTH_KEY_FCM));

		JSONObject message = new JSONObject();
		try {
			message.put("registration_ids", new JSONArray(notificationService.getDevices()));
			message.put("priority", "high");

			JSONObject notification = new JSONObject();
			notification.put("title", "Serviço XD");
			notification.put("body", text);
			notification.put("sound", "notification");
			notification.put("vibrate", 1);
			notification.put("icon", "ionic");

			message.put("notification", notification);

			post.setEntity(new StringEntity(message.toString(), "UTF-8"));
			HttpResponse response = client.execute(post);
			System.out.println(response);
			System.out.println(message);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/config")
	public ResponseEntity<List<NotificationConfigPojo>> getConfigPojos(){
		return new ResponseEntity<List<NotificationConfigPojo>>(notificationConfigRepo.findAll().stream().map(t -> {
			NotificationConfigPojo notificationConfigPojo = new NotificationConfigPojo();
			notificationConfigPojo.setName(t.getNotificationClass());
			notificationConfigPojo.setNotificationOn(t.isNotificationOn());
			return notificationConfigPojo;
		}).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PostMapping("/config")
	public ResponseEntity<?> saveConfigs(@RequestBody List<NotificationConfigPojo> configs){
		for (NotificationConfigPojo notificationConfigPojo : configs) {
			NotificationConfiguration notificationConfiguration = notificationConfigRepo.findOne(notificationConfigPojo.getName());
			notificationConfiguration.setNotificationOn(notificationConfigPojo.isNotificationOn());
			notificationConfigRepo.save(notificationConfiguration);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/sent")
	public ResponseEntity<List<NotificationSentPojo>> notificationsSent(){
		Page<Notification> notifications = notificationRepo.findAll(new PageRequest(0, 50, new Sort(new Order(Direction.DESC, "id"))));
		return new ResponseEntity<>(notifications.getContent().stream().map(t -> {
			NotificationSentPojo notificationSentPojo = new NotificationSentPojo();
			notificationSentPojo.setDeviceIds(t.getDevices().stream().map(o -> o.getDeviceId()).collect(Collectors.joining(", ")));
			notificationSentPojo.setMessage(t.getMessage());
			notificationSentPojo.setDate(t.getDateSent());
			return notificationSentPojo;
		}).collect(Collectors.toList()), HttpStatus.OK);
	}
}
