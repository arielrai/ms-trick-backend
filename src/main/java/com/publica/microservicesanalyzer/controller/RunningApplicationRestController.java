package com.publica.microservicesanalyzer.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.publica.microservicesanalyzer.mapping.app.Application;
import com.publica.microservicesanalyzer.mapping.app.EurekaApps;
import com.publica.microservicesanalyzer.mapping.app.Instance;
import com.publica.microservicesanalyzer.mapping.request.Content;
import com.publica.microservicesanalyzer.pojo.RunningApplicationPojo;
import com.publica.microservicesanalyzer.pojo.ThirdPartyRequest;

@RestController
public class RunningApplicationRestController {

	@Value("${eureka.server.url}") private String eurekaServerUrl;
	@Value("${gateway.server.url}") private String gatewayServerUrl;
	private RestTemplate restTemplate;

	public RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}
		return restTemplate;
	}
	
	@GetMapping
	@RequestMapping("/apps")
	public ResponseEntity<List<RunningApplicationPojo>> getRunningApplication(){
		EurekaApps eurekaApps = getRestTemplate().getForObject(String.format("%s/eureka/apps", eurekaServerUrl), EurekaApps.class);
		List<Application> runningApps = eurekaApps.getApplications().getApplication();
		List<RunningApplicationPojo> runningAppsPojos = new ArrayList<>();
		for (Application app : runningApps) {
			List<Instance> instances = app.getInstance();
			for (Instance instance : instances) {
				RunningApplicationPojo runningApplicationPojo = new RunningApplicationPojo();
				runningApplicationPojo.setApplicationName(app.getName());
				runningApplicationPojo.setUpSince(instance.getLeaseInfo().getServiceUpTimestamp());
				runningApplicationPojo.setInstanceId(instance.getInstanceId());
				runningApplicationPojo.setHost(instance.getHostName());
				runningApplicationPojo.setIpAdress(instance.getIpAddr());
				runningAppsPojos.add(runningApplicationPojo);
			}
		}
		return new ResponseEntity<>(runningAppsPojos, HttpStatus.OK);
	}

	@GetMapping("/requests")
	public ResponseEntity<List<Content>> getRequests(){
		ResponseEntity<Content[]> forEntity = getRestTemplate().getForEntity(String.format("%s/trace", gatewayServerUrl), Content[].class);
		return new ResponseEntity<>(Arrays.asList(forEntity.getBody()), HttpStatus.OK);
	}
	
	@PostMapping("/thirdParty")
	public ResponseEntity<String> thirdPartyRequest(@RequestBody ThirdPartyRequest request){
		return getRestTemplate().getForEntity(String.format("%s%s%s/", request.getProtocol(), request.getHost(), request.getPath()), String.class);
	}
	
	@PostMapping("/thirdPartyPost")
	public ResponseEntity<String> thirdPartyRequestPost(@RequestBody ThirdPartyRequest request){
		return getRestTemplate().postForEntity(String.format("%s%s%s/", request.getProtocol(),request.getHost(), request.getPath()), null,String.class);
	}
}
