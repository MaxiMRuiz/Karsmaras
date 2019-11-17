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

import com.races.dto.EquipoDto;
import com.races.entity.Equipo;
import com.races.services.EquipoService;

@RestController
public class EquipoController {

	private static final Log LOGGER = LogFactory.getLog(EquipoController.class);

	@Autowired
	@Qualifier("EquipoService")
	EquipoService equipoService;

	@PostMapping("/equipo")
	public ResponseEntity<Equipo> creaEquipo(@RequestBody EquipoDto equipoDto) {

		LOGGER.info("Creando nuevo Equipo: " + equipoDto.toString());

		Equipo equipo = equipoService.crearEquipo(equipoDto);

		return new ResponseEntity<>(equipo, HttpStatus.OK);

	}

	@GetMapping("/equipo")
	public ResponseEntity<List<Equipo>> getEquipo(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "nombre") String nombre,
			@RequestParam(required = false, name = "alias") String alias) {

		return new ResponseEntity<>(equipoService.getAllEquipos(id, nombre, alias), HttpStatus.OK);

	}

	@PutMapping("/equipo/{id}")
	public ResponseEntity<Equipo> putEquipo(@PathVariable(name = "id") Long id, @RequestBody EquipoDto equipoDto) {

		Equipo equipo = equipoService.updateEquipo(id, equipoDto);
		if (equipo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(equipo, HttpStatus.OK);

	}

	@DeleteMapping("/equipo/{id}")
	public ResponseEntity<Equipo> borrarEquipo(@PathVariable(name = "id") Long id) {

		equipoService.borrarEquipo(id);

		return new ResponseEntity<>(HttpStatus.OK);

	}

}
