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
import com.races.portal.dto.GranPremio;
import com.races.portal.services.GranPremioService;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;

@Service
public class GranPremioServiceImpl implements GranPremioService {

	private static final Log LOGGER = LogFactory.getLog(PuntuacionServiceImpl.class);

	@Autowired
	Utils utils;

	@Autowired
	Converter converter;

	@Autowired
	Environment env;

	@Override
	public List<GranPremio> buscarGrandesPremios(String id) {
		List<GranPremio> listReglamentos = new ArrayList<>();

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.gp.buscar");
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.PARAM_ID_CAMPEONATO, id);

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, null, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				for (int i = 0; i < jsonArray.length(); i++) {
					listReglamentos.add(converter.json2Gp(jsonArray.getJSONObject(i)));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return listReglamentos;
	}

	@Override
	public GranPremio buscarGranPremio(Long id) {

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.gp.buscar");
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.PARAM_ID, id);

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, null, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				if (jsonArray.length() > 0) {
					return converter.json2Gp(jsonArray.getJSONObject(0));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return new GranPremio();
	}

	@Override
	public void crearGranPremio(String id, GranPremio gp) {

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.gp.crear");
		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_ID_CAMPEONATO, id);
		body.put(Constants.PARAM_UBICACION, gp.getUbicacion());
		body.put(Constants.PARAM_FECHA, gp.getFecha());

		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.CONTENT_TYPE, Constants.APP_JSON);

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, body, headers, HttpMethod.POST);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public Boolean borrarGP(String id) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.gp.borrar") + id;

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, null, null, HttpMethod.DELETE);
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
