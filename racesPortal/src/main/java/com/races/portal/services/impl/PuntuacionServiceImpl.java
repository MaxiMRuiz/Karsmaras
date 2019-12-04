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
import com.races.portal.dto.Puntuacion;
import com.races.portal.services.PuntuacionService;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;

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
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.puntuaciones.buscar");

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
	public Boolean editarPuntuacion(Long reglamento,Puntuacion puntuacion) {
		String url = env.getProperty(Constants.SERVICES_HOST)
				+ env.getProperty("races.services.puntuaciones.actualizar") + puntuacion.getId();

		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_POSICION, puntuacion.getPosicion());
		body.put(Constants.PARAM_PUNTOS, puntuacion.getPuntos());
		body.put(Constants.PARAM_ID_TIPO_SESION, puntuacion.getTipoSesion().getId());
		body.put(Constants.PARAM_ID_REGLAMENTO, reglamento);
		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.CONTENT_TYPE, Constants.APP_JSON);

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, body, headers, HttpMethod.PUT);
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

	@Override
	public Boolean crearPuntuacion(Long reglamento, Puntuacion puntuacion) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.puntuaciones.crear");

		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_POSICION, puntuacion.getPosicion());
		body.put(Constants.PARAM_PUNTOS, puntuacion.getPuntos());
		body.put(Constants.PARAM_ID_TIPO_SESION, puntuacion.getTipoSesion().getId());
		body.put(Constants.PARAM_ID_REGLAMENTO, reglamento);

		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.CONTENT_TYPE, Constants.APP_JSON);

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, body, headers, HttpMethod.POST);
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

	@Override
	public Boolean borrarPuntuacion(String id) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.puntuaciones.borrar")
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
