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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Inscripcion;
import com.races.portal.dto.Piloto;
import com.races.portal.dto.Resultado;
import com.races.portal.dto.SesionGP;
import com.races.portal.services.ResultadoService;
import com.races.portal.services.VueltaService;

public class VueltaControllerTest {
	private static final Object JWT = "jwt-test";

	private static final Object USER = "user-test";

	@Mock
	ResultadoService resultados;

	@Mock
	VueltaService vueltas;

	@InjectMocks
	VueltaController vueltaController;

	private MockMvc mockMvc;

	String base = "/races/vueltas/1/1/1";

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(vueltaController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void listaEquiposTest() {
		try {
			Mockito.when(vueltas.buscarVueltas(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());
			Mockito.when(resultados.buscarResultado(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(
					new Resultado(1L, new Inscripcion(1L, new Piloto(), null, null), new SesionGP(), 1, 1, 1));
			mockMvc.perform(get(base).sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
