package com.races.portal.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.races.portal.constants.Constants;

/**
 * Request Interceptor de todas las peticiones
 * 
 * @author Maximino Mañanes Ruiz
 */
@Component("RequestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOGGER = LogFactory.getLog(RequestTimeInterceptor.class);
	private static final String LOGIN_PAGE = "/races/login";

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
			HttpSession sesion = request.getSession();
			if (isUnprotectedPage(request.getRequestURI())) {
				return true;
			} else if (!isUnprotectedPage(request.getRequestURI()) && sesion.getAttribute(Constants.JWT_ATTR) == null) {
				response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
				return false;
			} else if (request.getRequestURI().equals("/races/logout")
					|| System.currentTimeMillis() > (long) sesion.getAttribute(Constants.JWT_EXPIRED)) {
				sesion.removeAttribute(Constants.JWT_ATTR);
				sesion.removeAttribute(Constants.USER_ATTR);
				sesion.removeAttribute(Constants.PARAM_ADMIN);
				sesion.removeAttribute(Constants.JWT_EXPIRED);
				response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
				return false;
			}
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}

	private boolean isUnprotectedPage(String requestURI) {
		return requestURI.equals("/races/registro") || requestURI.equals(LOGIN_PAGE)
				|| !requestURI.startsWith("/races");
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
				LOGGER.info("Request [" + request.getMethod() + "] to : '" + request.getRequestURL().toString()
						+ "' -- in: " + (System.currentTimeMillis() - startTime) + "ms.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}