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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.dto.PilotoDto;
import com.races.entity.Piloto;
import com.races.services.PilotoService;

@RestController
public class PilotoController {

	private static final Log LOGGER = LogFactory.getLog(PilotoController.class);

	@Autowired
	@Qualifier("PilotoService")
	PilotoService pilotoService;

	@PostMapping("/piloto")
	public ResponseEntity<Piloto> creaPiloto(@RequestBody PilotoDto pilotoDto) {

		LOGGER.info("Creando nuevo Piloto: " + pilotoDto.toString());

		Piloto piloto = pilotoService.crearPiloto(pilotoDto);

		return new ResponseEntity<>(piloto, HttpStatus.OK);

	}

	@GetMapping("/piloto")
	public ResponseEntity<List<Piloto>> getPiloto(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "nombre") String nombre,
			@RequestParam(required = false, name = "apellido") String apellido,
			@RequestParam(required = false, name = "apodo") String apodo) {

		return new ResponseEntity<>(pilotoService.getAllPilotos(id,nombre,apellido,apodo), HttpStatus.OK);

	}

	@PutMapping("/piloto/{id}")
	public ResponseEntity<Piloto> putPiloto(@PathVariable(name = "id") Long id,
			@RequestBody PilotoDto pilotoDto) {

		Piloto piloto = pilotoService.updatePiloto(id, pilotoDto);
		if (piloto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(piloto, HttpStatus.OK);

	}

	@DeleteMapping("/piloto/{id}")
	public ResponseEntity<Piloto> borrarPiloto(@PathVariable(name = "id") Long id) {

		pilotoService.borrarPiloto(id);

		return new ResponseEntity<>(HttpStatus.OK);

	}
	
}
