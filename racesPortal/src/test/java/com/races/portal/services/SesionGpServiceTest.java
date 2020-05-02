package com.races.portal.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.races.portal.component.Utils;
import com.races.portal.dto.Sesion;
import com.races.portal.services.impl.SesionGpServiceImpl;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class SesionGpServiceTest {
	private static final String TEST = "test";

	@Mock
	Utils utils;

	@Mock
	ObjectMapper mapper;

	@Mock
	Environment env;

	@InjectMocks
	SesionGpServiceImpl sesionGpService;

	JSONArray array;

	JSONObject obj;

	@SuppressWarnings("unchecked")
	HttpResponse<String> mockedResponse = Mockito.mock(HttpResponse.class);

	@Before
	public void init() throws UnirestException {

		MockitoAnnotations.initMocks(this);
		Mockito.when(env.getProperty(Mockito.anyString())).thenReturn("");
		Mockito.when(utils.executeHttpMethod(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(mockedResponse);
		array = new JSONArray();
		obj = new JSONObject();
	}

	@Test
	public void buscarSesionTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(Sesion.class))).thenReturn(new Sesion());

			assertNotNull(sesionGpService.buscarSesion(1L, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarSesionFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(sesionGpService.buscarSesion(1L, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}
}
