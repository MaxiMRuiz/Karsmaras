
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
import org.springframework.web.multipart.MultipartFile;

import com.races.component.RacesException;
import com.races.dto.ResultadoDto;
import com.races.dto.ResultadoResponseDto;
import com.races.entity.Resultado;
import com.races.services.ResultadoService;

/**
 * Controlador para los servicios de Resultados de Grandes Premios
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class ResultadoController {

	private static final Log LOGGER = LogFactory.getLog(ResultadoController.class);

	@Autowired
	ResultadoService resultadoService;

	/**
	 * Servicio de creacion de un nuevo resultado
	 * 
	 * @param reglamentoDto
	 * @return
	 */
	@PostMapping("/resultado")
	public ResponseEntity<Resultado> crearResultado(@RequestBody ResultadoDto resultadoDto) {

		try {
			LOGGER.info("Creando nuevo Resultado: P[" + resultadoDto.getIdPiloto() + "] - S["
					+ resultadoDto.getIdSesion() + "]");
			return new ResponseEntity<>(resultadoService.crearResultado(resultadoDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error creando Resultado: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Servicio de busqueda de Resultados con filtro por Id
	 * 
	 * @return
	 */
	@GetMapping("/resultado")
	public ResponseEntity<List<ResultadoResponseDto>> buscarResultados(
			@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "idPiloto") Long idPiloto,
			@RequestParam(required = false, name = "idSesion") Long idSesion,
			@RequestParam(required = false, name = "nVueltas") Integer nVueltas,
			@RequestParam(required = false, name = "tiempo") Integer tiempo) {

		LOGGER.info("Buscando resultados - id [" + id + "]");
		return new ResponseEntity<>(resultadoService.buscarResultados(id, idPiloto, idSesion, nVueltas, tiempo),
				HttpStatus.OK);

	}

	/**
	 * Servicio para el borrado de un resultado
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/resultado/{id}")
	public ResponseEntity<Boolean> borrarResultado(@PathVariable(name = "id") Long id) {
		try {
			LOGGER.info("Eliminando Puntuacion " + id);
			return new ResponseEntity<>(resultadoService.borrarResultado(id), HttpStatus.OK);
		} catch (RacesException e) {
			LOGGER.error("Error borrando la Puntuacion " + id + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Servicio de actualizacion de un resultado
	 * 
	 * @param reglamentoDto
	 * @return
	 */
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

	@PostMapping(value = "/resultado/load/{id}")
	public ResponseEntity<Boolean> uploadFileHandler(@PathVariable Long id, @RequestParam("file") MultipartFile file) {

		resultadoService.processFile(id, file);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
