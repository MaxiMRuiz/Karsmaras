package com.races.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.races.portal.constants.Constants;
import com.races.portal.dto.LoginDto;
import com.races.portal.dto.LoginResponse;
import com.races.portal.dto.Piloto;
import com.races.portal.services.AuthService;
import com.races.portal.services.PilotoService;

import io.micrometer.core.instrument.util.StringUtils;

@Controller
@RequestMapping({ "/races", "/" })
public class PortalController {

	private static final Log LOGGER = LogFactory.getLog(PortalController.class);

	@Autowired
	Environment env;

	@Autowired
	AuthService authService;

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

	@GetMapping("/races")
	public ModelAndView mainPage() {
		return new ModelAndView("home");
	}

	@GetMapping("/")
	public ModelAndView mainRedirectPage() {
		return new ModelAndView("redirect:/races");
	}

	@GetMapping(value = "/races/login")
	public String loginPage(Model model, @RequestParam(required = false) String error) {
		model.addAttribute("loginDto", new LoginDto());
		if (!StringUtils.isBlank(error)) {
			model.addAttribute("error", error);
		}
		return "login";
	}

	@PostMapping(value = "/races/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView processLogin(LoginDto login, final HttpServletRequest request,
			final HttpServletResponse response, RedirectAttributes redirectAtt) {
		try {
			LoginResponse credentials = authService.login(login);
			HttpSession sesion = request.getSession();
			sesion.setAttribute(Constants.JWT_ATTR, credentials.getJwt());
			sesion.setAttribute(Constants.USER_ATTR, login.getUser());
			sesion.setAttribute("admin", credentials.getAdmin());
			sesion.setAttribute(Constants.JWT_EXPIRED, System.currentTimeMillis() + 3600000); // 1 hora de duracion en milisegundos
			sesion.setAttribute(Constants.PILOTO_ATTR, pilotos
					.buscarPilotos(null, null, null, login.getUser(), credentials.getJwt(), login.getUser()).get(0));
			return new ModelAndView("redirect:/races");
		} catch (Exception e) {
			redirectAtt.addAttribute(Constants.ERROR, 403);
			return new ModelAndView("redirect:/races/login");
		}

	}

	@GetMapping(value = "/races/registro")
	public ModelAndView registro(Model model, @RequestParam(required = false) String error) {
		Piloto piloto = new Piloto();
		model.addAttribute("piloto", piloto);
		if (!StringUtils.isBlank(error)) {
			model.addAttribute(Constants.ERROR, error);
		}
		return new ModelAndView("registro");
	}

	@PostMapping(value = "/races/registro")
	public ModelAndView postRegistro(@ModelAttribute Piloto piloto, final HttpServletRequest request,
			final HttpServletResponse response, RedirectAttributes redirectAtt) {

		if (pilotos.crearPiloto(piloto)) {
			return processLogin(new LoginDto(piloto.getApodo(), piloto.getPassword()), request, response, redirectAtt);
		} else {
			redirectAtt.addAttribute(Constants.ERROR, 500);
			return new ModelAndView("redirect:/races/registro");
		}
	}

}
