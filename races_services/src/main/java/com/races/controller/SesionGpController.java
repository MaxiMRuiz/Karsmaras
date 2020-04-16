package com.races.controller;

import java.util.Date;
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
	@PostMapping("/sesionGP")
	public ResponseEntity<SesionGP> crearSesion(@RequestBody SesionGpDto sesionGpDto) {

		try {
			LOGGER.info("Creando nueva Sesion de GP: GP[" + sesionGpDto.getIdGranPremio() + "] - S["
					+ sesionGpDto.getIdSesion() + "]");
			return new ResponseEntity<>(sesionGpService.crearSesion(sesionGpDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error creando Sesion de GP: " + ex.getMessage());
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

	/**
	 * Servicio para el borrado de una sesion
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/sesionGP/{id}")
	public ResponseEntity<Boolean> borrarSesion(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Eliminando sesion de GP " + id);
			return new ResponseEntity<>(sesionGpService.borrarSesion(id), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error borrando la sesion de GP " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
