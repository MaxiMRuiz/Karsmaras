package com.races.portal.controller;

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
import com.races.portal.dto.Resultado;
import com.races.portal.dto.Sancion;
import com.races.portal.services.ResultadoService;
import com.races.portal.services.SancionService;

@Controller
@RequestMapping("/races/sancion")
public class SancionController {

	private static final Log LOGGER = LogFactory.getLog(SancionController.class);

	@Autowired
	Environment env;

	@Autowired
	SancionService sanciones;

	@Autowired
	ResultadoService resultados;

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

	@GetMapping(value = "/{idGp}/{idSesion}/{idResultado}")
	public ModelAndView listaSanciones(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@PathVariable Long idResultado,
			@RequestHeader(value = "referer", required = false) final String urlPrevia) {
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		model.addAttribute("listaSanciones", sanciones.buscarSanciones(idResultado));
		model.addAttribute(Constants.PARAM_ID, idGp);
		model.addAttribute(Constants.PARAM_ID_SESION, idSesion);
		model.addAttribute(Constants.PARAM_ID_RESULTADO, idResultado);
		Resultado resultado = resultados.buscarResultado(idResultado);
		model.addAttribute(Constants.PARAM_NOMBRE, resultado.getSesion().toString());
		model.addAttribute(Constants.PARAM_PILOTO, resultado.getPiloto().toString());
		model.addAttribute("urlServices", "/races/sancion/" + idGp + "/" + idSesion + "/" + idResultado);
		return new ModelAndView("sanciones");
	}

	@GetMapping(value = "/{idGp}/{idSesion}/{idResultado}/{id}")
	public ModelAndView formularioSancion(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@PathVariable Long idResultado, @PathVariable String id) {

		Sancion sancion;
		Resultado resultado = resultados.buscarResultado(idResultado);
		if ("new".equals(id)) {
			sancion = new Sancion();
			sancion.setResultado(resultado);
		} else {
			sancion = sanciones.buscarSancion(id);
		}
		model.addAttribute("sancion", sancion);
		model.addAttribute(Constants.PARAM_ID, idGp);
		model.addAttribute(Constants.PARAM_ID_SESION, idSesion);

		model.addAttribute(Constants.PARAM_NOMBRE, resultado.getSesion().toString());
		model.addAttribute(Constants.PARAM_PILOTO, resultado.getPiloto().toString());
		return new ModelAndView("sancion");
	}

	@DeleteMapping(value = "/{idGp}/{idSesion}/{idResultado}/{id}")
	public ResponseEntity<Boolean> borrarSancion(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@PathVariable Long idResultado, @PathVariable Long id) {
		return new ResponseEntity<>(sanciones.borrarSancion(id), HttpStatus.OK);
	}

	@PostMapping(value = "/{idGp}/{idSesion}/{idResultado}")
	public ModelAndView postFormularioSancion(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@PathVariable Long idResultado, @ModelAttribute Sancion sancion) {

		if (sancion.getId() != null) {
			sanciones.editarSancion(sancion);
		} else {
			sanciones.crearSancion(sancion);
		}
		return new ModelAndView("redirect:/races/sancion/" + idGp + "/" + idSesion + "/" + idResultado);
	}

}
