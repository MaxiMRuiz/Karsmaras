package com.races.controller;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.races.dto.LoginResponse;
import com.races.dto.PilotoDto;
import com.races.entity.Piloto;
import com.races.exception.RacesException;
import com.races.services.PilotoService;

/**
 * Controlador para los servicios de Pilotos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@RestController
public class PilotoController {

	private static final Log LOGGER = LogFactory.getLog(PilotoController.class);

	@Autowired
	PilotoService pilotoService;

	/**
	 * Servicio de creacion de un nuevo piloto
	 * 
	 * @param pilotoDto
	 * @return
	 */
	@PostMapping("/piloto")
	public ResponseEntity<Piloto> crearPiloto(@RequestBody PilotoDto pilotoDto) {

		try {
			LOGGER.info("Creando nuevo Piloto: " + pilotoDto.getApodo());
			return new ResponseEntity<>(pilotoService.crearPiloto(pilotoDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error creando Piloto: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	/**
	 * Servicio de creacion de un nuevo piloto
	 * 
	 * @param pilotoDto
	 * @return
	 */
	@PutMapping("/piloto/{id}")
	public ResponseEntity<Piloto> editarPiloto(@PathVariable(name = "id") Long id, @RequestBody PilotoDto pilotoDto) {

		try {
			LOGGER.info("Editando Piloto: " + pilotoDto.getApodo());
			return new ResponseEntity<>(pilotoService.editarPiloto(id, pilotoDto), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error Editando Piloto: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Servicio de creacion de un nuevo piloto
	 * 
	 * @param pilotoDto
	 * @return
	 */
	@PutMapping("/admin/{id}")
	public ResponseEntity<Piloto> setPilotoAdmin(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Promocionando a Admin al Piloto: " + id);
			return new ResponseEntity<>(pilotoService.setPilotoAdmin(id, true), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error("Error Promocionando Piloto: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Servicio de creacion de un nuevo piloto
	 * 
	 * @param pilotoDto
	 * @return
	 */
	@PutMapping("/password")
	public ResponseEntity<Boolean> changePassword(
			@RequestHeader(name = "X-Races-Change-Password", required = true) String header) {

		String passHeader = new String(Base64.getDecoder().decode(header), StandardCharsets.UTF_8);
		String[] credenciales = passHeader.split(":");
		if (credenciales.length != 3) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		String alias = credenciales[0];
		char[] password = credenciales[1].toCharArray();
		char[] newPassword = credenciales[2].toCharArray();
		try {
			Boolean responseChange = pilotoService.changePassword(alias, password, newPassword);
			Arrays.fill(password, '0');
			Arrays.fill(newPassword, '0');
			return new ResponseEntity<>(responseChange, HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Error Cambio Contraseña[" + alias + "]: " + ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Servicio para la obtencion de pilotos, con filtros por id, nombre, apellido,
	 * apodo
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param apodo
	 * @return
	 */
	@GetMapping("/piloto")
	public ResponseEntity<List<Piloto>> buscarPiloto(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "nombre") String nombre,
			@RequestParam(required = false, name = "apellido") String apellido,
			@RequestParam(required = false, name = "apodo") String apodo) {

		LOGGER.info("Buscando Pilotos: id[" + id + "] - nombre[" + nombre + "] - apellido[" + apellido + "] - apodo["
				+ apodo + "]");
		return new ResponseEntity<>(pilotoService.buscarPilotos(id, nombre, apellido, apodo), HttpStatus.OK);

	}

	/**
	 * Servicio para el borrado de un piloto
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/piloto/{id}")
	public ResponseEntity<Boolean> borrarPiloto(@PathVariable(name = "id") Long id) {

		try {
			LOGGER.info("Borrando Piloto: " + id);
			return new ResponseEntity<>(pilotoService.borrarPiloto(id), HttpStatus.OK);
		} catch (RacesException ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Servicio para el borrado de un piloto
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(
			@RequestHeader(name = "Authorization", required = true) String authorization) {

		try {
			if (!authorization.substring(0, 5).equalsIgnoreCase("BASIC")) {
				LOGGER.error("Autenticacion no Válida");
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			String authHeader = new String(Base64.getDecoder().decode(authorization.substring(6)),
					StandardCharsets.UTF_8);
			String[] credenciales = authHeader.split(":");
			String alias = credenciales[0];
			char[] password = credenciales[1].toCharArray();
			LoginResponse loginResponse = pilotoService.authenticarUsuario(alias, password);
			Arrays.fill(password, '0');
			return new ResponseEntity<>(loginResponse, HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

}
