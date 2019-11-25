package com.races.portal.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/races/clasificacion")
public class ClasificacionesController {

	private static final Log LOGGER = LogFactory.getLog(ClasificacionesController.class);

	@Autowired
	Environment env;

	/**
	 * Handler bad request
	 * 
	 * @param exception
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handle(Exception exception) {
		LOGGER.warn("Returning HTTP 400 Bad Request", exception);
	}

	@GetMapping
	public ModelAndView mainPage() {
		return new ModelAndView("home");
	}

	@GetMapping(value = "/equipos")
	public ModelAndView viewClasificacionEquipos() {
		return new ModelAndView("home");
	}

	@GetMapping(value = "/pilotos")
	public ModelAndView viewClasificacionPilotos() {
		return new ModelAndView("home");
	}

}
