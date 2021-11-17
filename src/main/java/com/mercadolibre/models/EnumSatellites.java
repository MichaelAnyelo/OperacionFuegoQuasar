package com.mercadolibre.models;

public enum EnumSatellites {
	kenobi(-500.0, -200.0), skywalker(100.0, -100.0), sato(500.0, 100.0);

	private double positionx;
	private double positiony;

	EnumSatellites(double d, double e) {
		this.positionx = d;
		this.positiony = e;
	}

	public synchronized double getPositionx() {
		return positionx;
	}

	public synchronized void setPositionx(double positionx) {
		this.positionx = positionx;
	}

	public synchronized double getPositiony() {
		return positiony;
	}

	public synchronized void setPositiony(double positiony) {
		this.positiony = positiony;
	}
	
     
}
