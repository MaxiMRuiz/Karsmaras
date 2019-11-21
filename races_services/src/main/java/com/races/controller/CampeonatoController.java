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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.component.RacesException;
import com.races.dto.CampeonatoDto;
import com.races.entity.Campeonato;
import com.races.services.CampeonatoService;

/**
 * Controlador de la tabla Campeonatos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class CampeonatoController {

	private static final Log LOGGER = LogFactory.getLog(CampeonatoController.class);

	@Autowired
	@Qualifier("CampeonatoService")
	CampeonatoService campeonatoService;

	/**
	 * Creacion de un nuevo campeonato
	 * 
	 * @param campeonatoDto
	 * @return
	 */
	@PostMapping("/campeonato")
	public ResponseEntity<Campeonato> creaCampeonato(@RequestBody CampeonatoDto campeonatoDto) {

		try {
			LOGGER.info("Creando nuevo Campeonato: " + campeonatoDto.getNombre());
			return new ResponseEntity<>(campeonatoService.crearCampeonato(campeonatoDto), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error creando un nuevo Campeonato: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	/**
	 * Obtención de un listado de campeonatos, pudiendose filtrar por id, nombre o
	 * temporada.
	 * 
	 * @param id
	 * @param nombre
	 * @param temporada
	 * @return
	 */
	@GetMapping("/campeonato")
	public ResponseEntity<List<Campeonato>> getCampeonato(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "nombre") String nombre,
			@RequestParam(required = false, name = "temporada") String temporada) {
		LOGGER.info("Busqueda de Campeonatos");
		return new ResponseEntity<>(campeonatoService.getAllCampeonatos(id, nombre, temporada), HttpStatus.OK);

	}

	/**
	 * Actualizacion de un campeonato.
	 * 
	 * @param id
	 * @param reglamentoDto
	 * @return
	 */
	@PutMapping("/campeonato/{id}")
	public ResponseEntity<Campeonato> putCampeonato(@PathVariable(name = "id") Long id,
			@RequestBody CampeonatoDto reglamentoDto) {

		try {
			LOGGER.info("Actualizando Campeonato " + id);
			Campeonato reglamento = campeonatoService.updateCampeonato(id, reglamentoDto);
			return new ResponseEntity<>(reglamento, HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error actualizando el Campeonato " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Eliminacion de un campeonato
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/campeonato/{id}")
	public ResponseEntity<Campeonato> deleteCampeonato(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Eliminando Campeonato " + id);
			campeonatoService.borrarCampeonato(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error borrando el Campeonato " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
