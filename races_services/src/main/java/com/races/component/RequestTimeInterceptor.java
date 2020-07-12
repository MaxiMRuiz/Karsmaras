package com.races.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.races.entity.Piloto;
import com.races.exception.RacesException;
import com.races.services.JwtService;
import com.races.services.PilotoService;

/**
 * Request Interceptor de todas las peticiones
 * 
 * @author Maximino Mañanes Ruiz
 */
@Component("RequestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	PilotoService pilotoService;

	@Autowired
	JwtService jwtService;

	private static final Log LOGGER = LogFactory.getLog(RequestTimeInterceptor.class);

	private static final String AUTHORIZATION_HEADER = "Authorization";

	private static final String LOGIN = "login";

	private static final String BASIC_AUTH = "Basic";

	private static final String BEARER_AUTH = "Bearer";

	/**
	 * Metodo pre ejecucion de todas las peticiones
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * 
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {

		try {
			request.setAttribute("startTime", System.currentTimeMillis());

			String[] uri = request.getRequestURI().split("/");
			if (uri.length == 0) {
				return false;
			}

			if (uri[1].equals(LOGIN) && (request.getHeader(AUTHORIZATION_HEADER) == null
					|| !request.getHeader(AUTHORIZATION_HEADER).startsWith(BASIC_AUTH))) {
				throw new RacesException("Service " + request.getRequestURI() + " is protected: Invalid credentials.");
			} else if ((uri[1].equals(LOGIN) && request.getHeader(AUTHORIZATION_HEADER).startsWith(BASIC_AUTH))
					|| (uri[1].equals("piloto") && HttpMethod.valueOf(request.getMethod()).equals(HttpMethod.POST))
					|| esExcepcion(uri[1])) {
				return true;
			} else {
				return processServices(request, uri[1]);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			response.setStatus(403);
			response.setContentType("application/json");
			throw e;
		}
	}

	private boolean esExcepcion(String uri) {
		return "v3".equals(uri);
	}

	private boolean processServices(HttpServletRequest request, String basepath) throws RacesException {
		Piloto piloto = pilotoService.buscarPiloto(request.getHeader("X-Races-User"));

		try {
			if (request.getHeader(AUTHORIZATION_HEADER) == null
					|| !request.getHeader(AUTHORIZATION_HEADER).startsWith(BEARER_AUTH)) {
				throw new RacesException("Cabecera de authenticacion no encontrada");
			}
			return jwtService.validateJWT(request.getHeader(AUTHORIZATION_HEADER).split(" ")[1],
					jwtService.decodeData(piloto.getJwk()));
		} catch (JoseException e) {
			LOGGER.error(e);
			throw new RacesException("Service " + request.getRequestURI() + " is protected: Invalid credentials.");
		}

	}

	/**
	 * Método Post Ejecucion de todas las peticiones
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param exception
	 */
	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception exception) {
		try {
			long startTime = (long) request.getAttribute("startTime");
			LOGGER.info(
					"RequestInterceptor [" + request.getMethod() + "] - URL to : '" + request.getRequestURL().toString()
							+ "' -- in: " + (System.currentTimeMillis() - startTime) + "ms.");

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}