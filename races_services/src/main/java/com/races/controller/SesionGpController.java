package com.races.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.dto.SesionGpDto;
import com.races.entity.SesionGP;
import com.races.exception.RacesException;
import com.races.services.SesionGpService;

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
@Api(tags = { "Api Sesiones de Grandes Premios" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Sesiones de Grandes Premios", description = "Esta api ofrece las funcionalidades para la gestion de Sesiones de Grendios Premios.") })
@RestController
public class SesionGpController {

	private static final Log LOGGER = LogFactory.getLog(SesionGpController.class);

	@Autowired
	SesionGpService sesionGpService;

	/**
	 * Servicio de creacion de una nueva sesion
	 * 
	 * @param reglamentoDto
	 * @return
	 */
	@ApiOperation(value = "Editar Sesiones de Gran Premio", notes = "Servicio de edicion de Sesiones de Gran Premio en la plataforma.", response = SesionGP.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sesion de Gran Premio editada."),
			@ApiResponse(code = 409, message = "Error en la edicion de Gran Premio.")})
	@PutMapping("/sesionGP/{id}")
	public ResponseEntity<SesionGP> editarSesion(@PathVariable(name = "id") Long id,
			@RequestBody SesionGpDto sesionGpDto) {

		try {
			LOGGER.info("Cambiando fecha de la Sesion de GP[" + id + "] - F["
					+ sesionGpDto.getFecha() + "]");
			return new ResponseEntity<>(sesionGpService.updateSesionGp(id, sesionGpDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error modificando fecha de Sesion de GP: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Servicio de busqueda de Sesiones
	 * 
	 * @return
	 */
	@ApiOperation(value = "Consultar Sesiones de Gran Premio", notes = "Busqueda de Sesiones de Gran Premio en la plataforma, admitiendo filtros de consulta.", responseContainer = "List", response = SesionGP.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista de Sesiones de Gran Premio obtenida.") })
	@GetMapping("/sesionGP")
	public ResponseEntity<List<SesionGP>> buscarSesiones(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idGp") Long idGp,
			@RequestParam(required = false, name = "fecha") Date fecha,
			@RequestParam(required = false, name = "idSesion") Long idSesion) {

		LOGGER.info("Buscando Sesiones de GPs");
		return new ResponseEntity<>(sesionGpService.buscarSesiones(id, idGp, fecha, idSesion), HttpStatus.OK);

	}

}
