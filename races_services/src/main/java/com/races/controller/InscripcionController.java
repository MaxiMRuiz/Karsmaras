package com.races.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.dto.InscripcionDto;
import com.races.entity.Inscripcion;
import com.races.services.InscripcionService;

@RestController
public class InscripcionController {

	private static final Log LOGGER = LogFactory.getLog(InscripcionController.class);

	@Autowired
	@Qualifier("InscripcionService")
	InscripcionService inscriptionService;

	@PostMapping("/inscripcion")
	public ResponseEntity<Inscripcion> creaCampeonato(@RequestBody InscripcionDto inscripcionDto) {

		LOGGER.info("Creando nueva Inscripcion: " + inscripcionDto.toString());

		Inscripcion inscripcion = inscriptionService.crearInscripcion(inscripcionDto);

		return new ResponseEntity<>(inscripcion, HttpStatus.OK);

	}

	@GetMapping("/inscripcion")
	public ResponseEntity<List<Inscripcion>> getInscripcion(@RequestParam(required = false, name = "campeonato") Long campeonato,
			@RequestParam(required = false, name = "piloto") Long piloto,
			@RequestParam(required = false, name = "equipo") Long equipo) {

		List<Inscripcion> list = inscriptionService.getAllInscripciones(campeonato, piloto, equipo);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@DeleteMapping("/inscripcion")
	public ResponseEntity<Inscripcion> deleteCampeonato(@RequestBody InscripcionDto inscripcionDto) {

		inscriptionService.borrarInscripcion(inscripcionDto);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
