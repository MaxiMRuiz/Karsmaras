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
import com.races.portal.dto.Resultado;
import com.races.portal.dto.Sancion;
import com.races.portal.services.impl.SancionServiceImpl;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class SancionServiceTest {
	private static final String TEST = "test";

	@Mock
	Utils utils;

	@Mock
	Converter converter;

	@Mock
	Environment env;

	@InjectMocks
	SancionServiceImpl sancionService;

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
	public void buscarSancionesTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(converter.json2Sancion(Mockito.any())).thenReturn(new Sancion());

			assertNotNull(sancionService.buscarSanciones(1L, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarSancionesFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(sancionService.buscarSanciones(1L, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarSancionTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(sancionService.borrarSancion(1L, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarSancionFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(sancionService.borrarSancion(1L, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarSancionTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(converter.json2Sancion(Mockito.any())).thenReturn(new Sancion());

			assertNotNull(sancionService.buscarSancion(TEST, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarSancionFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(sancionService.buscarSancion(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void editarSancionTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(sancionService.editarSancion(new Sancion(1L, new Resultado(), TEST,1,"1"), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void editarSancionFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(sancionService.editarSancion(new Sancion(1L, new Resultado(), TEST,1,"1"), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearSancionTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(sancionService.crearSancion(new Sancion(1L, new Resultado(), TEST,1,"1"), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearSancionFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(sancionService.crearSancion(new Sancion(1L, new Resultado(), TEST,1,"1"), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}
}
