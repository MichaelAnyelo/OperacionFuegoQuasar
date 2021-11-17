package com.mercadolibre.repository;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.mercadolibre.models.Satellites;

@Repository
public class SatellitesRepository implements RedisRepository {
	
	private static final String KEY = "Satellite";
	
	private RedisTemplate<String, Satellites> redisTemplate;
	private HashOperations hashOperations;
	
	

	public SatellitesRepository(RedisTemplate<String, Satellites> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public Map<String, Satellites> findAll() {
		return hashOperations.entries(KEY);
	}

	@Override
	public Satellites findById(String id) {
		return (Satellites) hashOperations.get(KEY, id);
	}

	@Override
	public void save(Satellites satellites) {
		hashOperations.put(KEY, satellites.getName(), satellites);

	}

}
