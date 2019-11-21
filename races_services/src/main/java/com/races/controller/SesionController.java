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

import com.races.dto.SesionDto;
import com.races.entity.Sesion;
import com.races.services.SesionService;

/**
 * Controlador para los servicios de Sesiones de Grandes Premios
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class SesionController {

	private static final Log LOGGER = LogFactory.getLog(SesionController.class);

	@Autowired
	SesionService sesionService;

	/**
	 * Servicio de creacion de una nueva sesion
	 * 
	 * @param reglamentoDto
	 * @return
	 */
	@PostMapping("/sesion")
	public ResponseEntity<Sesion> creaReglamento(@RequestBody SesionDto sesionDto) {

		LOGGER.info("Creando nueva Sesion: " + sesionDto.toString());

		Sesion sesion = sesionService.crearSesion(sesionDto);

		return new ResponseEntity<>(sesion, HttpStatus.OK);

	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/sesion")
	public ResponseEntity<List<Sesion>> getReglamento() {

		return new ResponseEntity<>(sesionService.getAllSesion(), HttpStatus.OK);

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/sesion/{id}")
	public ResponseEntity<Sesion> deleteReglamento(@PathVariable(name = "id") Long id) {

		sesionService.removeSesion(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
