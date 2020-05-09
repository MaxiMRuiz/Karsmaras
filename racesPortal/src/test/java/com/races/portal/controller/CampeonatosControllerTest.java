package com.races.portal.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.races.portal.dto.Reglamento;
import com.races.portal.services.CampeonatoService;
import com.races.portal.services.ReglamentoService;

@RunWith(SpringRunner.class)
@WebMvcTest(CampeonatosController.class)
public class CampeonatosControllerTest {

	@MockBean
	CampeonatoService campeonatos;

	@MockBean
	ReglamentoService reglamentos;

	@InjectMocks
	CampeonatosController campeonatosController;

	@Autowired
	MockMvc mockMvc;

	private static final Object JWT = "jwt-test";

	private static final Object USER = "user-test";

	private static final String TEST = "Test";

	String base = "/races/campeonatos";

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(campeonatosController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void listaCampeonatosTest() {
		try {
			Mockito.when(campeonatos.buscarCampeonatos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
			mockMvc.perform(get(base).sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioCampeonatosCrearTest() {
		try {
			Mockito.when(reglamentos.buscarReglamentos(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
			mockMvc.perform(
					get(base + "/new").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioCampeonatosEditarTest() {
		try {
			Mockito.when(campeonatos.buscarCampeonato(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new Campeonato());
			Mockito.when(reglamentos.buscarReglamentos(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
			mockMvc.perform(
					get(base + "/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarCampeonatoTest() {
		try {
			Mockito.when(campeonatos.borrarCampeonato(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(
					delete(base + "/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void postFormularioCampeonatosTest() {
		try {
			Mockito.when(campeonatos.crearCampeonato(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(post(base).flashAttr("campeonato", new Campeonato(1L, TEST, TEST, TEST, new Reglamento()))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void postFormularioCampeonatosEditarTest() {
		try {
			Mockito.when(campeonatos.editarCampeonato(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			Mockito.when(reglamentos.buscarReglamentos(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
			mockMvc.perform(post(base).flashAttr("campeonato", new Campeonato()).sessionAttr(Constants.JWT_ATTR, JWT)
					.sessionAttr(Constants.USER_ATTR, USER)).andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
