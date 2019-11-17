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

import com.races.dto.PuntuacionDto;
import com.races.entity.Puntuacion;
import com.races.services.PuntuacionService;

@RestController
public class PuntuacionController {

	private static final Log LOGGER = LogFactory.getLog(PuntuacionController.class);

	@Autowired
	PuntuacionService puntuacionService;

	/**
	 * OK
	 * 
	 * @param reglamentoDto
	 * @return
	 */
	@PostMapping("/puntuacion")
	public ResponseEntity<Puntuacion> creaReglamento(@RequestBody PuntuacionDto puntuacionDto) {

		LOGGER.info("Creando nueva Puntuacion: " + puntuacionDto.toString());

		Puntuacion reglamento = puntuacionService.crearPuntuacion(puntuacionDto);

		return new ResponseEntity<>(reglamento, HttpStatus.OK);

	}

	@GetMapping("/puntuacion")
	public ResponseEntity<List<Puntuacion>> getReglamento(
			@RequestParam(required = false, name = "idReglamento") Long idReglamento,
			@RequestParam(required = false, name = "posicion") Integer posicion,
			@RequestParam(required = false, name = "puntos") Integer puntos,
			@RequestParam(required = false, name = "idTipoSesion") Long idTipoSesion) {

		return new ResponseEntity<>(puntuacionService.getAllPuntuaciones(idReglamento, posicion, puntos, idTipoSesion),
				HttpStatus.OK);

	}

	@PutMapping("/puntuacion/{id}")
	public ResponseEntity<Puntuacion> putReglamento(@PathVariable(name = "id") Long id,
			@RequestBody PuntuacionDto puntuacionDto) {

		Puntuacion puntuacion = puntuacionService.updatePuntuacion(id, puntuacionDto);
		if (puntuacion == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(puntuacion, HttpStatus.OK);

	}

	@DeleteMapping("/puntuacion/{id}")
	public ResponseEntity<Puntuacion> deleteReglamento(@PathVariable(name = "id") Long id) {

		puntuacionService.borrarPuntuacion(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
