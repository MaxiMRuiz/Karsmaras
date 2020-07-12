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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.dto.GranPremioDto;
import com.races.dto.GranPremioSesionesDto;
import com.races.entity.Equipo;
import com.races.entity.GranPremio;
import com.races.exception.RacesException;
import com.races.services.GranPremioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Controlador para los servicios de Grandes premios
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Api(tags = { "Api Grandes Premios" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Grandes Premios", description = "Esta api ofrece las funcionalidades para la gestion de Grendios Premios.") })
@RestController
public class GranPremioController {

	private static final Log LOGGER = LogFactory.getLog(GranPremioController.class);

	@Autowired
	GranPremioService gpService;

	/**
	 * Servicio de creacion de un nuevo gran premio
	 * 
	 * @param gpDto
	 * @return Gran Premio creado
	 */
	@ApiOperation(value = "Crear Gran Premio", notes = "Servicio de creacion de un nuevo Gran Premio.", response = Equipo.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Gran Premio Creado."),
			@ApiResponse(code = 409, message = "Gran Premio Duplicado.") })
	@PostMapping("/gp")
	public ResponseEntity<GranPremio> crearGp(@RequestBody GranPremioDto gpDto) {

		try {
			LOGGER.info("Creando nuevo GP en " + gpDto.getUbicacion() + " para el Campeonato ["
					+ gpDto.getIdCampeonato() + "]");
			return new ResponseEntity<>(gpService.crearGranPremio(gpDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Servicio de obtencion del listado de Grandes Premios
	 * 
	 * @param id           Filtro por ID
	 * @param ubicacion    Filtrio por Ubicacion
	 * @param idCampeonato Filtro por ID del Campeonato
	 * @return Lista de GP aplicando los filtros
	 */
	@ApiOperation(value = "Buscar Grandes Premios", notes = "Servicio de consulta de Grandes Premios.", responseContainer = "List", response = GranPremioSesionesDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista de Grandes Premios devuelta.") })
	@GetMapping("/gp")
	public ResponseEntity<List<GranPremioSesionesDto>> buscarGrandesPremios(
			@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "ubicacion") String ubicacion,
			@RequestParam(required = false, name = "idCampeonato") Long idCampeonato) {

		LOGGER.info("Obteniendo Grandes Premios");
		return new ResponseEntity<>(gpService.buscarGrandesPremios(id, ubicacion, idCampeonato), HttpStatus.OK);

	}

	/**
	 * Servicio de borrado de Gran Premio por Id
	 * 
	 * @param id
	 * @return 200/404
	 */
	@ApiOperation(value = "Borrar Gran Premio", notes = "Servicio de borrado de un Gran Premio.", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Gran Premio Borrado."),
			@ApiResponse(code = 404, message = "Gran Premio no encontrado.") })
	@DeleteMapping("/gp/{id}")
	public ResponseEntity<Boolean> borrarGranPremio(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Borrando el Gran Premio: " + id);
			return new ResponseEntity<>(gpService.borrarGranPremio(id), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
