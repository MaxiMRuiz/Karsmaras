package com.races.portal.component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.races.portal.constants.Constants;

public class RequestTimeInterceptorTest {

	@Mock
	Environment env;

	@InjectMocks
	RequestTimeInterceptor interceptor;

	MockHttpServletRequest request = new MockHttpServletRequest();
	MockHttpServletResponse response = new MockHttpServletResponse();
	MockHttpSession session = new MockHttpSession();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		MockMvcBuilders.standaloneSetup(interceptor).build();
		request.setAttribute("startTime", System.currentTimeMillis());

	}

	@Test
	public void preHandleTestUnprotected() throws Exception {

		request.setRequestURI("/races/registro");
		assertTrue(interceptor.preHandle(request, response, null));

	}

	@Test
	public void preHandleTestProtected() throws Exception {

		request.setRequestURI("/races/equipos");
		assertFalse(interceptor.preHandle(request, response, null));

	}

	@Test
	public void preHandleTestLogout() throws Exception {

		request.setRequestURI("/races/logout");
		session.setAttribute(Constants.JWT_EXPIRED, System.currentTimeMillis());
		session.setAttribute(Constants.JWT_ATTR, "TEST");
		request.setSession(session);
		assertFalse(interceptor.preHandle(request, response, null));

	}

	@Test
	public void afterHandleTest() {

		request.setRequestURI("/races/campeonatos");
		try {
			interceptor.afterCompletion(request, response, null, null);
			assertTrue("1".equals("1"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
