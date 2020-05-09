package com.races.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.races.entity.Reglamento;
import com.races.exception.RacesException;
import com.races.services.impl.ReglamentoServiceImpl;

import net.minidev.json.JSONObject;

public class ReglamentoControllerTest {
	private static final String TEST = "test";

	@Mock
	ReglamentoServiceImpl reglamentoService;

	@MockBean
	MockMvc mockMvc;

	@InjectMocks
	ReglamentoController controller;

	String base = "/reglamento";

	String baseId = base + "/1";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void crearReglamentoTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("descripcion", TEST);
			body.put("nEntrenamientos", 1);
			body.put("nClasificaciones", 1);
			body.put("nCarreras", 1);
			body.put("nPilotos", 1);
			body.put("nEquipos", 1);

			Mockito.when(reglamentoService.crearReglamento(Mockito.any())).thenReturn(new Reglamento());

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void buscarReglamentosTest() {

		try {

			Mockito.when(reglamentoService.buscarReglamentos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());

			mockMvc.perform(
					get(base).param("nEntrenamientos", "1").param("descripcion", TEST).param("nClasificaciones", "1")
							.param("nCarreras", "1").param("nPilotos", "1").param("nEquipos", "1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void actualizarReglamentoTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("descripcion", TEST);
			body.put("nEntrenamientos", 1);
			body.put("nClasificaciones", 1);
			body.put("nCarreras", 1);
			body.put("nPilotos", 1);
			body.put("nEquipos", 1);

			Mockito.when(reglamentoService.actualizarReglamento(Mockito.any(), Mockito.any()))
					.thenReturn(new Reglamento());

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarCampeonatoTest() {

		try {

			Mockito.when(reglamentoService.borrarReglamento(Mockito.any())).thenReturn(true);

			mockMvc.perform(delete(baseId)).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void crearCampeonatoExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("descripcion", TEST);
			body.put("nEntrenamientos", 1);
			body.put("nClasificaciones", 1);
			body.put("nCarreras", 1);
			body.put("nPilotos", 1);
			body.put("nEquipos", 1);

			Mockito.when(reglamentoService.crearReglamento(Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().isConflict()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void actualizarCampeonatoExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("descripcion", TEST);
			body.put("nEntrenamientos", 1);
			body.put("nClasificaciones", 1);
			body.put("nCarreras", 1);
			body.put("nPilotos", 1);
			body.put("nEquipos", 1);

			Mockito.when(reglamentoService.actualizarReglamento(Mockito.any(), Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarCampeonatoExceptionTest() {

		try {

			Mockito.when(reglamentoService.borrarReglamento(Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(delete(baseId)).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
