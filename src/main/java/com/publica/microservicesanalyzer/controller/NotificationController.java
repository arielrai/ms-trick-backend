package com.publica.microservicesanalyzer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publica.microservicesanalyzer.pojo.RegisterPojoToken;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	private Map<String, String> devices = new HashMap<>();

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterPojoToken registerPojoToken) {
		devices.put(registerPojoToken.getDevice_token(), registerPojoToken.getDevice_token());
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
			message.put("registration_ids", new JSONArray(devices.values()));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
