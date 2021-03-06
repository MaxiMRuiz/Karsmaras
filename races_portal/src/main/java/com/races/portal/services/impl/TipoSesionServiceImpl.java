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
import com.races.portal.dto.TipoSesion;
import com.races.portal.services.TipoSesionService;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;

@Service
public class TipoSesionServiceImpl implements TipoSesionService{

	private static final Log LOGGER = LogFactory.getLog(TipoSesionServiceImpl.class);

	@Autowired
	Utils utils;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	Environment env;
	
	@Override
	public List<TipoSesion> buscarTiposSesiones(String jwt, String user) {
		List<TipoSesion> listReglamentos = new ArrayList<>();

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.tiposesion.buscar");

		try {
			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
			HttpResponse<String> response = utils.executeHttpMethod(url, null, null, headers, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn("Response " + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				for (int i = 0; i < jsonArray.length(); i++) {
					listReglamentos.add(mapper.readValue(jsonArray.getJSONObject(i).toString(),TipoSesion.class));
				}
			}

		} catch (UnirestException | IOException e) {
			LOGGER.error(e);
		}

		return listReglamentos;
	}

}
