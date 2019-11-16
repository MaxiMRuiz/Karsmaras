package com.races.auth.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Request Interceptor of all request
 * 
 * @author Maximino Mañanes Ruiz
 */
@Component("RequestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOGGER = LogFactory.getLog(RequestTimeInterceptor.class);

	/**
	 * Method pre-execution of every request 
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * 
	 * @throws OnboardingException
	 */
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {

		try {
			// Request start time
			request.setAttribute("startTime", System.currentTimeMillis());
			
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * Method post-execution of every request to check the execution.
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
			// Get request duration
			long startTime = (long) request.getAttribute("startTime");
			LOGGER.info("RequestInterceptor - URL to : '" + request.getRequestURL().toString()
					+ "' -- in: " + (System.currentTimeMillis() - startTime) + "ms.");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}