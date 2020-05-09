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

import com.races.services.impl.TipoSesionServiceImpl;

public class TipoSesionControllerTest {
	private static final String TEST = "test";

	@Mock
	TipoSesionServiceImpl tipoSesionService;

	@MockBean
	MockMvc mockMvc;

	@InjectMocks
	TipoSesionController controller;

	String base = "/tsesion";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void buscarTipoSesionesTest() {

		try {

			Mockito.when(tipoSesionService.buscarTipoSesiones(Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());

			mockMvc.perform(get(base).param("descripcion", TEST).param("id", "1")).andExpect(status().is2xxSuccessful())
					.andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
