package com.races.portal.controller;

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
import com.races.portal.dto.Resultado;
import com.races.portal.dto.Sancion;
import com.races.portal.services.ResultadoService;
import com.races.portal.services.SancionService;

@Controller
@RequestMapping("/races/sancion")
public class SancionController {

	@Autowired
	SancionService sanciones;

	@Autowired
	ResultadoService resultados;

	@GetMapping(value = "/{idGp}/{idSesion}/{idResultado}")
	public ModelAndView listaSanciones(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@PathVariable Long idResultado, @SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		model.addAttribute("listaSanciones", sanciones.buscarSanciones(idResultado, jwt, user));
		model.addAttribute(Constants.PARAM_ID, idGp);
		model.addAttribute(Constants.PARAM_ID_SESION, idSesion);
		model.addAttribute(Constants.PARAM_ID_RESULTADO, idResultado);
		Resultado resultado = resultados.buscarResultado(idResultado, jwt, user);
		model.addAttribute(Constants.PARAM_NOMBRE, resultado.getSesionGP().toString());
		model.addAttribute(Constants.PARAM_PILOTO, resultado.getInscripcion().getPiloto().toString());
		model.addAttribute("urlServices", "/races/sancion/" + idGp + "/" + idSesion + "/" + idResultado);
		return new ModelAndView("sanciones");
	}

	@GetMapping(value = "/{idGp}/{idSesion}/{idResultado}/{id}")
	public ModelAndView formularioSancion(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@PathVariable Long idResultado, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		Sancion sancion;
		Resultado resultado = resultados.buscarResultado(idResultado, jwt, user);
		if ("new".equals(id)) {
			sancion = new Sancion();
			sancion.setResultado(resultado);
		} else {
			sancion = sanciones.buscarSancion(id, jwt, user);
		}
		model.addAttribute("sancion", sancion);
		model.addAttribute(Constants.PARAM_ID, idGp);
		model.addAttribute(Constants.PARAM_ID_SESION, idSesion);
		model.addAttribute(Constants.PARAM_ID_RESULTADO, idResultado);
		model.addAttribute(Constants.PARAM_NOMBRE, resultado.getSesionGP().toString());
		model.addAttribute(Constants.PARAM_PILOTO, resultado.getInscripcion().getPiloto().toString());
		return new ModelAndView("sancion");
	}

	@DeleteMapping(value = "/{idGp}/{idSesion}/{idResultado}/{id}")
	public ResponseEntity<Boolean> borrarSancion(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@PathVariable Long idResultado, @PathVariable Long id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		return new ResponseEntity<>(sanciones.borrarSancion(id, jwt, user), HttpStatus.OK);
	}

	@PostMapping(value = "/{idGp}/{idSesion}/{idResultado}")
	public ModelAndView postFormularioSancion(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@PathVariable Long idResultado, @ModelAttribute Sancion sancion,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		if (sancion.getId() != null) {
			sanciones.editarSancion(sancion, jwt, user);
		} else {
			sanciones.crearSancion(sancion, jwt, user);
		}
		return new ModelAndView("redirect:/races/sancion/" + idGp + "/" + idSesion + "/" + idResultado);
	}

}
