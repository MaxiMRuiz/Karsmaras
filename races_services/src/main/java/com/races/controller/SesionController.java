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
	public ResponseEntity<Sesion> crearSesion(@RequestBody SesionDto sesionDto) {

		try {
			LOGGER.info("Creando nueva Sesion: GP[" + sesionDto.getIdGranPremio() + "] - TS["
					+ sesionDto.getIdTipoSesion() + "]");
			return new ResponseEntity<>(sesionService.crearSesion(sesionDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error creando Sesion: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Servicio de busqueda de Sesiones
	 * 
	 * @return
	 */
	@GetMapping("/sesion")
	public ResponseEntity<List<Sesion>> buscarSesiones(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idGp") Long idGp,
			@RequestParam(required = false, name = "fecha") Date fecha,
			@RequestParam(required = false, name = "idTipoSesion") Long idTipoSesion) {

		LOGGER.info("Buscando Sesiones");
		return new ResponseEntity<>(sesionService.buscarSesiones(id, idGp, fecha, idTipoSesion), HttpStatus.OK);

	}

	/**
	 * Servicio para el borrado de una sesion
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/sesion/{id}")
	public ResponseEntity<Boolean> borrarSesion(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Eliminando Puntuacion " + id);
			return new ResponseEntity<>(sesionService.borrarSesion(id), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error borrando la Puntuacion " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
