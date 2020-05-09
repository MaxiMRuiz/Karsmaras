package com.races.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Sesion;
import com.races.portal.services.SesionService;

@Controller
@RequestMapping("/races/sesiones")
public class SesionController {

	@Autowired
	SesionService sesiones;

	@GetMapping(value = "/{idReglamento}")
	public ModelAndView listaSesiones(Model model, @PathVariable String idReglamento,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		List<Sesion> listaSesiones = sesiones.buscarSesiones(idReglamento, jwt, user);
		model.addAttribute("listaSesiones", listaSesiones);
		return new ModelAndView("sesiones");
	}

}