package com.races.portal.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.races.portal.component.Converter;
import com.races.portal.component.Utils;
import com.races.portal.dto.Resultado;
import com.races.portal.services.impl.ResultadoServiceImpl;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
public class ResultadoServiceTest {
	private static final String TEST = "test";

	@Mock
	Utils utils;

	@Mock
	Converter converter;

	@Mock
	Environment env;

	@InjectMocks
	ResultadoServiceImpl resultadoService;

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
	public void buscarResultadosTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(converter.json2Resultado(Mockito.any())).thenReturn(new Resultado());

			assertNotNull(resultadoService.buscarResultados(1L, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarResultadosFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(resultadoService.buscarResultados(1L, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarResultadoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(converter.json2Resultado(Mockito.any())).thenReturn(new Resultado());

			assertNotNull(resultadoService.buscarResultado(1L, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarResultadoFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(resultadoService.buscarResultado(1L, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

}
