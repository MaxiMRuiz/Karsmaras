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

import com.races.entity.Sesion;
import com.races.services.SesionService;

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
@Api(tags = { "Api Sesiones" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Sesiones", description = "Esta api ofrece las funcionalidades para la gestion de Sesiones.") })
@RestController
public class SesionController {

	private static final Log LOGGER = LogFactory.getLog(SesionController.class);

	@Autowired
	SesionService sesionService;

	/**
	 * Servicio de busqueda de Sesiones
	 * 
	 * @return
	 */
	@ApiOperation(value = "Consultar Sesiones", notes = "Servicio de consulta de Sesiones en la plataforma.", responseContainer = "List", response = Sesion.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sesiones encontradas.") })
	@GetMapping("/sesion")
	public ResponseEntity<List<Sesion>> buscarSesiones(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idReglamento") Long idReglamento,
			@RequestParam(required = false, name = "descripcion") String descripcion,
			@RequestParam(required = false, name = "idTipoSesion") Long idTipoSesion) {

		LOGGER.info("Buscando Sesiones");
		return new ResponseEntity<>(sesionService.buscarSesiones(id, idReglamento, descripcion, idTipoSesion),
				HttpStatus.OK);

	}

}
