package com.races.portal.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.races.portal.dto.Campeonato;
import com.races.portal.dto.Reglamento;
import com.races.portal.services.CampeonatoService;
import com.races.portal.services.ReglamentoService;

import io.micrometer.core.instrument.util.StringUtils;

@Controller
@RequestMapping("/races/campeonatos")
public class CampeonatosController {

	private static final Log LOGGER = LogFactory.getLog(CampeonatosController.class);

	@Autowired
	CampeonatoService campeonatos;

	@Autowired
	ReglamentoService reglamentos;
	
	@GetMapping
	public ModelAndView listaCampeonatos(Model model,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		LOGGER.info("Accediendo a la pantalla de Campeonatos");
		List<Campeonato> listCampeonatos = campeonatos.buscarCampeonatos(null, null, null, null, jwt, user);
		model.addAttribute("listCampeonatos", listCampeonatos);
		model.addAttribute("urlServices", "/races/campeonatos/");
		return new ModelAndView("campeonatos");
	}

	@GetMapping(value = "/{id}")
	public ModelAndView formularioCampeonatos(Model model, @PathVariable String id, 
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		Campeonato campeonato;
		if ("new".equals(id)) {
			LOGGER.info("Accediendo a la pantalla de Creacion de Campeonatos");
			campeonato = new Campeonato();
			campeonato.setReglamento(new Reglamento());
		} else {
			LOGGER.info("Accediendo a la pantalla de Edicion de Campeonatos [" + id + "]");
			campeonato = campeonatos.buscarCampeonato(id, jwt, user);
		}
		model.addAttribute("reglamentos", reglamentos.buscarReglamentos(jwt, user));
		model.addAttribute("campeonato", campeonato);
		return new ModelAndView("campeonato");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> borrarCampeonato(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		LOGGER.info("Borrando el campeonato [" + id + "]");
		if (StringUtils.isBlank(id)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(campeonatos.borrarCampeonato(id, jwt, user), HttpStatus.OK);
		}
	}

	@PostMapping
	public ModelAndView postFormularioCampeonatos(Model model, @ModelAttribute Campeonato campeonato,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		if (campeonato.getId() != null) {
			LOGGER.info("Editando el campeonato [" + campeonato.getId() + "]");
			campeonatos.editarCampeonato(campeonato, jwt, user);
		} else {
			LOGGER.info("Creando un nuevo campeonato [" + campeonato + "]");
			campeonatos.crearCampeonato(campeonato, jwt, user);
		}
		return new ModelAndView("redirect:/races/campeonatos");
	}

}
