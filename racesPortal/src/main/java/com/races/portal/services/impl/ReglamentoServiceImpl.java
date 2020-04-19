package com.races.portal.services.impl;

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

import com.races.portal.component.Converter;
import com.races.portal.component.Utils;
import com.races.portal.constants.Constants;
import com.races.portal.dto.Reglamento;
import com.races.portal.services.ReglamentoService;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;

@Service
public class ReglamentoServiceImpl implements ReglamentoService {

	private static final Log LOGGER = LogFactory.getLog(CampeonatoServiceImpl.class);

	@Autowired
	Utils utils;

	@Autowired
	Converter converter;

	@Autowired
	Environment env;

	@Override
	public List<Reglamento> buscarReglamentos(String jwt, String user) {

		List<Reglamento> listReglamentos = new ArrayList<>();

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.reglamentos.buscar");

		try {
			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
			HttpResponse<String> response = utils.executeHttpMethod(url, null, null, headers, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				for (int i = 0; i < jsonArray.length(); i++) {
					listReglamentos.add(converter.json2Reglamento(jsonArray.getJSONObject(i)));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return listReglamentos;

	}

	@Override
	public Reglamento buscarReglamento(String id, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.reglamentos.buscar");

		Map<String, Object> params = new HashMap<>();
		params.put(Constants.PARAM_ID, id);

		try {
			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, headers, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				if (jsonArray.length() > 0) {
					return converter.json2Reglamento(jsonArray.getJSONObject(0));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return new Reglamento();
	}

	@Override
	public Boolean borrarReglamento(String id, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.reglamentos.borrar")
				+ id;

		try {
			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
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

	@Override
	public Boolean crearReglamento(Reglamento reglamento, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.reglamentos.crear");

		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_DESCRIPCION, reglamento.getDescripcion());
		body.put(Constants.PARAM_N_CARRERAS, reglamento.getnCarreras());
		body.put(Constants.PARAM_N_CLASIFICACIONES, reglamento.getnClasificaciones());
		body.put(Constants.PARAM_N_ENTRENAMIENTOS, reglamento.getnEntrenamientos());
		body.put(Constants.PARAM_N_EQUIPOS, reglamento.getnEquipos());
		body.put(Constants.PARAM_N_PILOTOS, reglamento.getnPilotos());

		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
		headers.put(Constants.USER_HEADER, user);
		headers.put(Constants.CONTENT_TYPE, Constants.APP_JSON);

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
	public Boolean editarReglamento(Reglamento reglamento, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.reglamentos.actualizar")
				+ reglamento.getId();

		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_DESCRIPCION, reglamento.getDescripcion());
		body.put(Constants.PARAM_N_CARRERAS, reglamento.getnCarreras());
		body.put(Constants.PARAM_N_CLASIFICACIONES, reglamento.getnClasificaciones());
		body.put(Constants.PARAM_N_ENTRENAMIENTOS, reglamento.getnEntrenamientos());
		body.put(Constants.PARAM_N_EQUIPOS, reglamento.getnEquipos());
		body.put(Constants.PARAM_N_PILOTOS, reglamento.getnPilotos());

		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
		headers.put(Constants.USER_HEADER, user);
		headers.put(Constants.CONTENT_TYPE, Constants.APP_JSON);

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

}
