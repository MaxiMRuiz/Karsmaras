package com.races.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.races.dto.ClasificacionDto;
import com.races.services.ClasificacionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Controlador para los calculos de clasificaciones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Api(tags = { "Api Clasificaciones" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Clasificaciones", description = "Esta api ofrece las funcionalidades para la consulta de Clasificaciones en los Campeonatos.") })
@RestController
public class ClasificacionController {

	private static final Log LOGGER = LogFactory.getLog(ClasificacionController.class);

	@Autowired
	ClasificacionService clasificaciones;

	/**
	 * Obtención de la clasificacion de un gp.
	 *
	 * @return
	 */
	@ApiOperation(value = "Consultar Clasificacion de Gran Premio", notes = "Servicio de consulta de Clasificacion de Gran Premio.", responseContainer = "List", response = ClasificacionDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Listado de Clasificaciones de Gran premio obtenido.") })
	@GetMapping("/clasificacion/gp/{id}")
	public ResponseEntity<List<ClasificacionDto>> calcularClasificacionGp(@PathVariable Long id) {
		LOGGER.info("Calculando clasificacin del GP " + id);
		return new ResponseEntity<>(clasificaciones.calcularClasificacionGp(id), HttpStatus.OK);

	}

	/**
	 * Obtención de la clasificacion de un gp.
	 *
	 * @return
	 */
	@ApiOperation(value = "Consultar Clasificacion de Campeonato", notes = "Servicio de consulta de Clasificacion de Campeonato.", responseContainer = "List", response = ClasificacionDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Listado de Clasificaciones de Campeonato obtenido.") })
	@GetMapping("/clasificacion/campeonato/{id}")
	public ResponseEntity<List<ClasificacionDto>> calcularClasificacionCampeonato(@PathVariable Long id) {
		LOGGER.info("Calculando clasificacin del Campeonato " + id);
		return new ResponseEntity<>(clasificaciones.calcularClasificacionCampeonato(id), HttpStatus.OK);

	}

}
