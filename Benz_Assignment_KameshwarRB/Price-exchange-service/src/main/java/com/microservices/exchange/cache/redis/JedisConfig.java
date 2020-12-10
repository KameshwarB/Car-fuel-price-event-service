package com.microservices.exchange.cache.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.util.StringUtils;

import com.microservices.exchange.dto.ExchangeValue;

@Configuration
public class JedisConfig {

	
	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private int port;
	@Value("${redis.password}")
	private String password;
	@Value("${redis.jedis.pool.max-active}")
	private int active;
	@Value("${redis.jedis.pool.max-idle}")
	private int minIdle;
	@Value("${redis.jedis.pool.min-idle}")
	private int maxIdle;
	
	
	// jedis client configuration using jedis pooling
	
	@SuppressWarnings("rawtypes")
	@Bean
	JedisClientConfiguration getJedisClientConfiguration() {
		JedisClientConfiguration.JedisClientConfigurationBuilder jedisBuilder = 
				(JedisClientConfiguration.JedisClientConfigurationBuilder) JedisClientConfiguration.builder();
		GenericObjectPoolConfig genPool = new GenericObjectPoolConfig();
		genPool.setMaxTotal(active);
		genPool.setMaxIdle(maxIdle);
		genPool.setMinIdle(minIdle);
		return jedisBuilder.usePooling().build();
		
	}
		
	// jedis connection factory
	
	@Bean
	JedisConnectionFactory jedisConnection() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);
		if(StringUtils.isEmpty(password)) {
			redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		}
		redisStandaloneConfiguration.setPort(port);
		return new JedisConnectionFactory(redisStandaloneConfiguration,getJedisClientConfiguration());
	}
	
	@Bean
	RedisTemplate<String, ExchangeValue> redisTemplate (){
		RedisTemplate<String, ExchangeValue>  redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnection());
		redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(ExchangeValue.class));
		return redisTemplate;
	}
	
	
	// redis template and implement jedis factory
	
}