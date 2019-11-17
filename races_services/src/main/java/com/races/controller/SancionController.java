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
import org.springframework.web.bind.annotation.RestController;

import com.races.dto.SancionDto;
import com.races.entity.Sancion;
import com.races.services.SancionService;


@RestController
public class SancionController {

	private static final Log LOGGER = LogFactory.getLog(SancionController.class);

	@Autowired
	SancionService sancionService;

	/**
	 * OK
	 * 
	 * @param reglamentoDto
	 * @return
	 */
	@PostMapping("/sancion")
	public ResponseEntity<Sancion> creaReglamento(@RequestBody SancionDto sancionDto) {

		LOGGER.info("Creando nueva sancion: " + sancionDto.toString());

		Sancion sancion = sancionService.crearSancion(sancionDto);

		return new ResponseEntity<>(sancion, HttpStatus.OK);

	}

	@GetMapping("/sancion")
	public ResponseEntity<List<Sancion>> getReglamento() {

		return new ResponseEntity<>(sancionService.getAllSancion(), HttpStatus.OK);

	}

	@DeleteMapping("/sancion/{id}")
	public ResponseEntity<Sancion> deleteReglamento(@PathVariable(name = "id") Long id) {

		sancionService.removeSancion(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
