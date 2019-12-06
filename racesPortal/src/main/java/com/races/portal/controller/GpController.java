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
import com.races.portal.dto.GranPremio;
import com.races.portal.services.CampeonatoService;
import com.races.portal.services.GranPremioService;

@Controller
@RequestMapping("/races/gp")
public class GpController {

	private static final Log LOGGER = LogFactory.getLog(GpController.class);

	@Autowired
	Environment env;

	@Autowired
	GranPremioService gpService;

	@Autowired
	CampeonatoService campeonatos;

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
	public ModelAndView listaGrandesPremios(Model model, @PathVariable String id,
			@RequestHeader(value = "referer", required = false) final String urlPrevia) {
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		List<GranPremio> listaGrandesPremios = gpService.buscarGrandesPremios(id);
		model.addAttribute("listaGrandesPremios", listaGrandesPremios);
		model.addAttribute("nombre", campeonatos.buscarCampeonato(id).toString());
		model.addAttribute(Constants.PARAM_ID, id);
		model.addAttribute("urlServices", "/races/gp/" + id);
		return new ModelAndView("gps");
	}

	@GetMapping(value = "/{id}/new")
	public ModelAndView formularioGrandesPremios(Model model, @PathVariable String id) {
		model.addAttribute("nombre", campeonatos.buscarCampeonato(id).toString());
		model.addAttribute("gp", new GranPremio());
		model.addAttribute(Constants.PARAM_ID, id);
		model.addAttribute("urlServices", "/races/gp/" + id);
		return new ModelAndView("gp");
	}

	@PostMapping(value = "/{id}")
	public ModelAndView crearGrandesPremios(Model model, @PathVariable String id, @ModelAttribute GranPremio gp) {

		gpService.crearGranPremio(id, gp);
		return new ModelAndView("redirect:/races/gp/" + id);
	}

	@DeleteMapping(value = "/{campeonato}/{id}")
	public ResponseEntity<Boolean> borrarGrandesPremios(Model model, @PathVariable String campeonato,
			@PathVariable String id) {
		return new ResponseEntity<>(gpService.borrarGP(id), HttpStatus.OK);
	}

}
