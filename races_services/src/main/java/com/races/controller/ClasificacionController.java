package com.races.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.races.dto.ClasificacionDto;
import com.races.services.ClasificacionService;

/**
 * Controlador para los calculos de clasificaciones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class ClasificacionController {

	private static final Log LOGGER = LogFactory.getLog(ClasificacionController.class);

	@Autowired
	ClasificacionService clasificaciones;

	/**
	 * Obtención de la clasificacion de un gp.
	 *
	 * @return
	 */
	@GetMapping("/clasificacion/gp/{id}")
	public ResponseEntity<List<ClasificacionDto>> calcularClasificacionGp(@PathVariable Long id) {
		LOGGER.info("Calculando clasificacin del GP " + id);
		return new ResponseEntity<>(clasificaciones.calcularClasificacionGp(id), HttpStatus.OK);

	}
	
	/**
	 * Obtención de la clasificacion de un gp.
	 *
	 * @return
	 */
	@GetMapping("/clasificacion/campeonato/{id}")
	public ResponseEntity<List<ClasificacionDto>> calcularClasificacionCampeonato(@PathVariable Long id) {
		LOGGER.info("Calculando clasificacin del Campeonato " + id);
		return new ResponseEntity<>(clasificaciones.calcularClasificacionCampeonato(id), HttpStatus.OK);

	}

}
