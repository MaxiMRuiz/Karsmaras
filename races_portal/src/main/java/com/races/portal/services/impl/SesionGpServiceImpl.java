package com.races.portal.services.impl;

import java.io.IOException;
import java.util.HashMap;
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
import com.races.portal.services.SesionGpService;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;

@Service
public class SesionGpServiceImpl implements SesionGpService {

	private static final Log LOGGER = LogFactory.getLog(SesionServiceImpl.class);

	@Autowired
	Utils utils;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	Environment env;

	@Override
	public Sesion buscarSesion(Long idSesion, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.sesiones.gp.buscar");
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.PARAM_ID, idSesion);

		try {
			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, headers, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn("Response " + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				if (jsonArray.length() > 0) {
					return mapper.readValue(response.getBody(), Sesion.class);
				}
			}

		} catch (UnirestException | IOException e) {
			LOGGER.error(e);
		}

		return new Sesion();
	}

}
