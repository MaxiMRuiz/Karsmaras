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
import com.races.portal.dto.Piloto;
import com.races.portal.services.PilotoService;

/**
 * Controlador de pilotos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Controller
@RequestMapping("/races/pilotos")
public class PilotosController {

	private static final Log LOGGER = LogFactory.getLog(PilotosController.class);

	@Autowired
	Environment env;

	@Autowired
	PilotoService pilotos;

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
	public ModelAndView listaPilotos(Model model,
			@RequestHeader(value = "referer", required = false) final String urlPrevia) {
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);

		List<Piloto> listaPilotos = pilotos.buscarPilotos(null, null, null, null);
		model.addAttribute("listaPilotos", listaPilotos);
		model.addAttribute("urlServices", "/races/pilotos/");
		return new ModelAndView("pilotos");
	}

	@GetMapping(value = "/{id}")
	public ModelAndView formularioPilotos(Model model, @PathVariable String id,
			@RequestHeader(value = "referer", required = false) final String urlPrevia) {
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		Piloto piloto;
		if ("new".equals(id)) {
			piloto = new Piloto();
		} else if (id.startsWith("clone")) {
			String subId = id.substring(5);
			piloto = pilotos.buscarPilotos(subId);
			piloto.setId(null);
		} else {
			piloto = pilotos.buscarPilotos(id);
		}
		model.addAttribute("piloto", piloto);
		return new ModelAndView("piloto");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> borrarPiloto(Model model, @PathVariable String id) {

		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(pilotos.borrarPiloto(id), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping()
	public ModelAndView postFormularioPilotos(Model model, @ModelAttribute Piloto piloto) {

		pilotos.crearPiloto(piloto);
		return new ModelAndView("redirect:/races/pilotos");
	}

}
