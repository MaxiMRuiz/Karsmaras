package com.races.portal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Clasificacion;
import com.races.portal.services.CampeonatoService;
import com.races.portal.services.ClasificacionService;
import com.races.portal.services.GranPremioService;

@Controller
@RequestMapping("/races/clasificacion")
public class ClasificacionController {

	@Autowired
	ClasificacionService clasificacionService;

	@Autowired
	GranPremioService gpService;

	@Autowired
	CampeonatoService campeonatoService;

	@GetMapping(value = "/gp/{id}")
	public ModelAndView clasificacionGP(Model model, @PathVariable Long id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		List<Clasificacion> listaPilotos = clasificacionService.clasificacionGp(id, jwt, user);
		model.addAttribute(Constants.LISTA_CLASIFICACION, listaPilotos);
		model.addAttribute(Constants.LISTA_EQUIPOS, getListaEquipos(listaPilotos));
		model.addAttribute(Constants.TYPE, Constants.PARAM_PILOTO);
		model.addAttribute(Constants.PARAM_NOMBRE, gpService.buscarGranPremio(id, jwt, user));
		return new ModelAndView(Constants.CLASIFICACION);
	}

	@GetMapping(value = "/campeonato/{id}")
	public ModelAndView clasificacionCampeonato(Model model, @PathVariable Long id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		List<Clasificacion> listaPilotos = clasificacionService.clasificacionCampeonato(id, jwt, user);
		model.addAttribute(Constants.LISTA_CLASIFICACION, listaPilotos);
		model.addAttribute(Constants.LISTA_EQUIPOS, getListaEquipos(listaPilotos));
		model.addAttribute(Constants.TYPE, Constants.PARAM_PILOTO);
		model.addAttribute(Constants.PARAM_NOMBRE, campeonatoService.buscarCampeonato(id.toString(), jwt, user));
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
