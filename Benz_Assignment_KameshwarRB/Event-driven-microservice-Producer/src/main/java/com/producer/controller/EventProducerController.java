package com.producer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producer.model.ResultValue;
import com.producer.service.EventService;

//Useful to create the RESTful webservices.
@RestController
@RequestMapping(value= "/event")
public class EventProducerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	EventService eventService;

	// adds an event to the queue.
	// @PostMapping annotation handles the http post request matched with the given uri.
	// @RequestBody annotation binds the http request body to the domain object.
	@PostMapping(value= "/open")
	public ResponseEntity<String> openFuelLid(@RequestBody final ResultValue resultValue) {
		
		// if fuel Lid is false then send a message as below
		if(!resultValue.isFuelLid()) {
			return new ResponseEntity<>("Fuel Lid is closed",HttpStatus.NO_CONTENT);
		}
		
		logger.info("Creating a new event for fuel price= " + resultValue.toString());
		final String response = eventService.openLid(resultValue);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}