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
import org.springframework.web.bind.annotation.RestController;

import com.races.dto.ResultadoDto;
import com.races.entity.Resultado;
import com.races.services.ResultadoService;

@RestController
public class ResultadoController {

	private static final Log LOGGER = LogFactory.getLog(ResultadoController.class);

	@Autowired
	ResultadoService resultadoService;

	/**
	 * OK
	 * 
	 * @param reglamentoDto
	 * @return
	 */
	@PostMapping("/resultado")
	public ResponseEntity<Resultado> creaReglamento(@RequestBody ResultadoDto resultadoDto) {

		LOGGER.info("Creando nueva Sesion: " + resultadoDto.toString());

		Resultado sesion = resultadoService.crearResultado(resultadoDto);

		return new ResponseEntity<>(sesion, HttpStatus.OK);

	}

	@GetMapping("/resultado")
	public ResponseEntity<List<Resultado>> getReglamento() {

		return new ResponseEntity<>(resultadoService.getAllResultado(), HttpStatus.OK);

	}

	@DeleteMapping("/resultado/{id}")
	public ResponseEntity<Resultado> deleteReglamento(@PathVariable(name = "id") Long id) {

		resultadoService.removeResultado(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
