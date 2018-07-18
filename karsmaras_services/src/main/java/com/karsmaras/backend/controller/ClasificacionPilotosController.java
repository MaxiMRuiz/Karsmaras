package com.karsmaras.backend.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karsmaras.backend.dto.ClasificacionPilotosDTO;
import com.karsmaras.backend.entity.ClasificacionPilotos;
import com.karsmaras.backend.services.ClasificacionPilotoService;

@RestController
public class ClasificacionPilotosController {

	private static final Log LOGGER = LogFactory.getLog(ClasificacionPilotosController.class);

	@Autowired
	@Qualifier("ClasificacionPilotoService")
	ClasificacionPilotoService clasificacionPilotoService;

	@PostMapping("/karts/clasificacion/pilotos")
	public ResponseEntity<ClasificacionPilotos> creaClasificacionPilotos(
			@RequestBody ClasificacionPilotosDTO clasiPilotosDto) {

		LOGGER.info("Creando nueva Clasificacion de Pilotos: " + clasiPilotosDto.toString());

		ClasificacionPilotos clasiPilotos = clasificacionPilotoService.crearClasificacionPilotos(clasiPilotosDto);

		return new ResponseEntity<>(clasiPilotos, HttpStatus.OK);

	}

	@GetMapping("/karts/clasificacion/pilotos")
	public ResponseEntity<List<ClasificacionPilotos>> getClasificacionPilotos(
			@RequestParam(required = false, name = "id") Integer id) {

		LOGGER.info(id);

		List<ClasificacionPilotos> list;
		if (id == null) {
			list = clasificacionPilotoService.getAllClasificacionPilotos();
		} else {
			list = clasificacionPilotoService.getClasificacionPilotos(id);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@PutMapping("/karts/clasificacion/pilotos/{id}")
	public ResponseEntity<ClasificacionPilotos> putClasificacionPilotos(@PathVariable(name = "id") Integer id,
			@RequestBody ClasificacionPilotosDTO equipoDto) {

		LOGGER.info(id);
		ClasificacionPilotos clasiPilotos = clasificacionPilotoService.updateClasificacionPilotos(id, equipoDto);
		if (clasiPilotos == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clasiPilotos, HttpStatus.OK);

	}

	@DeleteMapping("/karts/clasificacion/pilotos/{id}")
	public ResponseEntity<ClasificacionPilotos> borrarClasificacionPilotos(@PathVariable(name = "id") Integer id) {

		LOGGER.info(id);
		clasificacionPilotoService.borrarClasificacionPilotos(id);

		return new ResponseEntity<>(HttpStatus.OK);

	}
}
