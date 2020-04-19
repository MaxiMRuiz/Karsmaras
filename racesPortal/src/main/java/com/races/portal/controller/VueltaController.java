package com.races.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Resultado;
import com.races.portal.services.ResultadoService;
import com.races.portal.services.VueltaService;

@Controller
@RequestMapping("/races/vueltas")
public class VueltaController {

	private static final Log LOGGER = LogFactory.getLog(VueltaController.class);

	@Autowired
	Environment env;

	@Autowired
	ResultadoService resultados;

	@Autowired
	VueltaService vueltas;

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
	public ModelAndView listaVueltas(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@PathVariable Long idResultado, @RequestHeader(value = "referer", required = false) final String urlPrevia,
			final HttpServletRequest request, final HttpServletResponse response) {
		HttpSession sesion = request.getSession();
		String jwt = (String) sesion.getAttribute(Constants.JWT_ATTR);
		String user = (String) sesion.getAttribute(Constants.USER_ATTR);
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		model.addAttribute("listaVueltas", vueltas.buscarVueltas(idResultado, jwt, user));
		model.addAttribute(Constants.PARAM_ID, idGp);
		model.addAttribute(Constants.PARAM_ID_SESION, idSesion);
		model.addAttribute(Constants.PARAM_ID_RESULTADO, idSesion);
		Resultado resultado = resultados.buscarResultado(idResultado, jwt, user);
		model.addAttribute(Constants.PARAM_NOMBRE, resultado.getSesionGP().toString());
		model.addAttribute(Constants.PARAM_PILOTO, resultado.getInscripcion().getPiloto().toString());
		model.addAttribute("urlServices", "/races/sancion/" + idGp + "/" + idSesion + "/" + idResultado);
		return new ModelAndView("vueltas");
	}

}
