package com.publica.microservicesanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.publica.microservicesanalyzer.service.NotificationService;
import com.publica.microservicesanalyzer.service.SchedulerService;

@SpringBootApplication
@EnableJpaRepositories
public class MicroservicesAnalyzerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(MicroservicesAnalyzerApplication.class, args);
		run.getBean(NotificationService.class).registerNotificationService();
		run.getBean(SchedulerService.class).runNotifications();
	}
}
