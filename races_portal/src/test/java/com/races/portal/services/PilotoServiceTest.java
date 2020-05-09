package com.races.portal.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.races.portal.component.Utils;
import com.races.portal.dto.Password;
import com.races.portal.dto.Piloto;
import com.races.portal.services.impl.PilotoServiceImpl;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
public class PilotoServiceTest {
	private static final String TEST = "test";

	@Mock
	Utils utils;

	@Mock
	ObjectMapper mapper;

	@Mock
	Environment env;

	@InjectMocks
	PilotoServiceImpl pilotoService;

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
	public void buscarPilotosTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(Piloto.class))).thenReturn(new Piloto());

			assertNotNull(pilotoService.buscarPilotos(1L, TEST, TEST, TEST, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarPilotosFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(pilotoService.buscarPilotos(1L, TEST, TEST, TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarPilotoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(pilotoService.borrarPiloto(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarPilotoFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(pilotoService.borrarPiloto(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarPilotoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(Piloto.class))).thenReturn(new Piloto());

			assertNotNull(pilotoService.buscarPilotos(TEST, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarPilotoFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(pilotoService.buscarPilotos(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void editarPilotoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(Piloto.class))).thenReturn(new Piloto());
			assertNotNull(pilotoService.editarPiloto(new Piloto(1L, TEST, TEST, TEST, TEST, true), TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void editarPilotoFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNull(pilotoService.editarPiloto(new Piloto(1L, TEST, TEST, TEST, TEST, true), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearPilotoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(pilotoService.crearPiloto(new Piloto(1L, TEST, TEST, TEST, TEST, true)));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearPilotoFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(pilotoService.crearPiloto(new Piloto(1L, TEST, TEST, TEST, TEST, true)));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void promocionarPilotoTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(pilotoService.promocionarPiloto(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void promocionarPilotoFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(pilotoService.promocionarPiloto(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cambiarPasswordTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(pilotoService.cambiarPassword(new Password(1L, TEST, TEST), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cambiarPasswordFailTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(pilotoService.cambiarPassword(new Password(1L, TEST, TEST), TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}
}
