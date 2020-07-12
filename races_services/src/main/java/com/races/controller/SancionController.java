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

import com.races.dto.SancionDto;
import com.races.entity.Sancion;
import com.races.exception.RacesException;
import com.races.services.SancionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Controlador para los servicios de Sanciones en Sesiones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Api(tags = { "Api Sanciones" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Sanciones", description = "Esta api ofrece las funcionalidades para la gestion de Sanciones.") })
@RestController
public class SancionController {

	private static final Log LOGGER = LogFactory.getLog(SancionController.class);

	@Autowired
	SancionService sancionService;

	/**
	 * Servicio de creacion de una nueva sancion
	 * 
	 * @param reglamentoDto
	 * @return
	 */
	@ApiOperation(value = "Crear Sancion", notes = "Servicio de creacion de una sancion.", response = Sancion.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sancion creada."),
			@ApiResponse(code = 409, message = "Sancion duplicada.") })
	@PostMapping("/sancion")
	public ResponseEntity<Sancion> crearSancion(@RequestBody SancionDto sancionDto) {

		try {
			LOGGER.info("Creando nueva Sancion: R[" + sancionDto.getIdResultado() + "]");
			return new ResponseEntity<>(sancionService.crearSancion(sancionDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error creando Sancion: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Servicio de busqueda de sanciones
	 * 
	 * @return
	 */
	@ApiOperation(value = "Consultar Sanciones", notes = "Servicio de consulta de sanciones. Admite filtros por parametro..", responseContainer = "List", response = Sancion.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Listado de sanciones devuelto.") })
	@GetMapping("/sancion")
	public ResponseEntity<List<Sancion>> buscarSanciones(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idResultado") Long idResultado,
			@RequestParam(required = false, name = "puntos") Integer puntos,
			@RequestParam(required = false, name = "tiempo") Integer tiempo) {

		LOGGER.info("Buscando sanciones - " + id + " R[" + idResultado + "] - " + puntos + "p - " + tiempo + "s");
		return new ResponseEntity<>(sancionService.buscarSanciones(id, idResultado, puntos, tiempo), HttpStatus.OK);

	}

	/**
	 * Servicio para el borrado de una sancion
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Borrar Sancion", notes = "Servicio de Borrado de una sancion.", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sancion borrada."),
			@ApiResponse(code = 404, message = "Sancion no encontrada.") })
	@DeleteMapping("/sancion/{id}")
	public ResponseEntity<Boolean> borrarSancion(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Eliminando Sancion " + id);
			return new ResponseEntity<>(sancionService.borrarSancion(id), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error borrando la Sancion " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Servicio para el borrado de una sancion
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Editar Sancion", notes = "Servicio de edicion de una sancion.", response = Sancion.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sancion modificada."),
			@ApiResponse(code = 404, message = "Sancion no encontrada.") })
	@PutMapping("/sancion/{id}")
	public ResponseEntity<Sancion> editarSancion(@PathVariable(name = "id") Long id,
			@RequestBody SancionDto sancionDto) {

		try {
			LOGGER.info("Actualizando Sancion " + id);
			return new ResponseEntity<>(sancionService.editarSancion(id, sancionDto), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error actualizando la Sancion " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
