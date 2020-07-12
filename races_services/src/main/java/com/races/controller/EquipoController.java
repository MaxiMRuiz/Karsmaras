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

import com.races.dto.EquipoDto;
import com.races.entity.Equipo;
import com.races.exception.RacesException;
import com.races.services.EquipoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Controlador para los servicios de Equipos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Api(tags = { "Api Equipos" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Equipos", description = "Esta api ofrece las funcionalidades para la gestion de Equipos.") })
@RestController
public class EquipoController {

	private static final Log LOGGER = LogFactory.getLog(EquipoController.class);

	@Autowired
	EquipoService equipoService;

	/**
	 * Servicio de creacion de un nuevo equipo
	 * 
	 * @param equipoDto
	 * @return Equipo creado
	 */
	@ApiOperation(value = "Crear Equipo", notes = "Servicio de creacion de Equipos.", response = Equipo.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Equipo Creado."),
			@ApiResponse(code = 409, message = "Equipo Duplicado.")})
	@PostMapping("/equipo")
	public ResponseEntity<Equipo> crearEquipo(@RequestBody EquipoDto equipoDto) {

		try {
			LOGGER.info("Creando nuevo Equipo: " + equipoDto.getNombre());
			return new ResponseEntity<>(equipoService.crearEquipo(equipoDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error creando Equipo " + equipoDto.getNombre() + ":" + ex.getMessage());
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
	@ApiOperation(value = "Consultar Equipos", notes = "Servicio de consulta de Equipos.", responseContainer = "List", response = Equipo.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Listado de Equipos obtenido.") })
	@GetMapping("/equipo")
	public ResponseEntity<List<Equipo>> buscarEquipo(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "nombre") String nombre,
			@RequestParam(required = false, name = "alias") String alias) {

		LOGGER.info("Servicio de busqueda de equipos");
		return new ResponseEntity<>(equipoService.buscarEquipos(id, nombre, alias), HttpStatus.OK);

	}

	/**
	 * Servicio de actualizacion de un equipo
	 * 
	 * @param id
	 * @param equipoDto
	 * @return Equipo actualizado
	 */
	@ApiOperation(value = "Edicion de Equipo", notes = "Servicio de edicion de Equipos.", response = Equipo.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Listado de Clasificaciones de Gran premio obtenido."),
			@ApiResponse(code = 404, message = "Equipo no encontrado.")})
	@PutMapping("/equipo/{id}")
	public ResponseEntity<Equipo> actualizarEquipo(@PathVariable(name = "id") Long id,
			@RequestBody EquipoDto equipoDto) {

		try {
			LOGGER.info("Actualizando el Equipo: " + equipoDto.getNombre());
			return new ResponseEntity<>(equipoService.actualizarEquipo(id, equipoDto), HttpStatus.OK);
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
	@ApiOperation(value = "Borrado Equipo", notes = "Servicio de borrado de Equipo.", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Servicio de Borrado de Equipo."),
			@ApiResponse(code = 404, message = "Equipo no encontrado.")})
	@DeleteMapping("/equipo/{id}")
	public ResponseEntity<Boolean> borrarEquipo(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Borrando el Equipo: " + id);
			return new ResponseEntity<>(equipoService.borrarEquipo(id), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
