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

import com.karsmaras.backend.dto.ClasificacionEquiposDTO;
import com.karsmaras.backend.entity.ClasificacionEquipos;
import com.karsmaras.backend.services.ClasificacionEquipoService;

@RestController
public class ClasificacionEquiposController {

	private static final Log LOGGER = LogFactory.getLog(ClasificacionEquiposController.class);

	@Autowired
	@Qualifier("ClasificacionEquipoService")
	ClasificacionEquipoService clasificacionEquipoService;

	@PostMapping("/karts/clasificacion/equipos")
	public ResponseEntity<ClasificacionEquipos> creaClasificacionEquipos(
			@RequestBody ClasificacionEquiposDTO clasiPilotosDto) {

		LOGGER.info("Creando nueva Clasificacion de Pilotos: " + clasiPilotosDto.toString());

		ClasificacionEquipos clasiPilotos = clasificacionEquipoService.crearClasificacionEquipos(clasiPilotosDto);

		return new ResponseEntity<>(clasiPilotos, HttpStatus.OK);

	}

	@GetMapping("/karts/clasificacion/equipos")
	public ResponseEntity<List<ClasificacionEquipos>> getClasificacionEquipos(
			@RequestParam(required = false, name = "id") Integer id) {

		LOGGER.info(id);

		List<ClasificacionEquipos> list;
		if (id == null) {
			list = clasificacionEquipoService.getAllClasificacionEquipos();
		} else {
			list = clasificacionEquipoService.getClasificacionEquipos(id);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@PutMapping("/karts/clasificacion/equipos/{id}")
	public ResponseEntity<ClasificacionEquipos> putClasificacionEquipos(@PathVariable(name = "id") Integer id,
			@RequestBody ClasificacionEquiposDTO equipoDto) {

		LOGGER.info(id);
		ClasificacionEquipos clasiPilotos = clasificacionEquipoService.updateClasificacionEquipos(id, equipoDto);
		if (clasiPilotos == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clasiPilotos, HttpStatus.OK);

	}

	@DeleteMapping("/karts/clasificacion/equipos/{id}")
	public ResponseEntity<ClasificacionEquipos> borrarClasificacionEquipos(@PathVariable(name = "id") Integer id) {

		LOGGER.info(id);
		clasificacionEquipoService.borrarClasificacionEquipos(id);

		return new ResponseEntity<>(HttpStatus.OK);

	}
}
