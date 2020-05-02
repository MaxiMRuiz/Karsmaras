package com.races.portal.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import com.races.portal.dto.GranPremio;
import com.races.portal.services.impl.GranPremioServiceImpl;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class GranPremioServiceTest {
	private static final String TEST = "test";

	@Mock
	Utils utils;

	@Mock
	Converter converter;

	@Mock
	Environment env;

	@InjectMocks
	GranPremioServiceImpl gpService;

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
	public void buscarGrandesPremiosTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(converter.json2Gp(Mockito.any())).thenReturn(new GranPremio());

			assertNotNull(gpService.buscarGrandesPremios(TEST, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarGrandesPremiosFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(gpService.buscarGrandesPremios(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarGPTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			assertNotNull(gpService.borrarGP(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarGPFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(gpService.borrarGP(TEST, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarGranPremioTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(converter.json2Gp(Mockito.any())).thenReturn(new GranPremio());

			assertNotNull(gpService.buscarGranPremio(1L, TEST, TEST));
		} catch (UnirestException | IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void buscarGranPremioFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			assertNotNull(gpService.buscarGranPremio(1L, TEST, TEST));
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearGranPremioTests() {
		try {
			array.put(obj);
			Mockito.when(mockedResponse.getBody()).thenReturn(array.toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			gpService.crearGranPremio(TEST, new GranPremio(1L, TEST, TEST), TEST, TEST);
			assertTrue(true);
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearGranPremioFailTests() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(400);
			gpService.crearGranPremio(TEST, new GranPremio(1L, TEST, TEST), TEST, TEST);
			assertTrue(true);
		} catch (UnirestException e) {
			fail(e.getMessage());
		}
	}
}
