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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
			@RequestHeader(value = "referer", required = false) final String urlPrevia,
			final HttpServletRequest request, final HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String jwt = (String) sesion.getAttribute(Constants.JWT_ATTR);
		String user = (String) sesion.getAttribute(Constants.USER_ATTR);
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);

		List<Piloto> listaPilotos = pilotos.buscarPilotos(null, null, null, null, jwt, user);
		model.addAttribute("listaPilotos", listaPilotos);
		model.addAttribute("urlServices", "/races/pilotos/");
		return new ModelAndView("pilotos");
	}

	@GetMapping(value = "/{id}")
	public ModelAndView formularioPilotos(Model model, @PathVariable String id,
			@RequestHeader(value = "referer", required = false) final String urlPrevia,
			final HttpServletRequest request, final HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String jwt = (String) sesion.getAttribute(Constants.JWT_ATTR);
		String user = (String) sesion.getAttribute(Constants.USER_ATTR);
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		Piloto piloto;
		if ("new".equals(id)) {
			piloto = new Piloto();
		} else if (id.startsWith("clone")) {
			String subId = id.substring(5);
			piloto = pilotos.buscarPilotos(subId, jwt, user);
			piloto.setId(null);
		} else {
			piloto = pilotos.buscarPilotos(id, jwt, user);
		}
		model.addAttribute(Constants.PILOTO_ATTR, piloto);
		return new ModelAndView(Constants.PILOTO_ATTR);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> borrarPiloto(Model model, @PathVariable String id, final HttpServletRequest request,
			final HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String jwt = (String) sesion.getAttribute(Constants.JWT_ATTR);
		String user = (String) sesion.getAttribute(Constants.USER_ATTR);
		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(pilotos.borrarPiloto(id, jwt, user), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Boolean> promocionarPiloto(Model model, @PathVariable String id,
			final HttpServletRequest request, final HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String jwt = (String) sesion.getAttribute(Constants.JWT_ATTR);
		String user = (String) sesion.getAttribute(Constants.USER_ATTR);
		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(pilotos.promocionarPiloto(id, jwt, user), HttpStatus.BAD_REQUEST);
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
			final HttpServletRequest request, final HttpServletResponse response) {

		HttpSession sesion = request.getSession();
		model.addAttribute(Constants.PILOTO_ATTR, (Piloto) sesion.getAttribute(Constants.PILOTO_ATTR));
		if (!StringUtils.isBlank(error)) {
			model.addAttribute(Constants.ERROR, error);
		}
		return new ModelAndView("perfil");
	}

	@PostMapping(value = "/perfil")
	public ModelAndView postPerfil(@ModelAttribute Piloto piloto, final HttpServletRequest request,
			final HttpServletResponse response, RedirectAttributes redirectAtt) {

		HttpSession sesion = request.getSession();
		String jwt = (String) sesion.getAttribute(Constants.JWT_ATTR);
		String user = (String) sesion.getAttribute(Constants.USER_ATTR);
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
			final HttpServletRequest request, final HttpServletResponse response) {

		HttpSession sesion = request.getSession();
		model.addAttribute("password",
				new Password(((Piloto) sesion.getAttribute(Constants.PILOTO_ATTR)).getId(), "", ""));
		if (!StringUtils.isBlank(error)) {
			model.addAttribute(Constants.ERROR, error);
		}
		return new ModelAndView("password");
	}

	@PostMapping(value = "/password")
	public ModelAndView cambioPasswordPost(@ModelAttribute Password password, final HttpServletRequest request,
			final HttpServletResponse response, RedirectAttributes redirectAtt) {

		HttpSession sesion = request.getSession();
		String jwt = (String) sesion.getAttribute(Constants.JWT_ATTR);
		String user = (String) sesion.getAttribute(Constants.USER_ATTR);
		Boolean result = pilotos.cambiarPassword(password, jwt, user);
		if (result) {
			return new ModelAndView("redirect:/races/pilotos/password");
		} else {
			redirectAtt.addAttribute(Constants.ERROR, 500);
			return new ModelAndView("redirect:/races/pilotos/password");
		}
	}

}
