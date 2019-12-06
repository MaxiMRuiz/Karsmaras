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
import com.races.portal.dto.Sancion;
import com.races.portal.services.SancionService;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;

/**
 * @author Maximino Mañanes Ruiz
 *
 */
@Service
public class SancionServiceImpl implements SancionService {

	private static final Log LOGGER = LogFactory.getLog(SancionServiceImpl.class);

	@Autowired
	Utils utils;

	@Autowired
	Converter converter;

	@Autowired
	Environment env;

	@Override
	public List<Sancion> buscarSanciones(Long idResultado) {
		List<Sancion> listaSanciones = new ArrayList<>();

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.sancion.buscar");

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, null, null, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				for (int i = 0; i < jsonArray.length(); i++) {
					listaSanciones.add(converter.json2Sancion(jsonArray.getJSONObject(i)));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return listaSanciones;
	}

	@Override
	public Boolean borrarSancion(Long id) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.sancion.borrar") + id;

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

	@Override
	public Sancion buscarSancion(String id) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.sancion.buscar");

		Map<String, Object> params = new HashMap<>();
		params.put(Constants.PARAM_ID, id);

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, null, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				if (jsonArray.length() > 0) {
					return converter.json2Sancion(jsonArray.getJSONObject(0));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return new Sancion();
	}

	@Override
	public Boolean editarSancion(Sancion sancion) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.sancion.actualizar")
				+ sancion.getId();

		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_DESCRIPCION, sancion.getDescripcion());
		body.put(Constants.PARAM_PUNTOS, sancion.getPuntos());
		body.put(Constants.PARAM_TIEMPO, sancion.getTiempo());

		Map<String, String> headers = new HashMap<>();
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

	@Override
	public Boolean crearSancion(Sancion sancion) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.sancion.crear");

		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_DESCRIPCION, sancion.getDescripcion());
		body.put(Constants.PARAM_PUNTOS, sancion.getPuntos());
		body.put(Constants.PARAM_TIEMPO, sancion.getTiempo());
		body.put(Constants.PARAM_ID_RESULTADO, sancion.getResultado().getId());

		Map<String, String> headers = new HashMap<>();
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

}
