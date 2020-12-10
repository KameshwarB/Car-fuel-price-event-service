package com.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
// This annotation allows the asynchronous behavior and runs the methods annotated 
// with @Async annotation in the background thread pools.
@EnableAsync
@EnableDiscoveryClient
public class EventDrivenMicroserviceProducer {

	private static Logger logger = LoggerFactory.getLogger(EventDrivenMicroserviceProducer.class);

	public static void main(String[] args) {
		SpringApplication.run(EventDrivenMicroserviceProducer.class, args);
		logger.info("Producer application started successfully");
	}
}
