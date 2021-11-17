package com.mercadolibre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.mercadolibre.models.Satellites;

@Configuration
public class RedisConfiguration {
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName("10.98.167.35");
		configuration.setPort(6379);
		return new JedisConnectionFactory(configuration);
		//return new JedisConnectionFactory();
		
	}
	
	@Bean
	RedisTemplate<String, Satellites> redisTemplate(){
		final RedisTemplate<String,Satellites> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

}
