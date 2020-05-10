package com.races.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.races.exception.RacesException;
import com.races.services.JwtService;
import com.races.services.PilotoService;
import com.races.services.impl.ResultadoServiceImpl;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@WebMvcTest(ResultadoController.class)
public class ResultadoControllerTest {
	private static final String TEST = "test";

	@MockBean
	ResultadoServiceImpl resultadoService;

	@MockBean
	PilotoService pilotoService;
	
	@MockBean
	JwtService jwtService;
	
	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	ResultadoController controller;

	String base = "/resultado";

	String baseId = base + "/1";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void buscarResultadosTest() {

		try {

			Mockito.when(resultadoService.buscarResultados(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any())).thenReturn(new ArrayList<>());

			mockMvc.perform(get(base).param("idInscripcion", "1").param("idSesion", "1").param("id", "1")
					.param("nVueltas", "1").param("tiempo", "1")).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void actualizarResultadoTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("idInscripcion", 1L);
			body.put("idSesion", 1L);
			body.put("nVueltas", 1);
			body.put("tiempo", 1);

			Mockito.when(resultadoService.actualizarResultado(Mockito.any(), Mockito.any())).thenReturn(true);

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void uploadFileHandlerTest() {

		try {
			MockMultipartFile multipartFile = new MockMultipartFile("data", "test.txt", "multipart/form-data", "TEST:test:test".getBytes());		
			Mockito.doNothing().when(resultadoService).processFile(Mockito.any(), Mockito.any());

			mockMvc.perform(multipart(base + "/load/1").file("file", multipartFile.getBytes())).andExpect(status().is2xxSuccessful()).andReturn();
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

			Mockito.when(resultadoService.actualizarResultado(Mockito.any(), Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().isConflict()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
