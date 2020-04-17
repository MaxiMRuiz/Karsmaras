package com.races.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
