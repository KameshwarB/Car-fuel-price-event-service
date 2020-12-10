package com.microservices.conversion.utils;

import com.microservices.conversion.dto.ExchangeValue;
import com.microservices.conversion.dto.ResultValue;

public class PriceCalculator {
	
	public ResultValue calculatePrice(ExchangeValue ex,ResultValue resultValue) {
		
		ResultValue rv= null;
		// if fuelLid  open is true	then the price is calculated or not
		if(resultValue.isFuelLid()) {
			// price is calculated on the basis of number of seconds the fuel lid is open which will come from consumer service
			// calculated as total_time_inSeconds+(price_from_exchange/30) as said in assignment 1 liter takes 30 seconds
			double total = resultValue.getTime()*(ex.getPrice()/30);
			rv = new ResultValue(resultValue.isFuelLid(), resultValue.getTime() ,total, resultValue.getCity());
		}
		else {
			// if fuel lid is false then the event will return the same and it wont calculate the price
			rv = resultValue;
		}
		return rv;
	
	}

}
