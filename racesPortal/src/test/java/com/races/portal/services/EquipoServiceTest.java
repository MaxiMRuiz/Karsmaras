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
import com.races.portal.dto.Equipo;
import com.races.portal.services.impl.EquipoServiceImpl;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class EquipoServiceTest {
	private static final String TEST = "test";

	@Mock
	Utils utils;

	@Mock
	ObjectMapper mapper;

	@Mock
	Environment env;

	@InjectMocks
	EquipoServiceImpl equipoService;

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
	public void buscarEquiposTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(Equipo.class))).thenReturn(new Equipo());

			assertNotNull(equipoService.buscarEquipos(1L, TEST, TEST, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarEquiposFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(equipoService.buscarEquipos(1L, TEST, TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarEquipoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(equipoService.borrarEquipo(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarEquipoFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(equipoService.borrarEquipo(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarEquipoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(Equipo.class))).thenReturn(new Equipo());

			assertNotNull(equipoService.buscarEquipos(TEST, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarEquipoFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(equipoService.buscarEquipos(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void editarEquipoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(equipoService.editarEquipo(new Equipo(1L, TEST, TEST), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void editarEquipoFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(equipoService.editarEquipo(new Equipo(1L, TEST, TEST), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearEquipoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(equipoService.crearEquipo(new Equipo(1L, TEST, TEST), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearEquipoFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(equipoService.crearEquipo(new Equipo(1L, TEST, TEST), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}
}
