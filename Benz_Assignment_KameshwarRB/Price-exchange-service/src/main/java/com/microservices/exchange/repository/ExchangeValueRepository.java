package com.microservices.exchange.repository;

import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.microservices.exchange.dto.ExchangeValue;

@Repository
public class ExchangeValueRepository {

	@Autowired
	EntityManager em;

	@Autowired
	RedisTemplate<String,ExchangeValue> redisTemplate;



	public ExchangeValue findPriceByCity(String city, Integer id) {
		ExchangeValue exchangeValue;

		// if the city is already present in cache then exchange value object will be sent directly to API
		if(redisTemplate.opsForValue().get(city) != null) {
			exchangeValue = redisTemplate.opsForValue().get(city);

			return exchangeValue;
		}
		else {
			// if city data expired or if data not available in cache it will fetch from db and store in cache by next time it fetches from cache
			exchangeValue = em.find(ExchangeValue.class, id);
			// this adds the data to cache and set timer to 24 hours and then it expires.
			redisTemplate.opsForValue().set(exchangeValue.getFrom(), exchangeValue);
			redisTemplate.expire(exchangeValue.getFrom(), 24,TimeUnit.HOURS);
			
			return exchangeValue;
		}

	}

}
