package com.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.producer.model.ResultValue;


@Service
public class EventService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// Getting the rabbitMqTemplate object for sending the event object to the queue where a consumer
	// is listening and will process the event object further.
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Value("${event-create-queue}")
	String eventCreateQueue;

	public String openLid(final ResultValue resultValue) {
		
		publishEventToRabbitMq(resultValue);
		final String response = "event created successfully.";
		return response;
	}

	// @Async annotation ensures that the method is executed in a different background thread 
	// but not consume the main thread.
	// this is scheduled to run in every 2 mins with inital delay of 1 sec 
	@Async
	@Scheduled(fixedDelay = 20000, initialDelay = 1000)
	private void publishEventToRabbitMq(ResultValue resultValue) {
		
		logger.info("Sending the following event object to the queue: ");
		// Sending the new event object to the rabbitmq queue where a designated consumer will listen to the event
		// coming on this queue and process the further activities.
		rabbitTemplate.convertAndSend(eventCreateQueue, resultValue);
		logger.info("Message successfully sent to the rabbitMq.");
	}
}
