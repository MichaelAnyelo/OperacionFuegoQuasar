package com.mercadolibre.services;

import java.util.List;

public interface CommunicationService {
	

	
	public double[] GetLocation (double[] distances);
	
	public String GetMessage(List<String[]> messages);
	

}
