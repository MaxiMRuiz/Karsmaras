package com.races.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.races.services.impl.SesionServiceImpl;

public class SesionControllerTest {
	private static final String TEST = "test";

	@Mock
	SesionServiceImpl sesionService;

	@MockBean
	MockMvc mockMvc;

	@InjectMocks
	SesionController controller;

	String base = "/sesion";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void buscarCampeonatosTest() {

		try {

			Mockito.when(sesionService.buscarSesiones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());

			mockMvc.perform(get(base).param("idReglamento", "1").param("descripcion", TEST).param("id", "1")
					.param("idTipoSesion", "1")).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
