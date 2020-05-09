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
import com.races.portal.dto.Inscripcion;
import com.races.portal.dto.Piloto;
import com.races.portal.dto.Resultado;
import com.races.portal.dto.Sancion;
import com.races.portal.dto.SesionGP;
import com.races.portal.services.impl.ResultadoServiceImpl;
import com.races.portal.services.impl.SancionServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(SancionController.class)
public class SancionControllerTest {

	@MockBean
	SancionServiceImpl sanciones;

	@MockBean
	ResultadoServiceImpl resultados;

	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	SancionController sancionController;

	private static final Object JWT = "jwt-test";

	private static final Object USER = "user-test";

	private static final String TEST = "Test";

	String base = "/races/sancion/1/1/1";

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(sancionController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void listaSancionesTest() {
		try {
			Mockito.when(sanciones.buscarSanciones(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());
			Mockito.when(resultados.buscarResultado(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(
					new Resultado(1L, new Inscripcion(1L, new Piloto(), null, null), new SesionGP(), 1, 1, 1));
			mockMvc.perform(get(base).sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioEquiposTest() {
		try {
			Mockito.when(resultados.buscarResultado(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(
					new Resultado(1L, new Inscripcion(1L, new Piloto(), null, null), new SesionGP(), 1, 1, 1));
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
			Mockito.when(resultados.buscarResultado(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(
					new Resultado(1L, new Inscripcion(1L, new Piloto(), null, null), new SesionGP(), 1, 1, 1));
			Mockito.when(sanciones.buscarSancion(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new Sancion());
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
			Mockito.when(sanciones.borrarSancion(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
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
			Mockito.when(sanciones.crearSancion(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(post(base).flashAttr("sancion", new Sancion(1L, new Resultado(), TEST, 1, TEST))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void postFormularioEquiposEditarTest() {
		try {
			Mockito.when(sanciones.editarSancion(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(post(base).flashAttr("sancion", new Sancion()).sessionAttr(Constants.JWT_ATTR, JWT)
					.sessionAttr(Constants.USER_ATTR, USER)).andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
