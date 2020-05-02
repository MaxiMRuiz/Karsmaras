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
import com.races.portal.dto.Reglamento;
import com.races.portal.services.impl.ReglamentoServiceImpl;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class ReglamentoServiceTest {
	private static final String TEST = "test";

	@Mock
	Utils utils;

	@Mock
	ObjectMapper mapper;

	@Mock
	Environment env;

	@InjectMocks
	ReglamentoServiceImpl reglamentoService;

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
	public void buscarReglamentosTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(Reglamento.class)))
					.thenReturn(new Reglamento());

			assertNotNull(reglamentoService.buscarReglamentos(TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarReglamentosFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(reglamentoService.buscarReglamentos(TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarReglamentoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(reglamentoService.borrarReglamento(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarReglamentoFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(reglamentoService.borrarReglamento(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarReglamentoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(Reglamento.class)))
					.thenReturn(new Reglamento());

			assertNotNull(reglamentoService.buscarReglamento(TEST, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarReglamentoFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(reglamentoService.buscarReglamento(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void editarReglamentoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(reglamentoService.editarReglamento(new Reglamento(1L, TEST, 1L, 1L, 1L, 1L, 1L), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void editarReglamentoFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(reglamentoService.editarReglamento(new Reglamento(1L, TEST, 1L, 1L, 1L, 1L, 1L), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearReglamentoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(reglamentoService.crearReglamento(new Reglamento(1L, TEST, 1L, 1L, 1L, 1L, 1L), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearReglamentoFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(reglamentoService.crearReglamento(new Reglamento(1L, TEST, 1L, 1L, 1L, 1L, 1L), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}
}
