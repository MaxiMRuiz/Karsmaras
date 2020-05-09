package com.races.portal.controller;

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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Campeonato;
import com.races.portal.dto.GranPremio;
import com.races.portal.services.CampeonatoService;
import com.races.portal.services.ClasificacionService;
import com.races.portal.services.GranPremioService;

@RunWith(SpringRunner.class)
@WebMvcTest(ClasificacionController.class)
public class ClasificacionControllerTest {

	@MockBean
	CampeonatoService campeonatoService;

	@MockBean
	ClasificacionService clasificacionService;

	@MockBean
	GranPremioService gpService;

	@InjectMocks
	ClasificacionController clasificacionController;

	@Autowired
	MockMvc mockMvc;

	String base = "/races/clasificacion";
	
	private static final Object JWT = "jwt-test";

	private static final Object USER = "user-test";

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
			mockMvc.perform(
					get(base + "/gp/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
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
			mockMvc.perform(get(base + "/campeonato/1").sessionAttr(Constants.JWT_ATTR, JWT)
					.sessionAttr(Constants.USER_ATTR, USER)).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
