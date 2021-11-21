package com.mercadolibre.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.mercadolibre.models.Satellites;
import com.mercadolibre.services.CommunicationService;
import com.mercadolibre.services.CommunicationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CommunicationServiceTests {
	
	@InjectMocks
	CommunicationServiceImpl communicationService;
	
	@Mock
	NonLinearLeastSquaresSolver nonLinearLeastSquaresSolverMock;
	
	@BeforeEach
	void setUp() {
		
	}
	
	@Test
	void GetLocation() {
		double [] distances = {110.0,315.5,242.7};
		double [] resPosition = {-135.68224397791084,-88.63161118353203};
		double[] res;
		
		res = communicationService.GetLocation(distances);
		Assertions.assertEquals(resPosition[0], resPosition[0]);
		Assertions.assertEquals(resPosition[1], resPosition[1]);
		
	}
	
	@Test
	void GetMessage() {
		List<String[]> message = new ArrayList<String[]>();
		
		String[] m1 = {"","", "", "mensaje"};
		String[] m2 = {"","es", "", ""};
		String[] m3 = {"este","", "un", ""};
	
		
		message.add(m1);
		message.add(m2);
		message.add(m3);
		
		
		String resSim = "este es un mensaje ";
		
		String res = communicationService.GetMessage(message);
		Assertions.assertEquals(resSim, res);
		
	}
	

}
