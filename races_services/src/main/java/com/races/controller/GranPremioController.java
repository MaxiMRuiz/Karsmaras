package com.races.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.dto.GranPremioDto;
import com.races.entity.GranPremio;
import com.races.services.GranPremioService;

@RestController
public class GranPremioController {

	private static final Log LOGGER = LogFactory.getLog(GranPremioController.class);

	@Autowired
	GranPremioService gpService;

	@PostMapping("/gp")
	public ResponseEntity<GranPremio> creaCampeonato(@RequestBody GranPremioDto gpDto) {

		LOGGER.info("Creando nuevo Campeonato: " + gpDto.toString());

		GranPremio campeonato = gpService.crearGranPremio(gpDto);

		return new ResponseEntity<>(campeonato, HttpStatus.OK);

	}

	@GetMapping("/gp")
	public ResponseEntity<List<GranPremio>> getCampeonato(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "ubicacion") String ubicacion,
			@RequestParam(required = false, name = "idCampeonato") Long idCampeonato) {

		List<GranPremio> list = gpService.getAllGrandesPremios(id, ubicacion, idCampeonato);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@DeleteMapping("/gp/{id}")
	public ResponseEntity<GranPremio> deleteCampeonato(@PathVariable(name = "id") Long id) {

		gpService.borrarGranPremio(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
