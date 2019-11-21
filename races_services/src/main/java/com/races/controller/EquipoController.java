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
import com.races.dto.EquipoDto;
import com.races.entity.Equipo;
import com.races.services.EquipoService;

/**
 * Controlador para los servicios de Equipos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class EquipoController {

	private static final Log LOGGER = LogFactory.getLog(EquipoController.class);

	@Autowired
	@Qualifier("EquipoService")
	EquipoService equipoService;

	/**
	 * Servicio de creacion de un nuevo equipo
	 * 
	 * @param equipoDto
	 * @return Equipo creado
	 */
	@PostMapping("/equipo")
	public ResponseEntity<Equipo> creaEquipo(@RequestBody EquipoDto equipoDto) {

		try {
			LOGGER.info("Creando nuevo Equipo: " + equipoDto.getNombre());
			return new ResponseEntity<>(equipoService.crearEquipo(equipoDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error creando equipo " + equipoDto.getNombre() + ":" + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Servicio para la obtencion de equipos, permitiendo filtros por id, nombre y
	 * alias
	 * 
	 * @param id
	 * @param nombre
	 * @param alias
	 * @return Lista de equipos
	 */
	@GetMapping("/equipo")
	public ResponseEntity<List<Equipo>> getEquipo(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "nombre") String nombre,
			@RequestParam(required = false, name = "alias") String alias) {

		LOGGER.info("Servicio de busqueda de equipos");
		return new ResponseEntity<>(equipoService.getAllEquipos(id, nombre, alias), HttpStatus.OK);

	}

	/**
	 * Servicio de actualizacion de un equipo
	 * 
	 * @param id
	 * @param equipoDto
	 * @return Equipo actualizado
	 */
	@PutMapping("/equipo/{id}")
	public ResponseEntity<Equipo> putEquipo(@PathVariable(name = "id") Long id, @RequestBody EquipoDto equipoDto) {

		try {
			LOGGER.info("Actualizando el Equipo: " + equipoDto.getNombre());
			return new ResponseEntity<>(equipoService.updateEquipo(id, equipoDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Servicio para el borrado de un equipo
	 * 
	 * @param id
	 * @return 200/404
	 */
	@DeleteMapping("/equipo/{id}")
	public ResponseEntity<Equipo> borrarEquipo(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Borrando el Equipo: " + id);
			equipoService.borrarEquipo(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
