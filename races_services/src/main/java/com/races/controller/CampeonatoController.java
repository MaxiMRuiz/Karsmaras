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

import com.races.dto.CampeonatoDto;
import com.races.entity.Campeonato;
import com.races.exception.RacesException;
import com.races.services.CampeonatoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Controlador de la tabla Campeonatos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Api(tags = { "Api Campeonatos" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Campeonatos", description = "Esta api ofrece las funcionalidades CRUD para la gestion de Campeonatos en la herramienta.") })
@RestController
public class CampeonatoController {

	private static final Log LOGGER = LogFactory.getLog(CampeonatoController.class);

	@Autowired
	CampeonatoService campeonatoService;

	/**
	 * Creacion de un nuevo campeonato
	 * 
	 * @param campeonatoDto
	 * @return
	 */
	@ApiOperation(value = "Crear Campeonato", notes = "Crea un nuevo campeonato en la aplicación", response = Campeonato.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Campeonato creado correctamente."),
			@ApiResponse(code = 409, message = "Campeonato duplicado") })
	@PostMapping("/campeonato")
	public ResponseEntity<Campeonato> crearCampeonato(@RequestBody CampeonatoDto campeonatoDto) {

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
	@ApiOperation(value = "Buscar Campeonatos", notes = "Servicio de busqueda de campeonatos. Permite aplicar filtros por parametro.", responseContainer = "List", response = Campeonato.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Listado de Campeonatos encontrados.") })
	@GetMapping("/campeonato")
	public ResponseEntity<List<Campeonato>> buscarCampeonatos(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "nombre") String nombre,
			@RequestParam(required = false, name = "temporada") String temporada) {
		LOGGER.info("Busqueda de Campeonatos");
		return new ResponseEntity<>(campeonatoService.buscarCampeonatos(id, nombre, temporada), HttpStatus.OK);

	}

	/**
	 * Actualizacion de un campeonato.
	 * 
	 * @param id
	 * @param reglamentoDto
	 * @return
	 */
	@ApiOperation(value = "Editar Campeonato", notes = "Modifica el campeonato con los datos recibidos", response = Campeonato.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Campeonato actualizado correctamente."),
			@ApiResponse(code = 404, message = "Campeonato no encontrado") })
	@PutMapping("/campeonato/{id}")
	public ResponseEntity<Campeonato> actualizarCampeonato(@PathVariable(name = "id") Long id,
			@RequestBody CampeonatoDto campeonatoDto) {

		try {
			LOGGER.info("Actualizando Campeonato " + id);
			return new ResponseEntity<>(campeonatoService.actualizarCampeonato(id, campeonatoDto), HttpStatus.OK);
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
	@ApiOperation(value = "Eliminar Campeonato", notes = "Servicio de Borrado de un Campeonato de la aplicación", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Campeonato eliminado correctamente."),
			@ApiResponse(code = 404, message = "Campeonato no encontrado") })
	@DeleteMapping("/campeonato/{id}")
	public ResponseEntity<Boolean> borrarCampeonato(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Eliminando Campeonato " + id);
			return new ResponseEntity<>(campeonatoService.borrarCampeonato(id), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error borrando el Campeonato " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
