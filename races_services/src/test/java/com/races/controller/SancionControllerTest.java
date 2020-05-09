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

import com.races.entity.Sancion;
import com.races.exception.RacesException;
import com.races.services.impl.SancionServiceImpl;

import net.minidev.json.JSONObject;

public class SancionControllerTest {
	private static final String TEST = "test";

	@Mock
	SancionServiceImpl campeonatoService;

	@MockBean
	MockMvc mockMvc;

	@InjectMocks
	SancionController controller;

	String base = "/sancion";

	String baseId = base + "/1";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void crearSancionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("idResultado", 1L);
			body.put("descripcion", TEST);
			body.put("puntos", 1);
			body.put("tiempo", 1);

			Mockito.when(campeonatoService.crearSancion(Mockito.any())).thenReturn(new Sancion());

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void buscarSancionesTest() {

		try {

			Mockito.when(campeonatoService.buscarSanciones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());

			mockMvc.perform(
					get(base).param("idResultado", "1").param("puntos", "1").param("id", "1").param("tiempo", "1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void actualizarCampeonatoTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("idResultado", 1L);
			body.put("descripcion", TEST);
			body.put("puntos", 1);
			body.put("tiempo", 1);

			Mockito.when(campeonatoService.editarSancion(Mockito.any(), Mockito.any())).thenReturn(new Sancion());

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarCampeonatoTest() {

		try {

			Mockito.when(campeonatoService.borrarSancion(Mockito.any())).thenReturn(true);

			mockMvc.perform(delete(baseId)).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void crearCampeonatoExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("idResultado", 1L);
			body.put("descripcion", TEST);
			body.put("puntos", 1);
			body.put("tiempo", 1);

			Mockito.when(campeonatoService.crearSancion(Mockito.any()))
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
			body.put("idResultado", 1L);
			body.put("descripcion", TEST);
			body.put("puntos", 1);
			body.put("tiempo", 1);

			Mockito.when(campeonatoService.editarSancion(Mockito.any(), Mockito.any()))
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

			Mockito.when(campeonatoService.borrarSancion(Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(delete(baseId)).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
