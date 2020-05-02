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
import com.races.portal.services.SesionService;

public class SesionControllerTest {
	private static final Object JWT = "jwt-test";

	private static final Object USER = "user-test";


	@Mock
	SesionService sesiones;

	@InjectMocks
	SesionController sesionController;

	private MockMvc mockMvc;

	String base = "/races/sesiones/1";

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(sesionController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void listaEquiposTest() {
		try {
			Mockito.when(sesiones.buscarSesiones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());
			mockMvc.perform(get(base).sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
