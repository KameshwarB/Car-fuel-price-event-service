package com.microservices.exchange.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.exchange.dto.ExchangeValue;
import com.microservices.exchange.repository.ExchangeValueRepository;

@RestController
public class PriceExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/price-exchange/from/{city}/{id}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String city,@PathVariable Integer id){
		
		ExchangeValue exchangeValue = 	repository.findPriceByCity(city,id);
		logger.info("{}", exchangeValue);
		
		return exchangeValue;
	}
}
