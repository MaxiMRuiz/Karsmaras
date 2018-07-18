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

import com.karsmaras.backend.dto.CampeonatoDTO;
import com.karsmaras.backend.entity.Campeonato;
import com.karsmaras.backend.services.CampeonatoService;

@RestController
public class CampeonatoController {

	private static final Log LOGGER = LogFactory.getLog(CampeonatoController.class);

	@Autowired
	@Qualifier("CampeonatoService")
	CampeonatoService kartingService;

	@PostMapping("/karts/campeonato")
	public ResponseEntity<Campeonato> creaCampeonato(@RequestBody CampeonatoDTO campeonatoDto) {

		LOGGER.info("Creando nuevo Campeonato: " + campeonatoDto.toString());

		Campeonato campeonato = kartingService.crearCampeonato(campeonatoDto);

		return new ResponseEntity<>(campeonato, HttpStatus.OK);

	}

	@GetMapping("/karts/campeonato")
	public ResponseEntity<List<Campeonato>> getCampeonato(@RequestParam(required = false, name = "id") Integer id) {

		LOGGER.info(id);

		List<Campeonato> list;
		if (id == null) {
			list = kartingService.getAllCampeonatos();
		} else {
			list = kartingService.getCampeonato(id);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@PutMapping("/karts/campeonato/{id}")
	public ResponseEntity<Campeonato> putCampeonato(@PathVariable(name = "id") Integer id,
			@RequestBody CampeonatoDTO reglamentoDto) {

		LOGGER.info(id);
		Campeonato reglamento = kartingService.updateCampeonato(id, reglamentoDto);
		if (reglamento == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reglamento, HttpStatus.OK);

	}

	@DeleteMapping("/karts/campeonato/{id}")
	public ResponseEntity<Campeonato> deleteCampeonato(@PathVariable(name = "id") Integer id) {

		LOGGER.info(id);
		kartingService.borrarCampeonato(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
