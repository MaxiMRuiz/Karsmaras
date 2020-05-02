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

import com.races.portal.component.Converter;
import com.races.portal.component.Utils;
import com.races.portal.dto.Clasificacion;
import com.races.portal.services.impl.ClasificacionServiceImpl;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class ClasificacionServiceTest {
	private static final String TEST = "test";

	@Mock
	Utils utils;

	@Mock
	Converter converter;

	@Mock
	Environment env;

	@InjectMocks
	ClasificacionServiceImpl clasificacionService;

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
	public void clasificacionGpTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(converter.json2Clasificacion(Mockito.any())).thenReturn(new Clasificacion());

			assertNotNull(clasificacionService.clasificacionGp(1L, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void clasificacionGpFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(clasificacionService.clasificacionGp(1L, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void clasificacionCampeonatoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(converter.json2Clasificacion(Mockito.any())).thenReturn(new Clasificacion());

			assertNotNull(clasificacionService.clasificacionCampeonato(1L, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void clasificacionCampeonatoFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(clasificacionService.clasificacionCampeonato(1L, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

}
