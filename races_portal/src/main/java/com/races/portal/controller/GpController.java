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
import com.races.portal.dto.GranPremio;
import com.races.portal.services.CampeonatoService;
import com.races.portal.services.GranPremioService;

@Controller
@RequestMapping("/races/gp")
public class GpController {

	@Autowired
	GranPremioService gpService;

	@Autowired
	CampeonatoService campeonatos;

	@GetMapping(value = "/{id}")
	public ModelAndView listaGrandesPremios(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		List<GranPremio> listaGrandesPremios = gpService.buscarGrandesPremios(id, jwt, user);
		model.addAttribute("listaGrandesPremios", listaGrandesPremios);
		model.addAttribute("nombre", campeonatos.buscarCampeonato(id, jwt, user).toString());
		model.addAttribute(Constants.PARAM_ID, id);
		model.addAttribute("urlServices", "/races/gp/" + id);
		return new ModelAndView("gps");
	}

	@GetMapping(value = "/{id}/new")
	public ModelAndView formularioGrandesPremios(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		model.addAttribute("nombre", campeonatos.buscarCampeonato(id, jwt, user).toString());
		model.addAttribute("gp", new GranPremio());
		model.addAttribute(Constants.PARAM_ID, id);
		model.addAttribute("urlServices", "/races/gp/" + id);
		return new ModelAndView("gp");
	}

	@PostMapping(value = "/{id}")
	public ModelAndView crearGrandesPremios(Model model, @PathVariable String id, @ModelAttribute GranPremio gp,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		gpService.crearGranPremio(id, gp, jwt, user);
		return new ModelAndView("redirect:/races/gp/" + id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> borrarGrandesPremios(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		return new ResponseEntity<>(gpService.borrarGP(id, jwt, user), HttpStatus.OK);
	}

}
