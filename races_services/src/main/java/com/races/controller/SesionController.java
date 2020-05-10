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
	 * Servicio de busqueda de Sesiones
	 * 
	 * @return
	 */
	@GetMapping("/sesion")
	public ResponseEntity<List<Sesion>> buscarSesiones(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idReglamento") Long idReglamento,
			@RequestParam(required = false, name = "descripcion") String descripcion,
			@RequestParam(required = false, name = "idTipoSesion") Long idTipoSesion) {

		LOGGER.info("Buscando Sesiones");
		return new ResponseEntity<>(sesionService.buscarSesiones(id, idReglamento, descripcion, idTipoSesion),
				HttpStatus.OK);

	}

}
