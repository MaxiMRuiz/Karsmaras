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

import com.races.portal.component.Converter;
import com.races.portal.component.Utils;
import com.races.portal.constants.Constants;
import com.races.portal.dto.Clasificacion;
import com.races.portal.services.ClasificacionService;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;

/**
 * @author Maximino Mañanes Ruiz
 *
 */
@Service
public class ClasificacionServiceImpl implements ClasificacionService {

	private static final Log LOGGER = LogFactory.getLog(ClasificacionServiceImpl.class);

	@Autowired
	Utils utils;

	@Autowired
	Converter converter;

	@Autowired
	Environment env;

	@Override
	public List<Clasificacion> clasificacionGp(Long id, String jwt, String user) {
		List<Clasificacion> listaClasificaciones = new ArrayList<>();

		String url = env.getProperty(Constants.SERVICES_HOST)
				+ env.getProperty("races.services.clasificacion.gp.buscar") + id;

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
					listaClasificaciones.add(converter.json2Clasificacion(jsonArray.getJSONObject(i)));
				}
			}

		} catch (UnirestException | JSONException | IOException e) {
			LOGGER.error(e);
		}

		return listaClasificaciones;
	}

	@Override
	public List<Clasificacion> clasificacionCampeonato(Long id, String jwt, String user) {
		List<Clasificacion> listaClasificaciones = new ArrayList<>();

		String url = env.getProperty(Constants.SERVICES_HOST)
				+ env.getProperty("races.services.clasificacion.campeonato.buscar") + id;

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
					listaClasificaciones.add(converter.json2Clasificacion(jsonArray.getJSONObject(i)));
				}
			}

		} catch (UnirestException | JSONException | IOException e) {
			LOGGER.error(e);
		}

		return listaClasificaciones;
	}

}
