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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Reglamento;
import com.races.portal.services.ReglamentoService;

@Controller
@RequestMapping("/races/reglamentos")
public class ReglamentoController {

	@Autowired
	ReglamentoService reglamentos;

	@GetMapping
	public ModelAndView listaReglamentos(Model model,
			@RequestParam(required = false, value = "filterId") String filterId,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		List<Reglamento> listaReglamentos = reglamentos.buscarReglamentos(jwt, user);
		model.addAttribute("listaReglamentos", listaReglamentos);
		model.addAttribute("urlServices", "/races/reglamentos/");
		model.addAttribute("filterId", filterId);
		return new ModelAndView("reglamentos");
	}

	@GetMapping(value = "/{id}")
	public ModelAndView formularioReglamentos(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		Reglamento reglamento;
		if ("new".equals(id)) {
			reglamento = new Reglamento();
		} else {
			reglamento = reglamentos.buscarReglamento(id, jwt, user);
		}
		model.addAttribute("reglamento", reglamento);
		return new ModelAndView("reglamento");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> borrarReglamento(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(reglamentos.borrarReglamento(id, jwt, user), HttpStatus.OK);
		}
	}

	@PostMapping()
	public ModelAndView postFormularioReglamentos(Model model, @ModelAttribute Reglamento reglamento,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		if (reglamento.getId() != null) {
			reglamentos.editarReglamento(reglamento, jwt, user);
		} else {
			reglamentos.crearReglamento(reglamento, jwt, user);
		}
		return new ModelAndView("redirect:/races/reglamentos");
	}

}
