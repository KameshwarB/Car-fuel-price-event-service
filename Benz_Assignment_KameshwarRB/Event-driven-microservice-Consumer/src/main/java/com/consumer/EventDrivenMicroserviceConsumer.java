package com.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
// Enable rabbit listener endpoints.
@EnableRabbit
@EnableDiscoveryClient
public class EventDrivenMicroserviceConsumer {

	private static Logger logger = LoggerFactory.getLogger(EventDrivenMicroserviceConsumer.class);

	public static void main(String[] args) {
		SpringApplication.run(EventDrivenMicroserviceConsumer.class, args);
		logger.info("Event driven microservice consumer application started successfully.");
	}
}
