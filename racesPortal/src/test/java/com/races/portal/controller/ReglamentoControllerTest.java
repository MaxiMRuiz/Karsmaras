package com.races.portal.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.races.portal.dto.Reglamento;
import com.races.portal.services.ReglamentoService;

public class ReglamentoControllerTest {
	private static final Object JWT = "jwt-test";

	private static final Object USER = "user-test";

	private static final String TEST = "Test";

	@Mock
	ReglamentoService reglamentos;

	@InjectMocks
	ReglamentoController reglamentoController;

	private MockMvc mockMvc;

	String base = "/races/reglamentos";

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(reglamentoController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void listaReglamentosTest() {
		try {
			Mockito.when(reglamentos.buscarReglamentos(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
			mockMvc.perform(get(base).sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioReglamentosTest() {
		try {
			mockMvc.perform(
					get(base + "/new").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioEquiposEditarTest() {
		try {
			Mockito.when(reglamentos.buscarReglamento(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new Reglamento());
			mockMvc.perform(
					get(base + "/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarEquipoTest() {
		try {
			Mockito.when(reglamentos.borrarReglamento(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(
					delete(base + "/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void postFormularioEquiposTest() {
		try {
			Mockito.when(reglamentos.crearReglamento(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(post(base).flashAttr("campeonato", new Reglamento(1L, TEST, 1L, 1L, 1L, 1L, 1L))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void postFormularioEquiposEditarTest() {
		try {
			Mockito.when(reglamentos.editarReglamento(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(post(base).flashAttr("reglamento", new Reglamento()).sessionAttr(Constants.JWT_ATTR, JWT)
					.sessionAttr(Constants.USER_ATTR, USER)).andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
