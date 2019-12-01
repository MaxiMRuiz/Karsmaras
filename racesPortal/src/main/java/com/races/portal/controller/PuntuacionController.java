package com.races.portal.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Puntuacion;
import com.races.portal.services.PuntuacionService;

@Controller
@RequestMapping("/races/puntuaciones")
public class PuntuacionController {

	private static final Log LOGGER = LogFactory.getLog(PuntuacionController.class);

	@Autowired
	Environment env;

	@Autowired
	PuntuacionService puntuaciones;

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

	@GetMapping(value = "/{id}")
	public ModelAndView listaPuntuaciones(Model model, @PathVariable String id) {
		List<Puntuacion> listaPuntuaciones = puntuaciones.buscarPuntuaciones(id);
		model.addAttribute("listaPuntuaciones", listaPuntuaciones);
		model.addAttribute(Constants.PARAM_ID, id);
		model.addAttribute("urlServices", "/races/puntuaciones/" + id);
		return new ModelAndView("puntuaciones");
	}

	@GetMapping(value = "/{id}/{obj}")
	public ModelAndView formularioPuntuaciones(Model model, @PathVariable String id, @PathVariable String obj) {

		Puntuacion puntuacion;
		if ("new".equals(obj)) {
			puntuacion = new Puntuacion();
		} else if (obj.startsWith("clone")) {
			String subId = obj.substring(5);
			puntuacion = puntuaciones.buscarPuntuacion(id, subId);
			puntuacion.setId(null);
		} else {
			puntuacion = puntuaciones.buscarPuntuacion(id, obj);
		}
		model.addAttribute("puntuacion", puntuacion);
		return new ModelAndView("puntuacion");
	}

	@DeleteMapping(value = "/{id}/{obj}")
	public ResponseEntity<Boolean> borrarPuntuacion(Model model, @PathVariable String id, @PathVariable String obj) {

		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(puntuaciones.borrarPuntuacion(id, obj), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/{id}")
	public ModelAndView postFormularioPuntuacion(Model model, @ModelAttribute Puntuacion puntuacion) {
		if (puntuacion.getId() != null) {
			puntuaciones.editarPuntuacion(puntuacion);
		} else {
			puntuaciones.crearPuntuacion(puntuacion);
		}
		return new ModelAndView("redirect:/races/puntuaciones");
	}

}
