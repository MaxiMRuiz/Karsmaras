package com.races.portal.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.races.portal.component.Utils;
import com.races.portal.constants.Constants;
import com.races.portal.dto.Campeonato;
import com.races.portal.services.CampeonatoService;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;

/**
 * Implementacion de la interfaz CampeonatoService
 * 
 * @author Maximino Mañanes Ruiz
 */
@Service
public class CampeonatoServiceImpl implements CampeonatoService {

	private static final Log LOGGER = LogFactory.getLog(CampeonatoServiceImpl.class);

	private static final String MSG = "Enviando peticion [%s] a: %s";

	@Autowired
	Utils utils;

	@Autowired
	Environment env;

	@Autowired
	ObjectMapper mapper;

	public List<Campeonato> buscarCampeonatos(Long id, String nombre, String descripcion, String temporada, String jwt,
			String user) {
		List<Campeonato> listCampeonatos = new ArrayList<>();

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.campeonatos.buscar");
		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
		headers.put(Constants.USER_HEADER, user);
		LOGGER.info(String.format(MSG, HttpMethod.GET, url));
		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, null, headers, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				for (int i = 0; i < jsonArray.length(); i++) {
					listCampeonatos.add(mapper.readValue(jsonArray.getJSONObject(i).toString(), Campeonato.class));
				}
			}

		} catch (UnirestException | JSONException | IOException e) {
			LOGGER.error(e);
		}

		return listCampeonatos;
	}

	public Campeonato buscarCampeonato(String id, String jwt, String user) {

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.campeonatos.buscar");
		LOGGER.info(String.format(MSG, HttpMethod.GET, url));
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.PARAM_ID, id);
		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
		headers.put(Constants.USER_HEADER, user);
		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, headers, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				if (jsonArray.length() > 0) {
					return mapper.readValue(jsonArray.getJSONObject(0).toString(), Campeonato.class);
				}
			}

		} catch (UnirestException | JSONException | IOException e) {
			LOGGER.error(e);
		}

		return new Campeonato();
	}

	@Override
	public Boolean editarCampeonato(Campeonato campeonato, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.campeonatos.actualizar")
				+ campeonato.getId();

		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_DESCRIPCION, campeonato.getDescripcion());
		body.put(Constants.PARAM_NOMBRE, campeonato.getNombre());
		body.put(Constants.PARAM_TEMPORADA, campeonato.getTemporada());
		body.put(Constants.PARAM_REGLAMENTO, campeonato.getReglamento().getId());

		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
		headers.put(Constants.USER_HEADER, user);
		headers.put(Constants.CONTENT_TYPE, Constants.APP_JSON);
		LOGGER.info(String.format(MSG, HttpMethod.PUT, url));
		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, body, headers, HttpMethod.PUT);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				return true;
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return false;
	}

	@Override
	public Boolean crearCampeonato(Campeonato campeonato, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.campeonatos.crear");

		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_NOMBRE, campeonato.getNombre());
		body.put(Constants.PARAM_DESCRIPCION, campeonato.getDescripcion());
		body.put(Constants.PARAM_TEMPORADA, campeonato.getTemporada());
		body.put(Constants.PARAM_REGLAMENTO, campeonato.getReglamento().getId());

		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
		headers.put(Constants.USER_HEADER, user);
		headers.put(Constants.CONTENT_TYPE, Constants.APP_JSON);
		LOGGER.info(String.format(MSG, HttpMethod.POST, url));
		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, body, headers, HttpMethod.POST);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				return true;
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return false;
	}

	@Override
	public Boolean borrarCampeonato(String id, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.campeonatos.borrar")
				+ id;

		try {
			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
			LOGGER.info(String.format(MSG, HttpMethod.DELETE, url));
			HttpResponse<String> response = utils.executeHttpMethod(url, null, null, headers, HttpMethod.DELETE);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				return true;
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return false;
	}

}
