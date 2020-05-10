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

import com.races.entity.Equipo;
import com.races.exception.RacesException;
import com.races.services.JwtService;
import com.races.services.PilotoService;
import com.races.services.impl.EquipoServiceImpl;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@WebMvcTest(EquipoController.class)
public class EquipoControllerTest {
	private static final String TEST = "test";

	@MockBean
	EquipoServiceImpl equipoService;

	@MockBean
	PilotoService pilotoService;
	
	@MockBean
	JwtService jwtService;
	
	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	EquipoController controller;

	String base = "/equipo";

	String baseId = base + "/1";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void crearEquipoTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("alias", TEST);

			Mockito.when(equipoService.crearEquipo(Mockito.any())).thenReturn(new Equipo());

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void buscarEquipoTest() {

		try {

			Mockito.when(equipoService.buscarEquipos(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());

			mockMvc.perform(get(base).param("nombre", TEST).param("alias", TEST).param("id", "1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void actualizarEquipoTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("alias", TEST);

			Mockito.when(equipoService.actualizarEquipo(Mockito.any(), Mockito.any())).thenReturn(new Equipo());

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarEquipoTest() {

		try {

			Mockito.when(equipoService.borrarEquipo(Mockito.any())).thenReturn(true);

			mockMvc.perform(delete(baseId)).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void crearEquipoExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("descripcion", TEST);
			body.put("temporada", TEST);
			body.put("reglamento", 1L);

			Mockito.when(equipoService.crearEquipo(Mockito.any())).thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().isConflict()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void actualizarEquipoExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("descripcion", TEST);
			body.put("temporada", TEST);
			body.put("reglamento", 1L);

			Mockito.when(equipoService.actualizarEquipo(Mockito.any(), Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarEquipoExceptionTest() {

		try {

			Mockito.when(equipoService.borrarEquipo(Mockito.any())).thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(delete(baseId)).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
