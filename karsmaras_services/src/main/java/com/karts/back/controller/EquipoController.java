package com.karts.back.controller;

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

import com.karts.back.dto.EquipoDTO;
import com.karts.back.entity.Equipo;
import com.karts.back.services.EquipoService;

@RestController
public class EquipoController {

	private static final Log LOGGER = LogFactory.getLog(EquipoController.class);

	@Autowired
	@Qualifier("EquipoService")
	EquipoService equipoService;

		@PostMapping("/karts/equipo")
	public ResponseEntity<Equipo> creaEquipo(@RequestBody EquipoDTO equipoDto) {

		LOGGER.info("Creando nuevo Equipo: " + equipoDto.toString());

		Equipo equipo = equipoService.crearEquipo(equipoDto);

		return new ResponseEntity<>(equipo, HttpStatus.OK);

	}

	@GetMapping("/karts/equipo")
	public ResponseEntity<List<Equipo>> getEquipo(@RequestParam(required = false, name = "alias") String alias) {

		LOGGER.info(alias);

		List<Equipo> list;
		if (alias == null) {
			list = equipoService.getAllEquipos();
		} else {
			list = equipoService.getEquipo(alias);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@PutMapping("/karts/equipo/{alias}")
	public ResponseEntity<Equipo> putEquipo(@PathVariable(name = "alias") String alias,
			@RequestBody EquipoDTO equipoDto) {

		LOGGER.info(alias);
		Equipo equipo = equipoService.updateEquipo(alias, equipoDto);
		if (equipo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(equipo, HttpStatus.OK);

	}

	@DeleteMapping("/karts/equipo/{alias}")
	public ResponseEntity<Equipo> borrarEquipo(@PathVariable(name = "alias") String alias) {

		LOGGER.info(alias);
		equipoService.borrarEquipo(alias);

		return new ResponseEntity<>(HttpStatus.OK);

	}

}
