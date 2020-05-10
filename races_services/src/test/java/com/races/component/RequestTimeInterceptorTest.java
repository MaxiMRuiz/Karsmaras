package com.races.component;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jose4j.lang.JoseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.races.entity.Piloto;
import com.races.exception.RacesException;
import com.races.services.JwtService;
import com.races.services.PilotoService;

@RunWith(SpringJUnit4ClassRunner.class)
public class RequestTimeInterceptorTest {

	private static final String TEST = "test";

	@Mock
	Environment env;

	@Mock
	PilotoService pilotoService;

	@Mock
	JwtService jwtService;

	@InjectMocks
	RequestTimeInterceptor interceptor;

	MockHttpServletRequest request = new MockHttpServletRequest();
	MockHttpServletResponse response = new MockHttpServletResponse();
	MockHttpSession session = new MockHttpSession();

	@Before
	public void init() throws RacesException, JoseException {
		MockitoAnnotations.initMocks(this);
		MockMvcBuilders.standaloneSetup(interceptor).build();
		request.setAttribute("startTime", System.currentTimeMillis());
		Mockito.when(pilotoService.buscarPiloto(Mockito.anyString()))
				.thenReturn(new Piloto(1L, TEST, TEST, TEST, TEST, true, TEST));
		Mockito.when(jwtService.decodeData(Mockito.anyString())).thenReturn(TEST);
		Mockito.when(jwtService.validateJWT(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
	}

	@Test
	public void preHandleTestBasicAuth() throws Exception {

		request.setRequestURI("/login");
		request.addHeader("Authorization", "Basic test");
		assertTrue(interceptor.preHandle(request, response, null));

	}

	@Test
	public void preHandleTestJWT() throws Exception {

		request.setRequestURI("/campeonato");
		request.addHeader("Authorization", "Bearer test");
		request.addHeader("X-Races-User", TEST);
		assertTrue(interceptor.preHandle(request, response, null));

	}

	@Test
	public void preHandleTestfail() {

		request.setRequestURI("/login");
		request.addHeader("X-Races-User", TEST);
		try {
			interceptor.preHandle(request, response, null);
		} catch (Exception e) {
			assertNotNull(e);
		}

	}

	@Test
	public void afterHandleTest() {

		request.setRequestURI("/campeonato");
		try {
			interceptor.afterCompletion(request, response, null, null);
			assertTrue("1".equals("1"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}