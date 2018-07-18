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

import com.karsmaras.backend.dto.PilotoDTO;
import com.karsmaras.backend.entity.Piloto;
import com.karsmaras.backend.services.PilotoService;

@RestController
public class PilotoController {

	private static final Log LOGGER = LogFactory.getLog(PilotoController.class);

	@Autowired
	@Qualifier("PilotoService")
	PilotoService pilotoService;

	@PostMapping("/karts/piloto")
	public ResponseEntity<Piloto> creaPiloto(@RequestBody PilotoDTO pilotoDto) {

		LOGGER.info("Creando nuevo Piloto: " + pilotoDto.toString());

		Piloto piloto = pilotoService.crearPiloto(pilotoDto);

		return new ResponseEntity<>(piloto, HttpStatus.OK);

	}

	@GetMapping("/karts/piloto")
	public ResponseEntity<List<Piloto>> getPiloto(@RequestParam(required = false, name = "alias") String alias) {

		LOGGER.info(alias);

		List<Piloto> list;
		if (alias == null) {
			list = pilotoService.getAllPilotos();
		} else {
			list = pilotoService.getPiloto(alias);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@PutMapping("/karts/piloto/{alias}")
	public ResponseEntity<Piloto> putPiloto(@PathVariable(name = "alias") String alias,
			@RequestBody PilotoDTO pilotoDto) {

		LOGGER.info(alias);
		Piloto piloto = pilotoService.updatePiloto(alias, pilotoDto);
		if (piloto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(piloto, HttpStatus.OK);

	}

	@DeleteMapping("/karts/piloto/{alias}")
	public ResponseEntity<Piloto> borrarPiloto(@PathVariable(name = "alias") String alias,
			@RequestBody PilotoDTO pilotoDto) {

		LOGGER.info(alias);
		pilotoService.borrarPiloto(alias);

		return new ResponseEntity<>(HttpStatus.OK);

	}
	
}
