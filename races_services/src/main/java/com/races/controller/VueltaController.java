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

import com.races.dto.VueltaDto;
import com.races.entity.Vuelta;
import com.races.services.VueltaService;

/**
 * Controlador para los servicios de Vueltas de Sesiones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class VueltaController {

	private static final Log LOGGER = LogFactory.getLog(VueltaController.class);

	@Autowired
	VueltaService vueltaService;

	/**
	 * Servicio de creacion de una nueva vuelta
	 * 
	 * @param vueltaDto
	 * @return
	 */
	@PostMapping("/vuelta")
	public ResponseEntity<Vuelta> creaReglamento(@RequestBody VueltaDto vueltaDto) {

		LOGGER.info("Creando nueva vuelta: " + vueltaDto.toString());

		Vuelta vuelta = vueltaService.crearVuelta(vueltaDto);

		return new ResponseEntity<>(vuelta, HttpStatus.OK);

	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/vuelta")
	public ResponseEntity<List<Vuelta>> getReglamento() {

		return new ResponseEntity<>(vueltaService.getAllVueltas(), HttpStatus.OK);

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/vuelta/{id}")
	public ResponseEntity<Vuelta> deleteReglamento(@PathVariable(name = "id") Long id) {

		vueltaService.removeVuelta(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
