package com.races.auth.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * Request Interceptor of all request of Onboarding MVC sercices.
 * 
 * @author Maximino Mañanes Ruiz
 */
@Component("RequestTimeInterceptor")
public class RequestInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOGGER = LogFactory.getLog(RequestInterceptor.class);

	@Autowired
	Environment env;

	/**
	 * Method pre-execution of every request to check the session attributes and
	 * user role to check if request are valid or not.
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * 
	 */
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler) {

		// Request start time
		request.setAttribute("startTime", System.currentTimeMillis());

		return true;

	}

	/**
	 * Method post-execution of every request to check the execution time and set
	 * the previous page.
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param exception
	 */
	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception exception) {
		// Get request duration
		long startTime = (long) request.getAttribute("startTime");

		LOGGER.info("RequestInterceptor - URL to : '" + request.getRequestURL().toString() + "' -- in: "
				+ (System.currentTimeMillis() - startTime) + "ms.");

	}

}
