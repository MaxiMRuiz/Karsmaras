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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Campeonato;
import com.races.portal.dto.Reglamento;
import com.races.portal.services.CampeonatoService;
import com.races.portal.services.ReglamentoService;

@Controller
@RequestMapping("/races/campeonatos")
public class CampeonatosController {

	private static final Log LOGGER = LogFactory.getLog(CampeonatosController.class);

	@Autowired
	Environment env;

	@Autowired
	CampeonatoService campeonatos;

	@Autowired
	ReglamentoService reglamentos;

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
	public ModelAndView listaCampeonatos(Model model,
			@RequestHeader(value = "referer", required = false) final String urlPrevia) {
		LOGGER.info("Accediendo a la pantalla de Campeonatos");
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		List<Campeonato> listCampeonatos = campeonatos.buscarCampeonatos(null, null, null, null);
		model.addAttribute("listCampeonatos", listCampeonatos);
		model.addAttribute("urlServices", "/races/campeonatos/");
		return new ModelAndView("campeonatos");
	}

	@GetMapping(value = "/{id}")
	public ModelAndView formularioCampeonatos(Model model, @PathVariable String id) {

		Campeonato campeonato;
		if ("new".equals(id)) {
			LOGGER.info("Accediendo a la pantalla de Creacion de Campeonatos");
			campeonato = new Campeonato();
			campeonato.setReglamento(new Reglamento());
		} else if (id.startsWith("clone")) {
			LOGGER.info("Accediendo a la pantalla de Creacion de Campeonatos");
			String subId = id.substring(5);
			campeonato = campeonatos.buscarCampeonato(subId);
			campeonato.setId(null);
		} else {
			LOGGER.info("Accediendo a la pantalla de Edicion de Campeonatos [" + id + "]");
			campeonato = campeonatos.buscarCampeonato(id);
		}
		model.addAttribute("reglamentos", reglamentos.buscarReglamentos());
		model.addAttribute("campeonato", campeonato);
		return new ModelAndView("campeonato");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> borrarCampeonato(Model model, @PathVariable String id) {
		LOGGER.info("Borrando el campeonato [" + id + "]");
		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(campeonatos.borrarCampeonato(id), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ModelAndView postFormularioCampeonatos(Model model, @ModelAttribute Campeonato campeonato) {
		if (campeonato.getId() != null) {
			LOGGER.info("Editando el campeonato [" + campeonato.getId() + "]");
			campeonatos.editarCampeonato(campeonato);
		} else {
			LOGGER.info("Creando un nuevo campeonato [" + campeonato + "]");
			campeonatos.crearCampeonato(campeonato);
		}
		return new ModelAndView("redirect:/races/campeonatos");
	}

}
