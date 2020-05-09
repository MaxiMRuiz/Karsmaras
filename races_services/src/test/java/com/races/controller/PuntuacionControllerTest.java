package com.races.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.races.entity.Puntuacion;
import com.races.exception.RacesException;
import com.races.services.impl.PuntuacionServiceImpl;

import net.minidev.json.JSONObject;

public class PuntuacionControllerTest {
	private static final String TEST = "test";

	@Mock
	PuntuacionServiceImpl puntuacionService;

	@MockBean
	MockMvc mockMvc;

	@InjectMocks
	PuntuacionController controller;

	String base = "/puntuacion";

	String baseId = base + "/1";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void buscarPuntuacionTest() {

		try {

			Mockito.when(
					puntuacionService.buscarPuntuaciones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());

			mockMvc.perform(
					get(base).param("posicion", "1").param("idSesion", "1").param("id", "1").param("puntos", "1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void actualizarPuntuacionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("idSesion", 1);
			body.put("posicion", 1);
			body.put("puntos", 1);

			Mockito.when(puntuacionService.actualizarPuntuacion(Mockito.any(), Mockito.any()))
					.thenReturn(new Puntuacion());

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void uploadFileHandlerTest() {

		try {
			MockMultipartFile multipartFile = new MockMultipartFile("data", "test.txt", "multipart/form-data",
					"TEST:test:test".getBytes());
			Mockito.doNothing().when(puntuacionService).processFile(Mockito.any(), Mockito.any());

			mockMvc.perform(multipart(base + "/upload/1").file("file", multipartFile.getBytes()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void actualizarPuntuacionExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("idSesion", 1);
			body.put("posicion", 1);
			body.put("puntos", 1);

			Mockito.when(puntuacionService.actualizarPuntuacion(Mockito.any(), Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	public void uploadFileHandlerExceptionTest() {

		try {
			MockMultipartFile multipartFile = new MockMultipartFile("data", "test.txt", "multipart/form-data",
					"TEST:test:test".getBytes());
			Mockito.doThrow(new RacesException("Simulated Error")).when(puntuacionService).processFile(Mockito.any(), Mockito.any());

			mockMvc.perform(multipart(base + "/upload/1").file("file", multipartFile.getBytes()))
					.andExpect(status().isInternalServerError()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
