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

import com.races.portal.dto.Reglamento;
import com.races.portal.services.ReglamentoService;

@Controller
@RequestMapping("/races/reglamentos")
public class ReglamentoController {

	private static final Log LOGGER = LogFactory.getLog(ReglamentoController.class);

	@Autowired
	Environment env;

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
	public ModelAndView listaReglamentos(Model model) {
		List<Reglamento> listaReglamentos = reglamentos.buscarReglamentos();
		model.addAttribute("listaReglamentos", listaReglamentos);
		model.addAttribute("urlServices", "/races/reglamentos/");
		return new ModelAndView("reglamentos");
	}

	@GetMapping(value = "/{id}")
	public ModelAndView formularioReglamentos(Model model, @PathVariable String id) {

		Reglamento reglamento;
		if ("new".equals(id)) {
			reglamento = new Reglamento();
		} else if (id.startsWith("clone")) {
			String subId = id.substring(5);
			reglamento = reglamentos.buscarReglamento(subId);
			reglamento.setId(null);
		} else {
			reglamento = reglamentos.buscarReglamento(id);
		}
		model.addAttribute("reglamento", reglamento);
		return new ModelAndView("reglamento");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> borrarPiloto(Model model, @PathVariable String id) {

		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(reglamentos.borrarReglamento(id), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping()
	public ModelAndView postFormularioPilotos(Model model, @ModelAttribute Reglamento reglamento) {
		if (reglamento.getId() != null) {
			reglamentos.editarReglamento(reglamento);
		} else {
			reglamentos.crearReglamento(reglamento);
		}
		return new ModelAndView("redirect:/races/reglamentos");
	}

}
