package com.races.portal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.races.portal.dto.Clasificacion;
import com.races.portal.services.CampeonatoService;
import com.races.portal.services.ClasificacionService;
import com.races.portal.services.GranPremioService;

@Controller
@RequestMapping("/races/clasificacion")
public class ClasificacionController {

	private static final Log LOGGER = LogFactory.getLog(ClasificacionController.class);

	@Autowired
	Environment env;

	@Autowired
	ClasificacionService clasificacionService;

	@Autowired
	GranPremioService gpService;

	@Autowired
	CampeonatoService campeonatoService;

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

	@GetMapping(value = "/gp/{id}")
	public ModelAndView clasificacionGP(Model model, @PathVariable Long id,
			@RequestHeader(value = "referer", required = false) final String urlPrevia) {

		List<Clasificacion> listaPilotos = clasificacionService.clasificacionGp(id);
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		model.addAttribute(Constants.LISTA_CLASIFICACION, listaPilotos);
		model.addAttribute(Constants.LISTA_EQUIPOS, getListaEquipos(listaPilotos));
		model.addAttribute(Constants.TYPE, Constants.PARAM_PILOTO);
		model.addAttribute(Constants.PARAM_NOMBRE, gpService.buscarGranPremio(id));
		return new ModelAndView(Constants.CLASIFICACION);
	}

	@GetMapping(value = "/campeonato/{id}")
	public ModelAndView clasificacionCampeonato(Model model, @PathVariable Long id,
			@RequestHeader(value = "referer", required = false) final String urlPrevia) {
		List<Clasificacion> listaPilotos = clasificacionService.clasificacionCampeonato(id);
		model.addAttribute(Constants.URL_VOLVER, urlPrevia);
		model.addAttribute(Constants.LISTA_CLASIFICACION, listaPilotos);
		model.addAttribute(Constants.LISTA_EQUIPOS, getListaEquipos(listaPilotos));
		model.addAttribute(Constants.TYPE, Constants.PARAM_PILOTO);
		model.addAttribute(Constants.PARAM_NOMBRE, campeonatoService.buscarCampeonato(id.toString()));
		return new ModelAndView(Constants.CLASIFICACION);
	}

	private List<Clasificacion> getListaEquipos(List<Clasificacion> listaPilotos) {
		List<Clasificacion> listaEquipos = new ArrayList<>();
		for (Clasificacion clasificacion : listaPilotos) {
			int posicion = getEquipo(listaEquipos, clasificacion);
			if (posicion > -1) {
				listaEquipos.get(posicion).addPuntos(clasificacion.getPuntos());
			} else {
				listaEquipos.add(new Clasificacion(clasificacion));
			}
		}
		Collections.sort(listaEquipos);
		return listaEquipos;
	}

	private int getEquipo(List<Clasificacion> listaEquipos, Clasificacion clasificacion) {
		for (int posicion = 0; posicion < listaEquipos.size(); posicion++) {
			if (listaEquipos.get(posicion).getEquipo().getId().equals(clasificacion.getEquipo().getId())) {
				return posicion;
			}
		}
		return -1;
	}

}
