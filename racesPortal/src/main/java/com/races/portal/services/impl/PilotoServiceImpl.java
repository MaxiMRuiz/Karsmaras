package com.races.portal.services.impl;

import java.util.ArrayList;
import java.util.Base64;
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
import com.races.portal.dto.Password;
import com.races.portal.dto.Piloto;
import com.races.portal.services.PilotoService;

import io.micrometer.core.instrument.util.StringUtils;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

/**
 * Implementacion de la interfaz PilotoService
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service
public class PilotoServiceImpl implements PilotoService {

	private static final Log LOGGER = LogFactory.getLog(PilotoServiceImpl.class);

	@Autowired
	Utils utils;

	@Autowired
	Converter converter;

	@Autowired
	Environment env;

	@Override
	public List<Piloto> buscarPilotos(Long id, String nombre, String apellido, String apodo, String jwt, String user) {
		List<Piloto> listaPilotos = new ArrayList<>();

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.pilotos.buscar");

		try {
			Map<String, Object> params = new HashMap<>();
			if (id != null) {
				params.put("id", id);
			}
			if (!StringUtils.isBlank(nombre)) {
				params.put("nombre", nombre);
			}
			if (!StringUtils.isBlank(apellido)) {
				params.put("apellido", apellido);
			}
			if (!StringUtils.isBlank(apodo)) {
				params.put("apodo", apodo);
			}

			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
			HttpResponse<String> response = utils.executeHttpMethod(url, params, null, headers, HttpMethod.GET);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				JSONArray jsonArray = new JSONArray(response.getBody());
				for (int i = 0; i < jsonArray.length(); i++) {
					listaPilotos.add(converter.json2Piloto(jsonArray.getJSONObject(i)));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return listaPilotos;
	}

	@Override
	public Piloto buscarPilotos(String id, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.pilotos.buscar");

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
					return converter.json2Piloto(jsonArray.getJSONObject(0));
				}
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return new Piloto();
	}

	@Override
	public Boolean borrarPiloto(String id, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.pilotos.borrar") + id;

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
	public Boolean crearPiloto(Piloto piloto) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.pilotos.crear");

		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_NOMBRE, piloto.getNombre());
		body.put(Constants.PARAM_APELLIDO, piloto.getApellido());
		body.put(Constants.PARAM_APODO, piloto.getApodo());
		body.put(Constants.PARAM_PASS, piloto.getPassword());
		body.put(Constants.PARAM_ADMIN, false);

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

	@Override
	public Piloto editarPiloto(Piloto piloto, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.pilotos.actualizar")
				+ piloto.getId();

		Map<String, Object> body = new HashMap<>();
		body.put(Constants.PARAM_NOMBRE, piloto.getNombre());
		body.put(Constants.PARAM_APELLIDO, piloto.getApellido());
		body.put(Constants.PARAM_APODO, piloto.getApodo());

		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
		headers.put(Constants.USER_HEADER, user);
		headers.put(Constants.CONTENT_TYPE, Constants.APP_JSON);

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, body, headers, HttpMethod.PUT);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
			} else {
				return converter.json2Piloto(new JSONObject(response.getBody()));
			}

		} catch (UnirestException e) {
			LOGGER.error(e);
		}

		return null;
	}

	@Override
	public Boolean promocionarPiloto(String id, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.pilotos.promocionar")
				+ id;

		try {
			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
			HttpResponse<String> response = utils.executeHttpMethod(url, null, null, headers, HttpMethod.PUT);
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
	public Boolean cambiarPassword(Password password, String jwt, String user) {
		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.pilotos.password");

		try {
			Map<String, String> headers = new HashMap<>();
			headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX + jwt);
			headers.put(Constants.USER_HEADER, user);
			headers.put(Constants.CHANGE_PASS_HEADER, Base64.getEncoder()
					.encodeToString((user + ":" + password.getCurrentPass() + ":" + password.getNewpass()).getBytes()));
			HttpResponse<String> response = utils.executeHttpMethod(url, null, null, headers, HttpMethod.PUT);
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
