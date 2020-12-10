package com.microservices.conversion.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.conversion.dto.ExchangeValue;
import com.microservices.conversion.dto.ResultValue;
import com.microservices.conversion.utils.PriceCalculator;

@RestController
public class PriceConversionController {

	@Autowired
	private PriceCalculator priceCalculator;

	@PostMapping("/price-converter/{from}/{id}")
	public ResultValue convertCurrency(@PathVariable String from, @PathVariable String id,
									   @RequestBody ResultValue resultValue) {

		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", id);

		ResponseEntity<ExchangeValue> responseEntity = new RestTemplate().getForEntity(
				"/price-exchange/from/{city}/{to}", ExchangeValue.class,
				uriVariables);

		ExchangeValue response = responseEntity.getBody();
		// calculates the price
		ResultValue result = priceCalculator.calculatePrice(response, resultValue);
		
		return result;
	}

}
