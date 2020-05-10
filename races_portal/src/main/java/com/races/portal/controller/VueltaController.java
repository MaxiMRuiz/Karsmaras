package com.races.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Resultado;
import com.races.portal.services.ResultadoService;
import com.races.portal.services.VueltaService;

@Controller
@RequestMapping("/races/vueltas")
public class VueltaController {

	@Autowired
	ResultadoService resultados;

	@Autowired
	VueltaService vueltas;

	@GetMapping(value = "/{idGp}/{idSesion}/{idResultado}")
	public ModelAndView listaVueltas(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@PathVariable Long idResultado, @SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
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
