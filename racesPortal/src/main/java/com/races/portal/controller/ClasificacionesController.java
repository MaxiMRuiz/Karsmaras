package com.races.portal.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.services.ClasificacionService;

@Controller
@RequestMapping("/races/clasificacion")
public class ClasificacionesController {

	private static final Log LOGGER = LogFactory.getLog(ClasificacionesController.class);

	@Autowired
	Environment env;

	@Autowired
	ClasificacionService clasificacionService;

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

	@GetMapping(value = "/gp/{id}")
	public ModelAndView clasificacionGP(Model model, @PathVariable Long id,
			@RequestHeader(value = "referer", required = false) final String urlPrevia) {
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		model.addAttribute(Constants.LISTA_CLASIFICACION, clasificacionService.clasificacionGp(id));
		model.addAttribute(Constants.TYPE, Constants.PARAM_PILOTO);
		return new ModelAndView(Constants.CLASIFICACION);
	}

	@GetMapping(value = "/campeonato/{id}")
	public ModelAndView clasificacionCampeonato(Model model, @PathVariable Long id,
			@RequestHeader(value = "referer", required = false) final String urlPrevia) {
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		model.addAttribute(Constants.LISTA_CLASIFICACION, clasificacionService.clasificacionCampeonato(id));
		model.addAttribute(Constants.TYPE, Constants.PARAM_PILOTO);
		return new ModelAndView(Constants.CLASIFICACION);
	}

	@GetMapping(value = "/gp/{id}/equipos")
	public ModelAndView clasificacionGpEquipos(Model model, @PathVariable Long id,
			@RequestHeader(value = "referer", required = false) final String urlPrevia) {
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		model.addAttribute(Constants.LISTA_CLASIFICACION, clasificacionService.clasificacionGpEquipos(id));
		model.addAttribute(Constants.TYPE, Constants.PARAM_EQUIPO);
		return new ModelAndView(Constants.CLASIFICACION);
	}

	@GetMapping(value = "/campeonato/{id}/equipos")
	public ModelAndView clasificacionCampeonatoEquipos(Model model, @PathVariable Long id,
			@RequestHeader(value = "referer", required = false) final String urlPrevia) {
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		model.addAttribute(Constants.LISTA_CLASIFICACION, clasificacionService.clasificacionCampeonatoEquipos(id));
		model.addAttribute(Constants.TYPE, Constants.PARAM_EQUIPO);
		return new ModelAndView(Constants.CLASIFICACION);
	}

}
