package com.races.portal.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.races.portal.dto.Equipo;
import com.races.portal.dto.Inscripcion;
import com.races.portal.dto.Piloto;
import com.races.portal.dto.Reglamento;
import com.races.portal.services.CampeonatoService;
import com.races.portal.services.EquipoService;
import com.races.portal.services.InscripcionService;
import com.races.portal.services.PilotoService;

public class InscripcionesControllerTest {
	private static final Object JWT = "jwt-test";

	private static final Object USER = "user-test";

	private static final String TEST = "Test";

	@Mock
	InscripcionService inscripciones;

	@Mock
	CampeonatoService campeonatos;

	@Mock
	PilotoService pilotos;

	@Mock
	EquipoService equipos;
	@InjectMocks
	InscripcionesController inscripcionesController;

	private MockMvc mockMvc;

	String base = "/races/inscripciones";

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(inscripcionesController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void listaInscripcionesCampeonatoTests() {
		try {
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(campeonatos.buscarCampeonato(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new Campeonato());
			mockMvc.perform(
					get(base + "/c1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void listaInscripcionesPilotoTests() {
		try {
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(pilotos.buscarPilotos(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new Piloto());
			mockMvc.perform(
					get(base + "/p1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void listaInscripcionesEquipoTests() {
		try {
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any())).thenReturn(new ArrayList<>());
			Mockito.when(equipos.buscarEquipos(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new Equipo());
			mockMvc.perform(
					get(base + "/e1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void listaInscripcionesFullTests() {
		try {
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any())).thenReturn(new ArrayList<>());
			mockMvc.perform(
					get(base + "/i").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioInscripcionesCampeonatoTest() {
		try {
			Piloto piloto = new Piloto(1L, TEST, TEST, TEST, TEST, true);
			Equipo equipo = new Equipo(1L, TEST, TEST);
			Campeonato campeonato = new Campeonato(1L, TEST, TEST, TEST, new Reglamento());
			Inscripcion inscripcion = new Inscripcion(1L, piloto, campeonato, equipo);
			List<Campeonato> listaCampeonatos = new ArrayList<>();
			List<Piloto> listaPilotos = new ArrayList<>();
			List<Equipo> listaEquipos = new ArrayList<>();
			List<Inscripcion> listInscripciones = new ArrayList<>();
			listInscripciones.add(inscripcion);
			listaCampeonatos.add(campeonato);
			listaPilotos.add(piloto);
			listaEquipos.add(equipo);
			Mockito.when(campeonatos.buscarCampeonatos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(listaCampeonatos);
			Mockito.when(pilotos.buscarPilotos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(listaPilotos);
			Mockito.when(
					equipos.buscarEquipos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
			.thenReturn(listaEquipos);
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any())).thenReturn(listInscripciones);
			Mockito.when(campeonatos.buscarCampeonato(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(campeonato);
			mockMvc.perform(get(base + "/c/1").sessionAttr(Constants.JWT_ATTR, JWT)
					.sessionAttr(Constants.USER_ATTR, USER).flashAttr("nombre", TEST))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioInscripcionesPilotoTest() {
		try {
			Mockito.when(pilotos.buscarPilotos(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new Piloto());
			Mockito.when(equipos.buscarEquipos(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new Equipo());
			Mockito.when(campeonatos.buscarCampeonato(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new Campeonato());
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any())).thenReturn(new ArrayList<>());
			mockMvc.perform(get(base + "/p/1").sessionAttr(Constants.JWT_ATTR, JWT)
					.sessionAttr(Constants.USER_ATTR, USER).flashAttr("nombre", TEST))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void formularioInscripcionesEquipoTest() {
		try {
			Mockito.when(pilotos.buscarPilotos(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new Piloto());
			Mockito.when(equipos.buscarEquipos(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new Equipo());
			Mockito.when(campeonatos.buscarCampeonato(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new Campeonato());
			Mockito.when(inscripciones.buscarInscripciones(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any())).thenReturn(new ArrayList<>());
			mockMvc.perform(get(base + "/e/1").sessionAttr(Constants.JWT_ATTR, JWT)
					.sessionAttr(Constants.USER_ATTR, USER).flashAttr("nombre", TEST))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void borrarInscripcionTest() {
		try {
			Mockito.when(inscripciones.borrarInscripcion(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			mockMvc.perform(
					delete(base + "/c/1").sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void crearInscripcionCampeonatoTest() {
		try {
			Mockito.doNothing().when(inscripciones).crearInscripcion(Mockito.any(), Mockito.any(), Mockito.any());
			mockMvc.perform(post(base + "/c/1")
					.flashAttr("inscripcion", new Inscripcion(1L, new Piloto(), new Campeonato(), new Equipo()))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void crearInscripcionPilotoTest() {
		try {
			Mockito.doNothing().when(inscripciones).crearInscripcion(Mockito.any(), Mockito.any(), Mockito.any());
			mockMvc.perform(post(base + "/p/1")
					.flashAttr("inscripcion", new Inscripcion(1L, new Piloto(), new Campeonato(), new Equipo()))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void crearInscripcionEquipoTest() {
		try {
			Mockito.doNothing().when(inscripciones).crearInscripcion(Mockito.any(), Mockito.any(), Mockito.any());
			mockMvc.perform(post(base + "/e/1")
					.flashAttr("inscripcion", new Inscripcion(1L, new Piloto(), new Campeonato(), new Equipo()))
					.sessionAttr(Constants.JWT_ATTR, JWT).sessionAttr(Constants.USER_ATTR, USER))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
