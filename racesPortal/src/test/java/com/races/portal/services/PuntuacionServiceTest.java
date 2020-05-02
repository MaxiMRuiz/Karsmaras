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
import com.races.portal.dto.Puntuacion;
import com.races.portal.dto.Sesion;
import com.races.portal.dto.TipoSesion;
import com.races.portal.services.impl.PuntuacionServiceImpl;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class PuntuacionServiceTest {
	private static final String TEST = "test";

	@Mock
	Utils utils;

	@Mock
	ObjectMapper mapper;

	@Mock
	Environment env;

	@InjectMocks
	PuntuacionServiceImpl puntuacionService;

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
	public void buscarPuntuacionesTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(Puntuacion.class)))
					.thenReturn(new Puntuacion());

			assertNotNull(puntuacionService.buscarPuntuaciones(TEST, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarPuntuacionesFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(puntuacionService.buscarPuntuaciones(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarPuntuacionTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(puntuacionService.borrarPuntuacion(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarPuntuacionFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(puntuacionService.borrarPuntuacion(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarPuntuacionTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(Puntuacion.class)))
					.thenReturn(new Puntuacion());

			assertNotNull(puntuacionService.buscarPuntuacion(TEST, TEST, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarPuntuacionFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(puntuacionService.buscarPuntuacion(TEST, TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void editarPuntuacionTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(puntuacionService.editarPuntuacion(1L,
					new Puntuacion(1L, 1, 1, new TipoSesion(), new Sesion()), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void editarPuntuacionFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(puntuacionService.editarPuntuacion(1L,
					new Puntuacion(1L, 1, 1, new TipoSesion(), new Sesion()), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearPuntuacionTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(puntuacionService.crearPuntuacion(1L,
					new Puntuacion(1L, 1, 1, new TipoSesion(), new Sesion()), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearPuntuacionFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(puntuacionService.crearPuntuacion(1L,
					new Puntuacion(1L, 1, 1, new TipoSesion(), new Sesion()), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}
}
