package com.races.portal.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.races.portal.component.RacesException;
import com.races.portal.component.Utils;
import com.races.portal.dto.LoginDto;
import com.races.portal.dto.LoginResponse;
import com.races.portal.services.impl.AuthServiceImpl;

import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthServiceTest {

	private static final String TEST = "TEST";

	@Mock
	Environment env;

	@Mock
	ObjectMapper mapper;

	@Mock
	Utils utils;

	@InjectMocks
	AuthServiceImpl authService;

	@SuppressWarnings("unchecked")
	HttpResponse<String> mockedResponse = Mockito.mock(HttpResponse.class);

	@Before
	public void init() throws UnirestException {

		MockitoAnnotations.initMocks(this);
		Mockito.when(env.getProperty(Mockito.anyString())).thenReturn("");
		Mockito.when(utils.executeHttpMethod(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(mockedResponse);

	}

	@Test
	public void loginTest() {
		try {
			Mockito.when(mockedResponse.getBody()).thenReturn(new LoginResponse().toString());
			Mockito.when(mockedResponse.getStatus()).thenReturn(200);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(LoginResponse.class)))
					.thenReturn(new LoginResponse());

			assertNotNull(authService.login(new LoginDto(TEST, TEST)));
		} catch (UnirestException | IOException | RacesException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void loginCatchTest() {
		try {
			Mockito.when(mockedResponse.getStatus()).thenReturn(403);
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.eq(LoginResponse.class)))
					.thenReturn(new LoginResponse());

			authService.login(new LoginDto(TEST, TEST));
			fail("Exception Expected");
		} catch (UnirestException | IOException | RacesException e) {
			assertNotNull(e);
		}
	}

}
