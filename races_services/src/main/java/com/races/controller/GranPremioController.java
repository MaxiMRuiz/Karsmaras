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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.component.RacesException;
import com.races.dto.GranPremioDto;
import com.races.entity.GranPremio;
import com.races.services.GranPremioService;

/**
 * Controlador para los servicios de Grandes premios
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class GranPremioController {

	private static final Log LOGGER = LogFactory.getLog(GranPremioController.class);

	@Autowired
	GranPremioService gpService;

	/**
	 * Servicio de creacion de un nuevo gran premio
	 * 
	 * @param gpDto
	 * @return Gran Premio creado
	 */
	@PostMapping("/gp")
	public ResponseEntity<GranPremio> crearGp(@RequestBody GranPremioDto gpDto) {

		try {
			LOGGER.info("Creando nuevo GP en " + gpDto.getUbicacion() + " para el Campeonato ["
					+ gpDto.getIdCampeonato() + "]");
			return new ResponseEntity<>(gpService.crearGranPremio(gpDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Servicio de obtencion del listado de Grandes Premios
	 * 
	 * @param id           Filtro por ID
	 * @param ubicacion    Filtrio por Ubicacion
	 * @param idCampeonato Filtro por ID del Campeonato
	 * @return Lista de GP aplicando los filtros
	 */
	@GetMapping("/gp")
	public ResponseEntity<List<GranPremio>> getListGp(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "ubicacion") String ubicacion,
			@RequestParam(required = false, name = "idCampeonato") Long idCampeonato) {

		LOGGER.info("Obteniendo Grandes Premios");
		return new ResponseEntity<>(gpService.getAllGrandesPremios(id, ubicacion, idCampeonato), HttpStatus.OK);

	}

	/**
	 * Servicio de borrado de Gran Premio por Id
	 * 
	 * @param id
	 * @return 200/404
	 */
	@DeleteMapping("/gp/{id}")
	public ResponseEntity<GranPremio> deleteGp(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Borrando el Gran Premio: " + id);
			gpService.borrarGranPremio(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
