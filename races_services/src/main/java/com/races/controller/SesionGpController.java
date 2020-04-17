package com.races.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.component.RacesException;
import com.races.dto.SesionGpDto;
import com.races.entity.SesionGP;
import com.races.services.SesionGpService;

/**
 * Controlador para los servicios de Sesiones de Grandes Premios
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class SesionGpController {

	private static final Log LOGGER = LogFactory.getLog(SesionGpController.class);

	@Autowired
	SesionGpService sesionGpService;

	/**
	 * Servicio de creacion de una nueva sesion
	 * 
	 * @param reglamentoDto
	 * @return
	 */
	@PutMapping("/sesionGP/{id}")
	public ResponseEntity<SesionGP> crearSesion(@PathVariable(name = "id") Long id,
			@RequestBody SesionGpDto sesionGpDto) {

		try {
			LOGGER.info("Cambiando fecha de la Sesion de GP[" + id + "] - F["
					+ sesionGpDto.getFecha() + "]");
			return new ResponseEntity<>(sesionGpService.updateSesionGp(id, sesionGpDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error modificando fecha de Sesion de GP: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Servicio de busqueda de Sesiones
	 * 
	 * @return
	 */
	@GetMapping("/sesionGP")
	public ResponseEntity<List<SesionGP>> buscarSesiones(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idGp") Long idGp,
			@RequestParam(required = false, name = "fecha") Date fecha,
			@RequestParam(required = false, name = "idSesion") Long idSesion) {

		LOGGER.info("Buscando Sesiones de GPs");
		return new ResponseEntity<>(sesionGpService.buscarSesiones(id, idGp, fecha, idSesion), HttpStatus.OK);

	}

}
