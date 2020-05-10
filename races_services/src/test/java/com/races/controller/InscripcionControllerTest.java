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

import com.races.entity.Inscripcion;
import com.races.exception.RacesException;
import com.races.services.JwtService;
import com.races.services.PilotoService;
import com.races.services.impl.InscripcionServiceImpl;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@WebMvcTest(InscripcionController.class)
public class InscripcionControllerTest {

	@MockBean
	InscripcionServiceImpl inscriptionService;

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	PilotoService pilotoService;
	
	@MockBean
	JwtService jwtService;

	@InjectMocks
	InscripcionController controller;

	String base = "/inscripcion";

	String baseId = base + "/1";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void crearInscripcionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("idCampeonato", 1L);
			body.put("idPiloto", 1L);
			body.put("idEquipo", 1L);

			Mockito.when(inscriptionService.crearInscripcion(Mockito.any())).thenReturn(new Inscripcion());

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void buscarInscripcionTest() {

		try {

			Mockito.when(inscriptionService.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());

			mockMvc.perform(get(base).param("campeonato", "1").param("piloto", "1").param("equipo", "1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarCampeonatoTest() {

		try {

			Mockito.when(inscriptionService.borrarInscripcion(Mockito.any())).thenReturn(true);

			mockMvc.perform(delete(baseId)).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void crearCampeonatoExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("idCampeonato", 1L);
			body.put("idPiloto", 1L);
			body.put("idEquipo", 1L);

			Mockito.when(inscriptionService.crearInscripcion(Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().isConflict()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarCampeonatoExceptionTest() {

		try {

			Mockito.when(inscriptionService.borrarInscripcion(Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(delete(baseId)).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
