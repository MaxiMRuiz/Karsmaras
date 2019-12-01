/**
 * 
 */
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
import com.races.portal.dto.Puntuacion;
import com.races.portal.services.PuntuacionService;

/**
 * @author Maximino Mañanes Ruiz
 *
 */
@Service
public class PuntuacionServiceImpl implements PuntuacionService {

	private static final Log LOGGER = LogFactory.getLog(PuntuacionServiceImpl.class);

	@Autowired
	Utils utils;

	@Autowired
	Converter converter;

	@Autowired
	Environment env;

	@Override
	public List<Puntuacion> buscarPuntuaciones(String id) {
		List<Puntuacion> listReglamentos = new ArrayList<>();

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.puntuaciones.buscar");
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.PARAM_ID_REGLAMENTO, id);

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, null, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn("Response " + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				for (int i = 0; i < jsonArray.length(); i++) {
					listReglamentos.add(converter.json2Puntuacion(jsonArray.getJSONObject(i)));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return listReglamentos;
	}

	@Override
	public Puntuacion buscarPuntuacion(String id, String subId) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.reglamentos.buscar");

		Map<String, Object> params = new HashMap<>();
		params.put(Constants.PARAM_ID, subId);
		params.put(Constants.PARAM_ID_REGLAMENTO, id);

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, null, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn("Response " + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				if (jsonArray.length() > 0) {
					return converter.json2Puntuacion(jsonArray.getJSONObject(0));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return new Puntuacion();
	}

	@Override
	public Boolean editarPuntuacion(Puntuacion puntuacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean crearPuntuacion(Puntuacion puntuacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean borrarPuntuacion(String id, String obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
