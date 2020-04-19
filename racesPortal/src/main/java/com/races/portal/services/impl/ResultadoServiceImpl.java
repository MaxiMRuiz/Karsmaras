package com.races.portal.services.impl;

import java.io.File;
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
import com.races.portal.dto.Resultado;
import com.races.portal.services.ResultadoService;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;

/**
 * @author Maximino Mañanes Ruiz
 *
 */
@Service
public class ResultadoServiceImpl implements ResultadoService {

	private static final Log LOGGER = LogFactory.getLog(ResultadoServiceImpl.class);

	@Autowired
	Utils utils;

	@Autowired
	Converter converter;

	@Autowired
	Environment env;

	@Override
	public List<Resultado> buscarResultados(Long idSesion, String jwt, String user) {
		List<Resultado> listResultados = new ArrayList<>();

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.resultados.buscar");
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.PARAM_ID_SESION, idSesion);

		try {
			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, headers, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				for (int i = 0; i < jsonArray.length(); i++) {
					listResultados.add(converter.json2Resultado(jsonArray.getJSONObject(i)));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return listResultados;

	}

	@Override
	public Resultado buscarResultado(Long id, String jwt, String user) {

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.resultados.buscar");
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
					return converter.json2Resultado(jsonArray.getJSONObject(0));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return new Resultado();
	}

	@Override
	public void editarResultado(Resultado resultado, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.resultados.actualizar")
				+ resultado.getId();
		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_N_VUELTAS, resultado.getVueltas());
		body.put(Constants.PARAM_TIEMPO, resultado.getTiempo());

		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
		headers.put(Constants.USER_HEADER, user);
		headers.put(Constants.CONTENT_TYPE, Constants.APP_JSON);

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, body, headers, HttpMethod.PUT);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

	}

	@Override
	public void sendFile(File fileResultados, Long idSesion, Long idGp, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.resultados.load")
				+ idSesion;
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
			HttpResponse<?> response = Unirest.post(url).field("file", fileResultados).headers(headers).asEmpty();
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

	}

}
