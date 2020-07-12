package com.races.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.races.dto.PuntuacionDto;
import com.races.entity.Puntuacion;
import com.races.exception.RacesException;
import com.races.services.PuntuacionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Controlador para los servicios de Puntuaciones de Reglamentos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Api(tags = { "Api Puntuaciones" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Puntuaciones", description = "Esta api ofrece las funcionalidades para la gestion de Puntuaciones.") })
@RestController
public class PuntuacionController {

	private static final Log LOGGER = LogFactory.getLog(PuntuacionController.class);

	@Autowired
	PuntuacionService puntuacionService;

	/**
	 * Servicio de busqueda de puntuaciones con filtros por Reglamento, posicion,
	 * puntos y tipo de sesion
	 * 
	 * @param idReglamento
	 * @param posicion
	 * @param puntos
	 * @param idTipoSesion
	 * @return
	 */
	@ApiOperation(value = "Consultar Puntuaciones", notes = "Servicio de consulta de puntuaciones. Admite filtros por parametro", responseContainer = "List", response = Puntuacion.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Listado de puntuaciones devuelto.") })
	@GetMapping("/puntuacion")
	public ResponseEntity<List<Puntuacion>> buscarPuntuacion(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idSesion") Long idSesion,
			@RequestParam(required = false, name = "posicion") Integer posicion,
			@RequestParam(required = false, name = "puntos") Integer puntos) {

		LOGGER.info("Buscando Puntuaciones: S[" + idSesion + "] - posicion[" + posicion + "] - puntos[" + puntos + "]");
		return new ResponseEntity<>(puntuacionService.buscarPuntuaciones(id, idSesion, posicion, puntos),
				HttpStatus.OK);

	}

	/**
	 * Servicio de actualizacion de una puntuacion
	 * 
	 * @param id
	 * @param puntuacionDto
	 * @return
	 */
	@ApiOperation(value = "Editar Puntuacion", notes = "Servicio de modificacion de una puntuacion.", response = Puntuacion.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Puntuacion modificada."),
			@ApiResponse(code = 404, message = "Puntuacion no encontrada.") })
	@PutMapping("/puntuacion/{id}")
	public ResponseEntity<Puntuacion> actualizarPuntuacion(@PathVariable(name = "id") Long id,
			@RequestBody PuntuacionDto puntuacionDto) {
		try {
			LOGGER.info("Actualizando Puntuacion " + id);
			return new ResponseEntity<>(puntuacionService.actualizarPuntuacion(id, puntuacionDto), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error actualizando la Puntuacion " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param idSesion
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "Cargar Puntuaciones", notes = "Servicio de carga de puntuaciones por fichero.", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fichero procesado."),
			@ApiResponse(code = 500, message = "Error en el procesado del fichero.") })
	@PostMapping("/puntuacion/upload/{idSesion}")
	public ResponseEntity<Boolean> uploadFileHandler(@PathVariable Long idSesion,
			@RequestParam("file") MultipartFile file) {

		try {
			puntuacionService.processFile(idSesion, file);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error procesando el fichero: " + e.getMessage(), e);
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
