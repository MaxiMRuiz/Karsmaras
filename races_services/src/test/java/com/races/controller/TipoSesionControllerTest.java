package com.races.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.races.services.JwtService;
import com.races.services.PilotoService;
import com.races.services.impl.TipoSesionServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(TipoSesionController.class)
public class TipoSesionControllerTest {
	private static final String TEST = "test";

	@MockBean
	TipoSesionServiceImpl tipoSesionService;

	@MockBean
	PilotoService pilotoService;
	
	@MockBean
	JwtService jwtService;
	
	@Autowired
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
