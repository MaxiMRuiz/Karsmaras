package com.races.controller;

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

import com.races.services.JwtService;
import com.races.services.PilotoService;
import com.races.services.impl.VueltaServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(VueltaController.class)
public class VueltaControllerTest {

	@MockBean
	VueltaServiceImpl vueltaService;

	@MockBean
	PilotoService pilotoService;

	@MockBean
	JwtService jwtService;

	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	VueltaController controller;

	String base = "/vuelta";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void buscarVueltasTest() {

		try {

			Mockito.when(vueltaService.buscarVueltas(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(new ArrayList<>());

			mockMvc.perform(
					get(base).param("idResultado", "1").param("nVuelta", "1").param("tiempo", "1").param("id", "1"))
					.andExpect(status().is2xxSuccessful()).andReturn();
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
}
