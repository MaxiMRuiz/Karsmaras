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

import com.races.dto.CampeonatoDto;
import com.races.entity.Campeonato;
import com.races.services.CampeonatoService;

@RestController
public class CampeonatoController {

	private static final Log LOGGER = LogFactory.getLog(CampeonatoController.class);

	@Autowired
	@Qualifier("CampeonatoService")
	CampeonatoService campeonatoService;

	@PostMapping("/campeonato")
	public ResponseEntity<Campeonato> creaCampeonato(@RequestBody CampeonatoDto campeonatoDto) {

		LOGGER.info("Creando nuevo Campeonato: " + campeonatoDto.toString());

		Campeonato campeonato = campeonatoService.crearCampeonato(campeonatoDto);

		return new ResponseEntity<>(campeonato, HttpStatus.OK);

	}

	@GetMapping("/campeonato")
	public ResponseEntity<List<Campeonato>> getCampeonato(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "nombre") String nombre,
			@RequestParam(required = false, name = "temporada") String temporada) {

		List<Campeonato> list = campeonatoService.getAllCampeonatos(id, nombre, temporada);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@GetMapping("/campeonato/{id}")
	public ResponseEntity<Campeonato> getCampeonato(@PathVariable(name = "id") Long id) {

		Campeonato campeonato = campeonatoService.getCampeonato(id);
		if (campeonato == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(campeonato, HttpStatus.OK);
		}

	}

	@PutMapping("/campeonato/{id}")
	public ResponseEntity<Campeonato> putCampeonato(@PathVariable(name = "id") Long id,
			@RequestBody CampeonatoDto reglamentoDto) {

		LOGGER.info(id);
		Campeonato reglamento = campeonatoService.updateCampeonato(id, reglamentoDto);
		if (reglamento == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reglamento, HttpStatus.OK);

	}

	@DeleteMapping("/campeonato/{id}")
	public ResponseEntity<Campeonato> deleteCampeonato(@PathVariable(name = "id") Long id) {

		LOGGER.info(id);
		campeonatoService.borrarCampeonato(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
