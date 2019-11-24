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

import com.races.component.RacesException;
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
	public ResponseEntity<Vuelta> crearVuelta(@RequestBody VueltaDto vueltaDto) {

		try {
			LOGGER.info("Creando nueva Vuelta: R[" + vueltaDto.getIdResultado() + "]");
			return new ResponseEntity<>(vueltaService.crearVuelta(vueltaDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error creando Vuelta: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Servicio de busqueda de Vueltas
	 * 
	 * @return
	 */
	@GetMapping("/vuelta")
	public ResponseEntity<List<Vuelta>> buscarVueltas(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idResultado") Long idResultado,
			@RequestParam(required = false, name = "nVuelta") Integer nVuelta,
			@RequestParam(required = false, name = "tiempo") Integer tiempo) {

		LOGGER.info("Servicio de busqueda de Vueltas");
		return new ResponseEntity<>(vueltaService.buscarVueltas(id, idResultado, nVuelta, tiempo), HttpStatus.OK);

	}

	/**
	 * Servicio para el borrado de una vuelta
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/vuelta/{id}")
	public ResponseEntity<Boolean> borrarVuelta(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Borrando la Vuelta " + id);
			return new ResponseEntity<>(vueltaService.borrarVuelta(id), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
