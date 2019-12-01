package com.races.portal.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Request Interceptor de todas las peticiones
 * 
 * @author Maximino Mañanes Ruiz
 */
@Component("RequestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOGGER = LogFactory.getLog(RequestTimeInterceptor.class);

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

			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
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
			if (request.getRequestURI().startsWith("/races")
					&& !(request.getMethod().equals(HttpMethod.OPTIONS.name()))) {
				LOGGER.info("RequestInterceptor [" + request.getMethod() + "] - URL to : '"
						+ request.getRequestURL().toString() + "' -- in: " + (System.currentTimeMillis() - startTime)
						+ "ms.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}