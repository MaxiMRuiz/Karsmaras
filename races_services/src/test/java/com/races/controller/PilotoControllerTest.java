package com.races.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.races.dto.LoginResponse;
import com.races.entity.Piloto;
import com.races.exception.RacesException;
import com.races.services.impl.PilotoServiceImpl;

import net.minidev.json.JSONObject;

public class PilotoControllerTest {
	private static final String TEST = "test";

	@Mock
	PilotoServiceImpl pilotoService;

	@MockBean
	MockMvc mockMvc;

	@InjectMocks
	PilotoController controller;

	String base = "/piloto";

	String baseId = base + "/1";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void crearPilotoTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("apellido", TEST);
			body.put("apodo", TEST);
			body.put("password", TEST);
			body.put("admin", true);

			Mockito.when(pilotoService.crearPiloto(Mockito.any())).thenReturn(new Piloto());

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void loginTest() {

		try {

			Mockito.when(pilotoService.authenticarUsuario(Mockito.any(), Mockito.any()))
					.thenReturn(new LoginResponse());

			mockMvc.perform(post("/login").header("Authorization",
					"Basic " + Base64.getEncoder()
							.encodeToString((TEST + ":" + TEST).getBytes(StandardCharsets.UTF_8))))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void loginNoBasicTest() {

		try {

			Mockito.when(pilotoService.authenticarUsuario(Mockito.any(), Mockito.any()))
					.thenReturn(new LoginResponse());

			mockMvc.perform(post("/login").header("Authorization",
					"Bearer "
							+ Base64.getEncoder().encodeToString((TEST + ":" + TEST).getBytes(StandardCharsets.UTF_8))))
					.andExpect(status().isUnauthorized()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void loginExceptionTest() {

		try {

			Mockito.when(pilotoService.authenticarUsuario(Mockito.any(), Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(post("/login").header("Authorization",
					"Basic " + Base64.getEncoder()
							.encodeToString((TEST + ":" + TEST).getBytes(StandardCharsets.UTF_8))))
					.andExpect(status().isForbidden()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void buscarPilotoTest() {

		try {

			Mockito.when(pilotoService.buscarPilotos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());

			mockMvc.perform(
					get(base).param("nombre", TEST).param("apellido", TEST).param("apodo", TEST).param("id", "1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void editarPilotoTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("apellido", TEST);
			body.put("apodo", TEST);
			body.put("password", TEST);
			body.put("admin", true);

			Mockito.when(pilotoService.editarPiloto(Mockito.any(), Mockito.any())).thenReturn(new Piloto());

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void setPilotoAdminTest() {

		try {

			Mockito.when(pilotoService.setPilotoAdmin(Mockito.any(), Mockito.any())).thenReturn(new Piloto());

			mockMvc.perform(put("/admin/1").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void setPilotoAdminExceptionTest() {

		try {

			Mockito.when(pilotoService.setPilotoAdmin(Mockito.any(), Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(put("/admin/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
					.andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void changePasswordTest() {

		try {

			Mockito.when(pilotoService.changePassword(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

			mockMvc.perform(put("/password").header("X-Races-Change-Password",
					Base64.getEncoder()
							.encodeToString((TEST + ":" + TEST + ":" + TEST).getBytes(StandardCharsets.UTF_8))))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void changePasswordBadRequestTest() {

		try {

			Mockito.when(pilotoService.changePassword(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

			mockMvc.perform(put("/password").header("X-Races-Change-Password",
					Base64.getEncoder().encodeToString((TEST + ":" + TEST).getBytes(StandardCharsets.UTF_8))))
					.andExpect(status().isBadRequest()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void changePasswordNotFoundTest() {

		try {

			Mockito.when(pilotoService.changePassword(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(put("/password").header("X-Races-Change-Password",
					Base64.getEncoder()
							.encodeToString((TEST + ":" + TEST + ":" + TEST).getBytes(StandardCharsets.UTF_8))))
					.andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarPilotoTest() {

		try {

			Mockito.when(pilotoService.borrarPiloto(Mockito.any())).thenReturn(true);

			mockMvc.perform(delete(baseId)).andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void crearPilotoExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("apellido", TEST);
			body.put("apodo", TEST);
			body.put("password", TEST);
			body.put("admin", true);

			Mockito.when(pilotoService.crearPiloto(Mockito.any())).thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(post(base).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().isConflict()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void editarPilotoExceptionTest() {

		try {

			JSONObject body = new JSONObject();
			body.put("nombre", TEST);
			body.put("apellido", TEST);
			body.put("apodo", TEST);
			body.put("password", TEST);
			body.put("admin", true);

			Mockito.when(pilotoService.editarPiloto(Mockito.any(), Mockito.any()))
					.thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(put(baseId).contentType(MediaType.APPLICATION_JSON).content(body.toJSONString()))
					.andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void borrarPilotoExceptionTest() {

		try {

			Mockito.when(pilotoService.borrarPiloto(Mockito.any())).thenThrow(new RacesException("Simulated Error"));

			mockMvc.perform(delete(baseId)).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
