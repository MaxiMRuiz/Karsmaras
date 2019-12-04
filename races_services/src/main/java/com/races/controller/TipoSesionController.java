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

import com.races.entity.TipoSesion;
import com.races.services.TipoSesionService;

/**
 * Controlador para los servicios de Sesiones de Grandes Premios
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class TipoSesionController {

	private static final Log LOGGER = LogFactory.getLog(TipoSesionController.class);

	@Autowired
	TipoSesionService tipoSesionService;

	/**
	 * Servicio de busqueda de Tipos de Sesiones
	 * 
	 * @return
	 */
	@GetMapping("/tsesion")
	public ResponseEntity<List<TipoSesion>> buscarSesiones(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "descripcion") String descripcion) {

		LOGGER.info("Buscando Sesiones");
		return new ResponseEntity<>(tipoSesionService.buscarTipoSesiones(id, descripcion), HttpStatus.OK);

	}

}
