package com.races.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Equipo;
import com.races.portal.services.EquipoService;

@Controller
@RequestMapping("/races/equipos")
public class EquiposController {

	@Autowired
	EquipoService equipos;

	@GetMapping
	public ModelAndView listaEquipos(Model model,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		List<Equipo> listaEquipos = equipos.buscarEquipos(null, null, null, jwt, user);
		model.addAttribute("listaEquipos", listaEquipos);
		model.addAttribute("urlServices", "/races/equipos/");
		return new ModelAndView("equipos");
	}

	@GetMapping(value = "/{id}")
	public ModelAndView formularioEquipos(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		Equipo equipo;
		if ("new".equals(id)) {
			equipo = new Equipo();
		} else {
			equipo = equipos.buscarEquipos(id, jwt, user);
		}
		model.addAttribute("equipo", equipo);
		return new ModelAndView("equipo");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> borrarEquipo(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(equipos.borrarEquipo(id, jwt, user), HttpStatus.OK);
		}
	}

	@PostMapping()
	public ModelAndView postFormularioEquipos(Model model, @ModelAttribute Equipo equipo,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		if (equipo.getId() != null) {
			equipos.editarEquipo(equipo, jwt, user);
		} else {
			equipos.crearEquipo(equipo, jwt, user);
		}
		return new ModelAndView("redirect:/races/equipos");
	}

}
