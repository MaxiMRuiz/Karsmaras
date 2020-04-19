package com.races.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.races.portal.dto.Equipo;
import com.races.portal.services.EquipoService;

@Controller
@RequestMapping("/races/equipos")
public class EquiposController {

	private static final Log LOGGER = LogFactory.getLog(EquiposController.class);

	@Autowired
	Environment env;

	@Autowired
	EquipoService equipos;

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
	public ModelAndView viewEquipos(Model model,
			@RequestHeader(value = "referer", required = false) final String urlPrevia,final HttpServletRequest request,
			final HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String jwt = (String) sesion.getAttribute(Constants.JWT_ATTR);
		String user = (String) sesion.getAttribute(Constants.USER_ATTR);
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		List<Equipo> listaEquipos = equipos.buscarEquipos(null, null, null, jwt, user);
		model.addAttribute("listaEquipos", listaEquipos);
		model.addAttribute("urlServices", "/races/equipos/");
		return new ModelAndView("equipos");
	}

	@GetMapping(value = "/{id}")
	public ModelAndView formularioEquipos(Model model, @PathVariable String id,
			@RequestHeader(value = "referer", required = false) final String urlPrevia,final HttpServletRequest request,
			final HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String jwt = (String) sesion.getAttribute(Constants.JWT_ATTR);
		String user = (String) sesion.getAttribute(Constants.USER_ATTR);
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		Equipo equipo;
		if ("new".equals(id)) {
			equipo = new Equipo();
		} else if (id.startsWith("clone")) {
			String subId = id.substring(5);
			equipo = equipos.buscarEquipos(subId, jwt, user);
			equipo.setId(null);
		} else {
			equipo = equipos.buscarEquipos(id, jwt, user);
		}
		model.addAttribute("equipo", equipo);
		return new ModelAndView("equipo");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> borrarEquipo(Model model, @PathVariable String id,final HttpServletRequest request,
			final HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String jwt = (String) sesion.getAttribute(Constants.JWT_ATTR);
		String user = (String) sesion.getAttribute(Constants.USER_ATTR);
		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(equipos.borrarEquipo(id, jwt, user), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping()
	public ModelAndView postFormularioEquipos(Model model, @ModelAttribute Equipo equipo,final HttpServletRequest request,
			final HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String jwt = (String) sesion.getAttribute(Constants.JWT_ATTR);
		String user = (String) sesion.getAttribute(Constants.USER_ATTR);
		if (equipo.getId() != null) {
			equipos.editarEquipo(equipo, jwt, user);
		} else {
			equipos.crearEquipo(equipo, jwt, user);
		}
		return new ModelAndView("redirect:/races/equipos");
	}

}
