package com.microservices.conversion.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.microservices.conversion.dto.ExchangeValue;


@RibbonClient(name="price-exchange-service")
public interface PriceExchangeServiceProxy {

	@GetMapping("/price-exchange/from/{city}/{id}")
	public ExchangeValue retrieveExchangeValue(@PathVariable("city") String from, @PathVariable("id") Integer id);
	
}
