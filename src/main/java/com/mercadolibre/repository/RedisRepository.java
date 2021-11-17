package com.mercadolibre.repository;

import java.util.Map;

import com.mercadolibre.models.Satellites;

public interface RedisRepository {
	
	Map<String, Satellites> findAll();
	Satellites findById(String id);
	void save(Satellites satellites);

}
