package com.races.portal.controller;

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
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Sesion;
import com.races.portal.services.ResultadoService;
import com.races.portal.services.SesionGpService;
import com.races.portal.services.TipoSesionService;

public class ResultadoControllerTest {
	private static final Object JWT = "jwt-test";

	private static final Object USER = "user-test";

	@Mock
	Environment env;

	@Mock
	ResultadoService resultados;

	@Mock
	TipoSesionService tipoSesiones;

	@Mock
	SesionGpService sesionesGp;

	@InjectMocks
	ResultadoController resultadoController;

	private MockMvc mockMvc;

	String base = "/races/sesion";

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(resultadoController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void listaResultadosTest() {
		try {
			Mockito.when(resultados.buscarResultados(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());
			Mockito.when(sesionesGp.buscarSesion(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new Sesion());
			mockMvc.perform(get(base+"/1/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
