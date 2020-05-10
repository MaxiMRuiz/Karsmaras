package com.races.portal.services.impl;

import java.io.IOException;
import java.util.Base64;
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
import com.races.portal.component.RacesException;
import com.races.portal.component.Utils;
import com.races.portal.constants.Constants;
import com.races.portal.dto.LoginDto;
import com.races.portal.dto.LoginResponse;
import com.races.portal.services.AuthService;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;

@Service
public class AuthServiceImpl implements AuthService {

	private static final Log LOGGER = LogFactory.getLog(AuthServiceImpl.class);

	@Autowired
	Environment env;

	@Autowired
	Utils utils;
	
	@Autowired
	ObjectMapper mapper;

	@Override
	public LoginResponse login(LoginDto dto) throws RacesException {
		Map<String, String> headers = new HashMap<>();
		headers.put(Constants.CONTENT_TYPE, Constants.APP_JSON);
		headers.put(Constants.AUTHORIZATION_HEADER, Constants.BASIC_PREFIX
				+ Base64.getEncoder().encodeToString((dto.getUser() + ":" + dto.getPassword()).getBytes()));

		String url = env.getProperty(Constants.SERVICES_HOST) + env.getProperty("races.services.login");

		try {
			HttpResponse<String> response = utils.executeHttpMethod(url, null, null, headers, HttpMethod.POST);
			if (response == null || response.getStatus() != HttpStatus.SC_OK) {
				LOGGER.warn(Constants.RESPONSE + (response == null ? "null" : response.getStatus()));
				throw new RacesException("Invalid Credentials");
			} else {
				return mapper.readValue(response.getBody(), LoginResponse.class);
			}
		} catch (RacesException | UnirestException | IOException e) {
			LOGGER.error(e);
			throw new RacesException(e);
		}
	}

}
