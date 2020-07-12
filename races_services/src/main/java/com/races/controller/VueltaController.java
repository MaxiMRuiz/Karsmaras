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

import com.races.entity.Vuelta;
import com.races.services.VueltaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Controlador para los servicios de Vueltas de Sesiones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Api(tags = { "Api Vueltas" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Vueltas", description = "Esta api ofrece las funcionalidades para la gestion de Vueltas.") })
@RestController
public class VueltaController {

	private static final Log LOGGER = LogFactory.getLog(VueltaController.class);

	@Autowired
	VueltaService vueltaService;

	/**
	 * Servicio de busqueda de Vueltas
	 * 
	 * @return
	 */
	@ApiOperation(value = "Consultar Vueltas", notes = "Busqueda de Vueltas en la plataforma, admitiendo filtros de consulta.", responseContainer = "List", response = Vuelta.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista de Vueltas obtenida.") })
	@GetMapping("/vuelta")
	public ResponseEntity<List<Vuelta>> buscarVueltas(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idResultado") Long idResultado,
			@RequestParam(required = false, name = "nVuelta") Integer nVuelta,
			@RequestParam(required = false, name = "tiempo") Integer tiempo) {

		LOGGER.info("Servicio de busqueda de Vueltas");
		return new ResponseEntity<>(vueltaService.buscarVueltas(id, idResultado, nVuelta, tiempo), HttpStatus.OK);
	}

}
