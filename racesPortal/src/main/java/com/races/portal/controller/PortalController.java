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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.races.portal.dto.LoginDto;
import com.races.portal.dto.LoginResponse;
import com.races.portal.dto.Piloto;
import com.races.portal.services.AuthService;

import io.micrometer.core.instrument.util.StringUtils;

@Controller
@RequestMapping({ "/races", "/" })
public class PortalController {

	private static final Log LOGGER = LogFactory.getLog(PortalController.class);

	@Autowired
	Environment env;

	@Autowired
	AuthService authService;

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
			sesion.setAttribute("jwt", credentials.getJwt());
			sesion.setAttribute("user", login.getUser());
			sesion.setAttribute("admin", credentials.getAdmin());
			sesion.setAttribute("expired", System.currentTimeMillis() + 3600000); // 1 hora de duracion en milisegundos
			return new ModelAndView("redirect:/races");
		} catch (Exception e) {
			redirectAtt.addAttribute("error", 403);
			return new ModelAndView("redirect:/races/login");
		}

	}

	@GetMapping(value = "/races/registro")
	public ModelAndView registro(Model model) {
		Piloto piloto = new Piloto();
		model.addAttribute("piloto", piloto);
		return new ModelAndView("registro");
	}

}
