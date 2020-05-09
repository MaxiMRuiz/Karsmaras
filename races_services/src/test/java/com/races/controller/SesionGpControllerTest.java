package com.races.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.races.entity.SesionGP;
import com.races.exception.RacesException;
import com.races.services.impl.SesionGpServiceImpl;

import net.minidev.json.JSONObject;

public class SesionGpControllerTest {
	private static final String TEST = "test";

	@Mock
	SesionGpServiceImpl sesionGpService;

	@MockBean
	MockMvc mockMvc;

	@InjectMocks
	SesionGpController controller;

	String base = "/sesionGP";

	String baseId = base + "/1";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void buscarSesionesTest() {

		try {

			Mockito.when(sesionGpService.buscarSesiones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());

			mockMvc.perform(get(base).param("nombre", TEST).param("descripcion", TEST).param("id", "1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void editarSesionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("fecha", "2021-05-03");

			Mockito.when(sesionGpService.updateSesionGp(Mockito.any(), Mockito.any())).thenReturn(new SesionGP());

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void editarSesionExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("fecha", "2021-05-03");

			Mockito.when(sesionGpService.updateSesionGp(Mockito.any(), Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().isConflict()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
