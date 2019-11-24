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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.component.RacesException;
import com.races.dto.PuntuacionDto;
import com.races.entity.Puntuacion;
import com.races.services.PuntuacionService;

/**
 * Controlador para los servicios de Puntuaciones de Reglamentos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class PuntuacionController {

	private static final Log LOGGER = LogFactory.getLog(PuntuacionController.class);

	@Autowired
	PuntuacionService puntuacionService;

	/**
	 * Servicio de creacion de una nueva puntuacion
	 * 
	 * @param puntuacionDto
	 * @return
	 */
	@PostMapping("/puntuacion")
	public ResponseEntity<Puntuacion> crearPuntuacion(@RequestBody PuntuacionDto puntuacionDto) {

		try {
			LOGGER.info("Creando nueva Puntuacion: R[" + puntuacionDto.getIdReglamento() + "] - TS["
					+ puntuacionDto.getIdTipoSesion() + "]");

			return new ResponseEntity<>(puntuacionService.crearPuntuacion(puntuacionDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error creando Puntuacion: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	/**
	 * Servicio de busqueda de puntuaciones con filtros por Reglamento, posicion,
	 * puntos y tipo de sesion
	 * 
	 * @param idReglamento
	 * @param posicion
	 * @param puntos
	 * @param idTipoSesion
	 * @return
	 */
	@GetMapping("/puntuacion")
	public ResponseEntity<List<Puntuacion>> buscarPuntuacion(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idReglamento") Long idReglamento,
			@RequestParam(required = false, name = "posicion") Integer posicion,
			@RequestParam(required = false, name = "puntos") Integer puntos,
			@RequestParam(required = false, name = "idTipoSesion") Long idTipoSesion) {

		LOGGER.info("Buscando Puntuaciones: R[" + idReglamento + "] - posicion[" + posicion + "] - puntos[" + puntos
				+ "] - TS[" + idTipoSesion + "]");
		return new ResponseEntity<>(
				puntuacionService.buscarPuntuaciones(id, idReglamento, posicion, puntos, idTipoSesion), HttpStatus.OK);

	}

	/**
	 * Servicio de actualizacion de una puntuacion
	 * 
	 * @param id
	 * @param puntuacionDto
	 * @return
	 */
	@PutMapping("/puntuacion/{id}")
	public ResponseEntity<Puntuacion> actualizarPuntuacion(@PathVariable(name = "id") Long id,
			@RequestBody PuntuacionDto puntuacionDto) {
		try {
			LOGGER.info("Actualizando Puntuacion " + id);
			return new ResponseEntity<>(puntuacionService.actualizarPuntuacion(id, puntuacionDto), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error actualizando la Puntuacion " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Servicio para el borrado de una puntuacion
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/puntuacion/{id}")
	public ResponseEntity<Boolean> borrarPuntuacion(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Eliminando Puntuacion " + id);
			return new ResponseEntity<>(puntuacionService.borrarPuntuacion(id), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error borrando la Puntuacion " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
