package com.races.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Password;
import com.races.portal.dto.Piloto;
import com.races.portal.services.PilotoService;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * Controlador de pilotos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Controller
@RequestMapping("/races/pilotos")
public class PilotosController {

	@Autowired
	PilotoService pilotos;

	@GetMapping
	public ModelAndView listaPilotos(Model model,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {

		List<Piloto> listaPilotos = pilotos.buscarPilotos(null, null, null, null, jwt, user);
		model.addAttribute("listaPilotos", listaPilotos);
		model.addAttribute("urlServices", "/races/pilotos/");
		return new ModelAndView("pilotos");
	}

	@GetMapping(value = "/{id}")
	public ModelAndView formularioPilotos(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		Piloto piloto;
		if ("new".equals(id)) {
			piloto = new Piloto();
		} else {
			piloto = pilotos.buscarPilotos(id, jwt, user);
		}
		model.addAttribute(Constants.PILOTO_ATTR, piloto);
		return new ModelAndView(Constants.PILOTO_ATTR);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> borrarPiloto(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(pilotos.borrarPiloto(id, jwt, user), HttpStatus.OK);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Boolean> promocionarPiloto(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(pilotos.promocionarPiloto(id, jwt, user), HttpStatus.OK);
		}
	}

	@PostMapping()
	public ModelAndView postFormularioPilotos(Model model, @ModelAttribute Piloto piloto,
			final HttpServletRequest request, final HttpServletResponse response) {

		pilotos.crearPiloto(piloto);
		return new ModelAndView("redirect:/races/pilotos");
	}

	@GetMapping(value = "/perfil")
	public ModelAndView perfil(@RequestParam(required = false) String error, Model model,
			final HttpServletRequest request) {

		HttpSession sesion = request.getSession();
		model.addAttribute(Constants.PILOTO_ATTR, (Piloto) sesion.getAttribute(Constants.PILOTO_ATTR));
		if (!StringUtils.isBlank(error)) {
			model.addAttribute(Constants.ERROR, error);
		}
		return new ModelAndView("perfil");
	}

	@PostMapping(value = "/perfil")
	public ModelAndView postPerfil(@ModelAttribute Piloto piloto, final HttpServletRequest request,
			RedirectAttributes redirectAtt, @SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		HttpSession sesion = request.getSession();
		Piloto pilotoEditado = pilotos.editarPiloto(piloto, jwt, user);
		if (pilotoEditado != null) {
			sesion.setAttribute(Constants.USER_ATTR, pilotoEditado.getApodo());
			sesion.setAttribute(Constants.PILOTO_ATTR, pilotoEditado);
			return new ModelAndView("redirect:/races/pilotos/perfil");
		} else {
			redirectAtt.addAttribute(Constants.ERROR, 500);
			return new ModelAndView("redirect:/races/pilotos/perfil");
		}
	}

	@GetMapping(value = "/password")
	public ModelAndView cambioPassword(@RequestParam(required = false) String error, Model model,
			@SessionAttribute(name = Constants.PILOTO_ATTR, required = true) Piloto piloto) {

		model.addAttribute("password", new Password(piloto.getId(), "", ""));
		if (!StringUtils.isBlank(error)) {
			model.addAttribute(Constants.ERROR, error);
		}
		return new ModelAndView("password");
	}

	@PostMapping(value = "/password")
	public ModelAndView cambioPasswordPost(@ModelAttribute Password password, RedirectAttributes redirectAtt,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {

		Boolean result = pilotos.cambiarPassword(password, jwt, user);
		if (result) {
			return new ModelAndView("redirect:/races/pilotos/password");
		} else {
			redirectAtt.addAttribute(Constants.ERROR, 500);
			return new ModelAndView("redirect:/races/pilotos/password");
		}
	}

}
