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
import com.races.portal.dto.Sesion;
import com.races.portal.services.SesionService;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;

@Service
public class SesionServiceImpl implements SesionService {

	private static final Log LOGGER = LogFactory.getLog(SesionServiceImpl.class);

	@Autowired
	Utils utils;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	Environment env;

	@Override
	public List<Sesion> buscarSesiones(String idReglamento, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.sesiones.buscar");
		List<Sesion> listSesiones = new ArrayList<>();
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
			Map<String, Object> params = new HashMap<>();
			params.put(Constants.PARAM_ID_REGLAMENTO, idReglamento);
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, headers, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn("Response " + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				for (int i = 0; i < jsonArray.length(); i++) {
					listSesiones.add(mapper.readValue(jsonArray.getJSONObject(i).toString(),Sesion.class));
				}
			}

		} catch (UnirestException | JSONException | IOException e) {
			LOGGER.error(e);
		}

		return listSesiones;
	}

}
