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

import com.races.dto.ReglamentoDto;
import com.races.entity.Reglamento;
import com.races.exception.RacesException;
import com.races.services.ReglamentoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Controlador para los servicios de Reglamentos de campeonatos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Api(tags = { "Api Reglamentos" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Reglamentos", description = "Esta api ofrece las funcionalidades para la gestion de Reglamentos.") })
@RestController
public class ReglamentoController {

	private static final Log LOGGER = LogFactory.getLog(ReglamentoController.class);

	@Autowired
	ReglamentoService reglamentoService;

	/**
	 * Servicio de creacion de un nuevo reglamento
	 * 
	 * @param reglamentoDto
	 * @return
	 */
	@ApiOperation(value = "Crear Reglamento", notes = "Servicio de creacion de un nuevo reglamento.", response = Reglamento.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Reglamento creado."),
			@ApiResponse(code = 404, message = "Reglamento duplicado.") })
	@PostMapping("/reglamento")
	public ResponseEntity<Reglamento> crearReglamento(@RequestBody ReglamentoDto reglamentoDto) {

		try {
			LOGGER.info("Creando nuevo Reglamento");
			return new ResponseEntity<>(reglamentoService.crearReglamento(reglamentoDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error creando Reglamento: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	/**
	 * Servicio de busqueda de reglamentos con filtro por id
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Consultar Reglamentos", notes = "Servicio de consulta de reglamentos. Admite filtros por parametro.", responseContainer = "List", response = Reglamento.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Listado de reglamentos devuelto.") })
	@GetMapping("/reglamento")
	public ResponseEntity<List<Reglamento>> buscarReglamentos(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "descripcion") String descripcion,
			@RequestParam(required = false, name = "nEntrenamientos") Integer nEntrenamientos,
			@RequestParam(required = false, name = "nClasificaciones") Integer nClasificaciones,
			@RequestParam(required = false, name = "nCarreras") Integer nCarreras,
			@RequestParam(required = false, name = "nPilotos") Integer nPilotos,
			@RequestParam(required = false, name = "nEquipos") Integer nEquipos) {
		LOGGER.info("Buscando Reglamentos - id[" + id + "]");
		return new ResponseEntity<>(reglamentoService.buscarReglamentos(id, descripcion, nEntrenamientos,
				nClasificaciones, nCarreras, nPilotos, nEquipos), HttpStatus.OK);

	}

	/**
	 * Servicio de actualizacion de un reglamento
	 * 
	 * @param id
	 * @param reglamentoBody
	 * @return
	 */
	@ApiOperation(value = "Editar Reglamento", notes = "Servicio de modificacion de un reglamento.", response = Reglamento.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Reglamento modificado."),
			@ApiResponse(code = 404, message = "Reglamento no encontrado.") })
	@PutMapping("/reglamento/{id}")
	public ResponseEntity<Reglamento> actualizarReglamento(@PathVariable(name = "id") Long id,
			@RequestBody ReglamentoDto reglamentoBody) {

		try {
			LOGGER.info("Actualizando Reglamento " + id);
			return new ResponseEntity<>(reglamentoService.actualizarReglamento(id, reglamentoBody), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error actualizando el Reglamento " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Servicio para el borrado de un Reglamento
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Borrar Reglamento", notes = "Servicio de Borrado de un reglamento.", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Reglamento borrado."),
			@ApiResponse(code = 404, message = "Reglamento no encontrado.") })
	@DeleteMapping("/reglamento/{id}")
	public ResponseEntity<Boolean> borrarReglamento(@PathVariable(name = "id") Long id) {
		try {
			LOGGER.info("Eliminando Puntuacion " + id);
			return new ResponseEntity<>(reglamentoService.borrarReglamento(id), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error borrando la Puntuacion " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
