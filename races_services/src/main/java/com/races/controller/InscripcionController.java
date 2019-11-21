package com.races.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.component.RacesException;
import com.races.dto.InscripcionDto;
import com.races.entity.Inscripcion;
import com.races.services.InscripcionService;

/**
 * Controlador para los servicios de Inscripciones de campeonatos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class InscripcionController {

	private static final Log LOGGER = LogFactory.getLog(InscripcionController.class);

	@Autowired
	@Qualifier("InscripcionService")
	InscripcionService inscriptionService;

	/**
	 * Servicio de creacion de una nueva inscripcion
	 * 
	 * @param inscripcionDto
	 * @return Inscripcion creada
	 */
	@PostMapping("/inscripcion")
	public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody InscripcionDto inscripcionDto) {

		try {
			LOGGER.info("Creando nueva Inscripcion: C" + inscripcionDto.getIdCampeonato() + " - E"
					+ inscripcionDto.getIdEquipo() + " - P" + inscripcionDto.getIdPiloto());
			return new ResponseEntity<>(inscriptionService.crearInscripcion(inscripcionDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error creando inscripcion: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Servicio para la obtencion de inscripciones, permitiendo filtros por
	 * campeonato, piloto y equipo
	 * 
	 * @param campeonato
	 * @param piloto
	 * @param equipo
	 * @return
	 */
	@GetMapping("/inscripcion")
	public ResponseEntity<List<Inscripcion>> buscarInscripcion(
			@RequestParam(required = false, name = "campeonato") Long campeonato,
			@RequestParam(required = false, name = "piloto") Long piloto,
			@RequestParam(required = false, name = "equipo") Long equipo) {

		LOGGER.info("Servicio de busqueda de Inscripciones");
		return new ResponseEntity<>(inscriptionService.getAllInscripciones(campeonato, piloto, equipo), HttpStatus.OK);

	}

	/**
	 * Servicio para el borrado de una inscripcion
	 * 
	 * @param inscripcionDto
	 * @return 200/404
	 */
	@DeleteMapping("/inscripcion")
	public ResponseEntity<Inscripcion> borrarInscripcion(@RequestBody InscripcionDto inscripcionDto) {
		try {
			LOGGER.info("Borrando la Inscripcion : C" + inscripcionDto.getIdCampeonato() + " - E"
					+ inscripcionDto.getIdEquipo() + " - P" + inscripcionDto.getIdPiloto());
			inscriptionService.borrarInscripcion(inscripcionDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
