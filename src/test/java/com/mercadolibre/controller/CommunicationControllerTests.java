package com.mercadolibre.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.mercadolibre.models.Communications;
import com.mercadolibre.models.Nave;
import com.mercadolibre.models.Position;
import com.mercadolibre.models.Satellites;
import com.mercadolibre.repository.SatellitesRepository;
import com.mercadolibre.services.CommunicationService;

@ExtendWith(MockitoExtension.class)
public class CommunicationControllerTests {

	
	@Mock
	CommunicationService communicationService;
	
	@InjectMocks
	CommunicationController communicationController;

	
	
	Communications communications;

	
	SatellitesRepository satellitesRepositoryMock = Mockito.mock(SatellitesRepository.class);

	@BeforeEach
	void setUp() {
		
		Satellites s1 = new Satellites();
		String[] m1 = {"","", "", "mensaje",""};
		Satellites s2 = new Satellites();
		String[] m2 = {"","es", "", "", "secreto"};
		Satellites s3 = new Satellites();
		String[] m3 = {"este","", "un", "", ""};
	
		s1.setName("kenobi");
		s1.setDistance(110.0);
		s1.setMessage(m1);
		s2.setName("skywalker");
		s2.setDistance(315.5);
		s2.setMessage(m2);
		s3.setName("sato");
		s3.setDistance(242.7);
		s3.setMessage(m3);
		double [] resPosition = {-135.68224397791084,-88.63161118353203};
		String m = "este es un mensaje ";

		
		List<Satellites> listSatellites = new ArrayList<Satellites>();
		listSatellites.add(s1);
		listSatellites.add(s2);
		listSatellites.add(s3);
		Communications  c = new Communications();
		c.setSatellites(listSatellites);
		communications = c;
		
		Mockito.when(satellitesRepositoryMock.findById(s1.getName())).thenReturn(s1);
		Mockito.when(satellitesRepositoryMock.findById(s2.getName())).thenReturn(s2);
		Mockito.when(satellitesRepositoryMock.findById(s3.getName())).thenReturn(s3);
		Mockito.when(communicationService.GetLocation(any())).thenReturn(resPosition);
		Mockito.when(communicationService.GetMessage(any())).thenReturn(m);

	}

	@Test
	void topSecret() {
		ResponseEntity<Nave> response;
		Nave nave = new Nave();
		Position position = new Position(0, 0);
		String m = "este es un mensaje ";
		nave.setMessage(m);
		position.setX(-135.68224397791084);
		position.setY(-88.63161118353203);
		nave.setPosition(position);
		
		response = communicationController.topSecret(communications);
		verify(communicationService, times(1)).GetLocation(any());
		verify(communicationService, times(1)).GetMessage(any());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(nave.getMessage(), ((Nave) response.getBody()).getMessage());
	}
	
	
	@Test
	void getTopSecretSplit() {
		ResponseEntity<?> response;
		Nave nave = new Nave();
		Position position = new Position(0, 0);
		String m = "este es un mensaje ";
		nave.setMessage(m);
		position.setX(-135.68224397791084);
		position.setY(-88.63161118353203);
		nave.setPosition(position);
		
		response = communicationController.getTopSecretSplit();
		verify(communicationService, times(1)).GetLocation(any());
		verify(communicationService, times(1)).GetMessage(any());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(nave.getMessage(), ((Nave) response.getBody()).getMessage());
		
	}
}
