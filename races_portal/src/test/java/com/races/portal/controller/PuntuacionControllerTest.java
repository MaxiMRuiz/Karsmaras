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
import com.races.portal.dto.Puntuacion;
import com.races.portal.dto.Sesion;
import com.races.portal.dto.TipoSesion;
import com.races.portal.services.PuntuacionService;
import com.races.portal.services.TipoSesionService;

@RunWith(SpringRunner.class)
@WebMvcTest(PuntuacionController.class)
public class PuntuacionControllerTest {

	@MockBean
	PuntuacionService puntuaciones;

	@MockBean
	TipoSesionService tipoSesiones;

	@InjectMocks
	PuntuacionController puntuacionController;

	@Autowired
	MockMvc mockMvc;

	private static final Object JWT = "jwt-test";

	private static final Object USER = "user-test";

	String base = "/races/puntuaciones";

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(puntuacionController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void listaPuntuacionesTest() {
		try {
			Mockito.when(puntuaciones.buscarPuntuaciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());
			mockMvc.perform(
					get(base + "/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioPuntuacionesTest() {
		try {
			Mockito.when(tipoSesiones.buscarTiposSesiones(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
			mockMvc.perform(
					get(base + "/1/new").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioPuntuacionesEditarTest() {
		try {
			Mockito.when(tipoSesiones.buscarTiposSesiones(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(puntuaciones.buscarPuntuacion(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new Puntuacion());
			mockMvc.perform(
					get(base + "/1/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarPuntuacionTest() {
		try {
			Mockito.when(puntuaciones.borrarPuntuacion(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(
					delete(base + "/1/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void postFormularioPuntuacionTest() {
		try {
			Mockito.when(puntuaciones.editarPuntuacion(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(true);
			mockMvc.perform(
					post(base + "/1").flashAttr("puntuacion", new Puntuacion(1L, 1, 1, new TipoSesion(), new Sesion()))
							.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void postFormularioPuntuacionEditarTest() {
		try {
			Mockito.when(puntuaciones.crearPuntuacion(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(true);
			mockMvc.perform(post(base + "/1").flashAttr("puntuacion", new Puntuacion())
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
