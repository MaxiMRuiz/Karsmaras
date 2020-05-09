package com.races.portal.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.races.portal.dto.Piloto;
import com.races.portal.services.PilotoService;

@RunWith(SpringRunner.class)
@WebMvcTest(PilotosController.class)
public class PilotosControllerTest {

	@MockBean
	PilotoService pilotos;

	@InjectMocks
	PilotosController pilotosController;

	@Autowired
	MockMvc mockMvc;

	private static final Object JWT = "jwt-test";

	private static final Object USER = "user-test";

	private static final String TEST = "Test";

	String base = "/races/pilotos";

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(pilotosController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void listaPilotosTest() {
		try {
			Mockito.when(pilotos.buscarPilotos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
			mockMvc.perform(get(base).sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioPilotosTest() {
		try {
			mockMvc.perform(
					get(base + "/new").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioPilotosEditarTest() {
		try {
			Mockito.when(pilotos.buscarPilotos(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new Piloto());
			mockMvc.perform(
					get(base + "/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarPilotoTest() {
		try {
			Mockito.when(pilotos.borrarPiloto(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(
					delete(base + "/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void promocionarPilotoTest() {
		try {
			Mockito.when(pilotos.promocionarPiloto(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(
					put(base + "/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void postFormularioPilotosTest() {
		try {
			Mockito.when(pilotos.crearPiloto(Mockito.any())).thenReturn(true);
			mockMvc.perform(post(base).flashAttr("piloto", new Piloto(1L, TEST, TEST, TEST, TEST, true))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void perfilTest() {
		try {
			mockMvc.perform(get(base + "/perfil")
					.sessionAttr(Constants.PILOTO_ATTR, new Piloto(1L, TEST, TEST, TEST, TEST, true))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void postPerfilTest() {
		try {
			Mockito.when(pilotos.editarPiloto(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new Piloto());
			mockMvc.perform(post(base + "/perfil")
					.sessionAttr(Constants.PILOTO_ATTR, new Piloto(1L, TEST, TEST, TEST, TEST, true))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void postPerfilFailTest() {
		try {
			mockMvc.perform(post(base + "/perfil")
					.sessionAttr(Constants.PILOTO_ATTR, new Piloto(1L, TEST, TEST, TEST, TEST, true))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cambioPasswordTest() {
		try {
			mockMvc.perform(get(base + "/password").sessionAttr(Constants.PILOTO_ATTR,
					new Piloto(1L, TEST, TEST, TEST, TEST, true))).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cambioPasswordPostTest() {
		try {
			Mockito.when(pilotos.cambiarPassword(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(post(base + "/password")
					.sessionAttr(Constants.PILOTO_ATTR, new Piloto(1L, TEST, TEST, TEST, TEST, true))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cambioPasswordPostFailTest() {
		try {
			Mockito.when(pilotos.cambiarPassword(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(false);
			mockMvc.perform(post(base + "/password")
					.sessionAttr(Constants.PILOTO_ATTR, new Piloto(1L, TEST, TEST, TEST, TEST, true))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
