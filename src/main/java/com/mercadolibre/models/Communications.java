package com.mercadolibre.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Communications implements Serializable{
	
	private static final long serialVersionUID = -8225744326803988473L;

    @JsonProperty("satellites")
    private List<Satellites> satellites;

	public synchronized List<Satellites> getSatellites() {
		return satellites;
	}

	public synchronized void setSatellites(List<Satellites> satellites) {
		this.satellites = satellites;
	}

	public static synchronized long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
