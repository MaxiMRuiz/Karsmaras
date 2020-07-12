package com.races.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.entity.TipoSesion;
import com.races.services.TipoSesionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Controlador para los servicios de Sesiones de Grandes Premios
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Api(tags = { "Api Tipos de Sesiones" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Tipos de Sesiones", description = "Esta api ofrece las funcionalidades para la consulta de Tipos de Sesiones.") })
@RestController
public class TipoSesionController {

	private static final Log LOGGER = LogFactory.getLog(TipoSesionController.class);

	@Autowired
	TipoSesionService tipoSesionService;

	/**
	 * Servicio de busqueda de Tipos de Sesiones
	 * 
	 * @return
	 */
	@ApiOperation(value = "Buscar Tipos de Sesion", notes = "Consulta los tipos de Sesion definidos en la aplicación", responseContainer = "List", response = TipoSesion.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Campeonato creado correctamente."),
			@ApiResponse(code = 409, message = "Campeonato duplicado") })
	@GetMapping("/tsesion")
	public ResponseEntity<List<TipoSesion>> buscarSesiones(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "descripcion") String descripcion) {

		LOGGER.info("Buscando Sesiones");
		return new ResponseEntity<>(tipoSesionService.buscarTipoSesiones(id, descripcion), HttpStatus.OK);

	}

}
