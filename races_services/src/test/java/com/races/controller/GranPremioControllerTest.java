package com.races.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.races.entity.GranPremio;
import com.races.exception.RacesException;
import com.races.services.JwtService;
import com.races.services.PilotoService;
import com.races.services.impl.GranPremioServiceImpl;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@WebMvcTest(GranPremioController.class)
public class GranPremioControllerTest {
	private static final String TEST = "test";

	@MockBean
	GranPremioServiceImpl gpService;

	@MockBean
	PilotoService pilotoService;

	@MockBean
	JwtService jwtService;
	
	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	GranPremioController controller;

	String base = "/gp";

	String baseId = base + "/1";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void crearCampeonatoTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("ubicacion", TEST);
			body.put("fecha", "2020-01-01");
			body.put("idCampeonato", 1L);

			Mockito.when(gpService.crearGranPremio(Mockito.any())).thenReturn(new GranPremio());

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void buscarCampeonatosTest() {

		try {

			Mockito.when(gpService.buscarGrandesPremios(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());

			mockMvc.perform(get(base).param("ubicacion", TEST).param("idCampeonato", "1").param("id", "1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarCampeonatoTest() {

		try {

			Mockito.when(gpService.borrarGranPremio(Mockito.any())).thenReturn(true);

			mockMvc.perform(delete(baseId)).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void crearCampeonatoExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("ubicacion", TEST);
			body.put("fecha", "2020-01-01");
			body.put("idCampeonato", 1L);

			Mockito.when(gpService.crearGranPremio(Mockito.any())).thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().isConflict()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarCampeonatoExceptionTest() {

		try {

			Mockito.when(gpService.borrarGranPremio(Mockito.any())).thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(delete(baseId)).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
