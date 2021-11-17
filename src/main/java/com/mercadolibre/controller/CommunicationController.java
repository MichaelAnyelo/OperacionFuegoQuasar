package com.mercadolibre.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.models.Communications;
import com.mercadolibre.models.EnumSatellites;
import com.mercadolibre.models.Nave;
import com.mercadolibre.models.Position;
import com.mercadolibre.models.Satellites;
import com.mercadolibre.repository.SatellitesRepository;
import com.mercadolibre.services.CommunicationService;

@RestController
public class CommunicationController {
	
	private SatellitesRepository satellitesRepository;
	
	private CommunicationService communicationService;

	public CommunicationController(SatellitesRepository satellitesRepository, CommunicationService communicationService) {
		this.satellitesRepository = satellitesRepository;
		this.communicationService = communicationService;
	}
	
	

	@PostMapping("/topsecret/")
	public ResponseEntity<?> topSecret(@RequestBody Communications comunications) {

		List<Double> lpos = new ArrayList<>(Arrays.asList());
		List<String[]> ms = new ArrayList<String[]>();
		Nave nave = new Nave();
		double[] pos;
		Position position = new Position(0.0, 0.0);


		for (Satellites s : comunications.getSatellites()) {
			lpos.add(s.getDistance());
			ms.add(s.getMessage());
		}

		double[] distances = lpos.stream().mapToDouble(Double::doubleValue).toArray();

		pos = communicationService.GetLocation(distances);
		position.setX(pos[0]);
		position.setY(pos[1]);

		nave.setPosition(position);
		nave.setMessage(communicationService.GetMessage(ms));
		// cs.GetLocation();

		return ResponseEntity.status(HttpStatus.OK).body(nave);

	}

	@PostMapping("/topsecret_split/{satellite_name}")
	public ResponseEntity<?> topSecretSplit(@PathVariable("satellite_name") String name,
			@RequestBody Satellites satellite) {
		
		satellite.setName(name);
		satellitesRepository.save(satellite);
		return ResponseEntity.status(HttpStatus.OK).body(null);

	}
	
	@GetMapping("/topsecret_split")
	public ResponseEntity<?> getTopSecretSplit() {
		
		List<Double> lpos = new ArrayList<>(Arrays.asList());
		List<String[]> ms = new ArrayList<String[]>();
		Nave nave = new Nave();
		double[] pos;
		Position position = new Position(0.0, 0.0);
		EnumSatellites[] satellites = EnumSatellites.values();
		
		
		for(int i = 0; i< satellites.length;i++) {
			Satellites s = satellitesRepository.findById(satellites[i].name());
			
			if(s == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay suficientes datos para calcular");
			}else
			{
			lpos.add(s.getDistance());
			ms.add(s.getMessage());
			}
			
		}
		double[] distances = lpos.stream().mapToDouble(Double::doubleValue).toArray();
		
		pos = communicationService.GetLocation(distances);
		position.setX(pos[0]);
		position.setY(pos[1]);

		nave.setPosition(position);
		nave.setMessage(communicationService.GetMessage(ms));
		// cs.GetLocation();

		return ResponseEntity.status(HttpStatus.OK).body(nave);
		

	}

}
