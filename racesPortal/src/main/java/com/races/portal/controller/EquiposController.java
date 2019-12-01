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

import com.races.portal.dto.Equipo;
import com.races.portal.services.EquipoService;

@Controller
@RequestMapping("/races/equipos")
public class EquiposController {

	private static final Log LOGGER = LogFactory.getLog(EquiposController.class);

	@Autowired
	Environment env;

	@Autowired
	EquipoService equipos;

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
	public ModelAndView viewEquipos(Model model) {
		List<Equipo> listaEquipos = equipos.buscarEquipos(null, null, null);
		model.addAttribute("listaEquipos", listaEquipos);
		model.addAttribute("urlServices", "/races/equipos/");
		return new ModelAndView("equipos");
	}

	@GetMapping(value = "/{id}")
	public ModelAndView formularioEquipos(Model model, @PathVariable String id) {

		Equipo equipo;
		if ("new".equals(id)) {
			equipo = new Equipo();
		} else if (id.startsWith("clone")) {
			String subId = id.substring(5);
			equipo = equipos.buscarEquipos(subId);
			equipo.setId(null);
		} else {
			equipo = equipos.buscarEquipos(id);
		}
		model.addAttribute("equipo", equipo);
		return new ModelAndView("equipo");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> borrarEquipo(Model model, @PathVariable String id) {

		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(equipos.borrarEquipo(id), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping()
	public ModelAndView postFormularioEquipos(Model model, @ModelAttribute Equipo equipo) {

		if (equipo.getId() != null) {
			equipos.editarEquipo(equipo);
		} else {
			equipos.crearEquipo(equipo);
		}
		return new ModelAndView("redirect:/races/equipos");
	}

}
