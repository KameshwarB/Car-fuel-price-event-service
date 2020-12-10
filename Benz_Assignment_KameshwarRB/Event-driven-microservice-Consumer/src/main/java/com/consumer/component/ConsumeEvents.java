package com.consumer.component;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.consumer.model.ResultValue;



// Service class that will consume the message from the given queue as soon as they are published/pushed to the queue.
@Service
public class ConsumeEvents {

	private static Logger logger = LoggerFactory.getLogger(ConsumeEvents.class);
	
	
	// Annotation allows the message to be listened at the given queue.
	// Queue name is directly read from the properties file.
	@RabbitListener(queues = "${student.create.queue}")
	public void recievedMessage(final ResultValue resultValue) {
		logger.info("Received following message from rabbitmq= " + resultValue);
		// this will get response for the units of fuel consumed if the lid is open
		// this will not send reponse back to the service but will just log that
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("city", resultValue.getCity());
		uriVariables.put("id", ""+resultValue.getId() );

		ResponseEntity<ResultValue> responseEntity = new RestTemplate().getForEntity(
				"/price-converter/{city}/{id}", ResultValue.class,
				uriVariables);
		// the output will be printed in log
		logger.info("Fuel price  for the given :"+responseEntity.getBody());
		
	}
}
