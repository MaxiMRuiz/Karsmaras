package com.races.portal.controller;

import static org.junit.Assert.fail;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.races.portal.component.RacesException;
import com.races.portal.dto.LoginDto;
import com.races.portal.dto.LoginResponse;
import com.races.portal.dto.Piloto;
import com.races.portal.services.AuthService;
import com.races.portal.services.PilotoService;

public class PortalControllerTest {

	private static final String TEST = "Test";

	@Mock
	AuthService authService;

	@Mock
	PilotoService pilotos;

	@InjectMocks
	PortalController portalController;

	private MockMvc mockMvc;

	String base = "/races";

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(portalController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void mainPageTest() {
		try {
			mockMvc.perform(get(base)).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void mainRedirectPageTest() {
		try {
			mockMvc.perform(get("/")).andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void loginPageTest() {
		try {
			mockMvc.perform(get("/races/login").param("error", TEST)).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void registroTest() {
		try {
			mockMvc.perform(get(base + "/registro").param("error", TEST)).andExpect(status().is2xxSuccessful())
					.andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void processLoginTest() {
		try {
			Mockito.when(authService.login(Mockito.any())).thenReturn(new LoginResponse(TEST, true));
			Mockito.when(pilotos.buscarPilotos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
					Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
			mockMvc.perform(post(base + "/login").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
					.flashAttr("login", new LoginDto(TEST, TEST))).andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void processLoginFailTest() {
		try {
			Mockito.when(authService.login(Mockito.any())).thenThrow(new RacesException("Simulated Error"));
			mockMvc.perform(post(base + "/login").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
					.flashAttr("login", new LoginDto(TEST, TEST))).andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cambioPasswordPostTest() {
		try {
			Mockito.when(pilotos.crearPiloto(Mockito.any())).thenReturn(true);
			mockMvc.perform(post(base + "/registro").flashAttr("piloto", new Piloto()))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void cambioPasswordPostFailTest() {
		try {
			Mockito.when(pilotos.crearPiloto(Mockito.any())).thenReturn(false);
			mockMvc.perform(post(base + "/registro").flashAttr("piloto", new Piloto()))
					.andExpect(status().is3xxRedirection()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
