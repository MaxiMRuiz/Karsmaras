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

import com.races.entity.Campeonato;
import com.races.exception.RacesException;
import com.races.services.CampeonatoService;
import com.races.services.JwtService;
import com.races.services.PilotoService;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@WebMvcTest(CampeonatoController.class)
public class CampeonatoControllerTest {

	private static final String TEST = "test";

	@MockBean
	CampeonatoService campeonatoService;
	
	@MockBean
	PilotoService pilotoService;
	
	@MockBean
	JwtService jwtService;

	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	CampeonatoController controller;

	String base = "/campeonato";

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
			body.put("nombre", TEST);
			body.put("descripcion", TEST);
			body.put("temporada", TEST);
			body.put("reglamento", 1L);

			Mockito.when(campeonatoService.crearCampeonato(Mockito.any())).thenReturn(new Campeonato());

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void buscarCampeonatosTest() {

		try {

			Mockito.when(campeonatoService.buscarCampeonatos(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());

			mockMvc.perform(get(base).param("nombre", TEST).param("descripcion", TEST).param("id", "1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void actualizarCampeonatoTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("descripcion", TEST);
			body.put("temporada", TEST);
			body.put("reglamento", 1L);

			Mockito.when(campeonatoService.actualizarCampeonato(Mockito.any(), Mockito.any()))
					.thenReturn(new Campeonato());

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarCampeonatoTest() {

		try {

			Mockito.when(campeonatoService.borrarCampeonato(Mockito.any()))
					.thenReturn(true);

			mockMvc.perform(delete(baseId)).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void crearCampeonatoExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("descripcion", TEST);
			body.put("temporada", TEST);
			body.put("reglamento", 1L);

			Mockito.when(campeonatoService.crearCampeonato(Mockito.any()))
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
			body.put("nombre", TEST);
			body.put("descripcion", TEST);
			body.put("temporada", TEST);
			body.put("reglamento", 1L);

			Mockito.when(campeonatoService.actualizarCampeonato(Mockito.any(), Mockito.any()))
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

			Mockito.when(campeonatoService.borrarCampeonato(Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(delete(baseId)).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
