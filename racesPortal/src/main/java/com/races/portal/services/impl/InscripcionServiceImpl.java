package com.races.portal.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.races.portal.component.Converter;
import com.races.portal.component.Utils;
import com.races.portal.constants.Constants;
import com.races.portal.dto.Inscripcion;
import com.races.portal.services.InscripcionService;

/**
 * 
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service
public class InscripcionServiceImpl implements InscripcionService {

	private static final Log LOGGER = LogFactory.getLog(InscripcionServiceImpl.class);

	@Autowired
	Utils utils;

	@Autowired
	Converter converter;

	@Autowired
	Environment env;

	@Override
	public List<Inscripcion> buscarInscripciones(String idCampeonato, String idPiloto, String idEquipo) {
		List<Inscripcion> listReglamentos = new ArrayList<>();

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.inscripciones.buscar");
		Map<String, Object> params = new HashMap<>();
		if (idCampeonato != null) {
			params.put(Constants.PARAM_CAMPEONATO, idCampeonato);
		}
		if (idPiloto != null) {
			params.put(Constants.PARAM_PILOTO, idPiloto);
		}
		if (idEquipo != null) {
			params.put(Constants.PARAM_EQUIPO, idEquipo);
		}

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, null, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn("Response " + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				for (int i = 0; i < jsonArray.length(); i++) {
					listReglamentos.add(converter.json2Inscripcion(jsonArray.getJSONObject(i)));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return listReglamentos;
	}

	@Override
	public void crearInscripcion(Inscripcion inscripcion) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.inscripciones.crear");

		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_ID_CAMPEONATO, inscripcion.getCampeonato().getId());
		body.put(Constants.PARAM_ID_EQUIPO, inscripcion.getEquipo().getId());
		body.put(Constants.PARAM_ID_PILOTO, inscripcion.getPiloto().getId());

		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.CONTENT_TYPE, Constants.APP_JSON);

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, body, headers, HttpMethod.POST);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn("Response " + (response == null ? "null" : response.getStatus()));
			}
		} catch (UnirestException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public Boolean borrarInscripcion(Long id) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.inscripciones.borrar")
				+ id;

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, null, null, HttpMethod.DELETE);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn("Response " + (response == null ? "null" : response.getStatus()));
			} else {
				return true;
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return false;
	}

}
