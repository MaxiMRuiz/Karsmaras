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
import com.races.portal.dto.Campeonato;
import com.races.portal.dto.GranPremio;
import com.races.portal.services.CampeonatoService;
import com.races.portal.services.ClasificacionService;
import com.races.portal.services.GranPremioService;

public class ClasificacionControllerTest {
	private static final Object JWT = "jwt-test";

	private static final Object USER = "user-test";

	@Mock
	CampeonatoService campeonatoService;

	@Mock
	ClasificacionService clasificacionService;

	@Mock
	GranPremioService gpService;

	@InjectMocks
	ClasificacionController clasificacionController;

	private MockMvc mockMvc;

	String base = "/races/clasificacion";

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(clasificacionController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void clasificacionGPTest() {
		try {
			Mockito.when(clasificacionService.clasificacionGp(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());
			Mockito.when(gpService.buscarGranPremio(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new GranPremio());
			mockMvc.perform(get(base+"/gp/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void clasificacionCampeonatoTest() {
		try {
			Mockito.when(clasificacionService.clasificacionCampeonato(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());
			Mockito.when(campeonatoService.buscarCampeonato(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new Campeonato());
			mockMvc.perform(
					get(base + "/campeonato/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
