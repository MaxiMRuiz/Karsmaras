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

import com.races.dto.ResultadoDto;
import com.races.dto.ResultadoResponseDto;
import com.races.exception.RacesException;
import com.races.services.ResultadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Controlador para los servicios de Resultados de Grandes Premios
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Api(tags = { "Api Resultados" })
@SwaggerDefinition(tags = {
		@Tag(name = "Api Resultados", description = "Esta api ofrece las funcionalidades para la gestion de Resultados.") })
@RestController
public class ResultadoController {

	private static final Log LOGGER = LogFactory.getLog(ResultadoController.class);

	@Autowired
	ResultadoService resultadoService;

	/**
	 * Servicio de busqueda de Resultados con filtro por Id
	 * 
	 * @return
	 */
	@ApiOperation(value = "Consultar Resultados", notes = "Servicio de consulta de Resultados.", responseContainer = "List", response = ResultadoResponseDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Listado de Resultados obtenido.") })
	@GetMapping("/resultado")
	public ResponseEntity<List<ResultadoResponseDto>> buscarResultados(
			@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idInscripcion") Long idInscripcion,
			@RequestParam(required = false, name = "idSesion") Long idSesion,
			@RequestParam(required = false, name = "nVueltas") Integer nVueltas,
			@RequestParam(required = false, name = "tiempo") Integer tiempo) {

		LOGGER.info("Buscando resultados - id [" + id + "]");
		return new ResponseEntity<>(resultadoService.buscarResultados(id, idInscripcion, idSesion, nVueltas, tiempo),
				HttpStatus.OK);

	}

	/**
	 * Servicio de actualizacion de un resultado
	 * 
	 * @param reglamentoDto
	 * @return
	 */
	@ApiOperation(value = "Editar Resultado", notes = "Servicio de edicion de Resultados.", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resultado Actualizado."),
			@ApiResponse(code = 409, message = "Error en la edicion de un Resultado.") })
	@PutMapping("/resultado/{id}")
	public ResponseEntity<Boolean> actualizarResultado(@PathVariable(name = "id") Long id,
			@RequestBody ResultadoDto resultadoDto) {

		try {
			LOGGER.info("Actualizando Resultado: R[" + id + "] - V[" + resultadoDto.getnVueltas() + "]");
			return new ResponseEntity<>(resultadoService.actualizarResultado(id, resultadoDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error Actualizando Resultado: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Servicio de carga de Resultados
	 * 
	 * @param id
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "Carga de Resultados", notes = "Servicio de carga de Resultados en una sesion de un Gran Premio.", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fichero de resultados Procesado.") })
	@PostMapping(value = "/resultado/load/{id}")
	public ResponseEntity<Boolean> uploadFileHandler(@PathVariable Long id, @RequestParam("file") MultipartFile file) {

		resultadoService.processFile(id, file);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
