package com.races.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.races.dto.ReglamentoDTO;
import com.races.entity.Reglamento;
import com.races.services.ReglamentoService;

@RestController
public class ReglamentoController {

	private static final Log LOGGER = LogFactory.getLog(ReglamentoController.class);

	@Autowired
	@Qualifier("ReglamentoService")
	ReglamentoService reglamentoService;

	/**
	 * OK
	 * 
	 * @param reglamentoDto
	 * @return
	 */
	@PostMapping("/reglamento")
	public ResponseEntity<Reglamento> creaReglamento(@RequestBody Reglamento reglamentoDto) {

		LOGGER.info("Creando nuevo Reglamento: " + reglamentoDto.toString());

		Reglamento reglamento = reglamentoService.crearReglamento(reglamentoDto);

		return new ResponseEntity<>(reglamento, HttpStatus.OK);

	}

	@GetMapping("/reglamento")
	public ResponseEntity<List<Reglamento>> getReglamento(@RequestParam(required = false, name = "id") Long id) {

		List<Reglamento> list = new ArrayList<>();
		if (id == null) {
			list = reglamentoService.getAllReglamentos();
		} else {
			Reglamento reglamento = reglamentoService.getReglamento(id);
			list.add(reglamento);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@PutMapping("/reglamento/{id}")
	public ResponseEntity<Reglamento> putReglamento(@PathVariable(name = "id") Long id,
			@RequestBody ReglamentoDTO reglamentoBody) {

		Reglamento reglamento = reglamentoService.updateReglamento(id, reglamentoBody);
		if (reglamento == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reglamento, HttpStatus.OK);

	}

	@DeleteMapping("/reglamento/{id}")
	public ResponseEntity<Reglamento> deleteReglamento(@PathVariable(name = "id") Long id) {

		reglamentoService.borrarReglamento(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
