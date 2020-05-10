package com.races.portal.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.races.portal.dto.Campeonato;
import com.races.portal.dto.Equipo;
import com.races.portal.dto.Inscripcion;
import com.races.portal.dto.Piloto;
import com.races.portal.services.CampeonatoService;
import com.races.portal.services.EquipoService;
import com.races.portal.services.InscripcionService;
import com.races.portal.services.PilotoService;

@Controller
@RequestMapping("/races/inscripciones")
public class InscripcionesController {

	private static final Log LOGGER = LogFactory.getLog(InscripcionesController.class);

	@Autowired
	InscripcionService inscripciones;

	@Autowired
	CampeonatoService campeonatos;

	@Autowired
	PilotoService pilotos;

	@Autowired
	EquipoService equipos;

	@GetMapping(value = "/{id}")
	public ModelAndView listaInscripciones(Model model, @PathVariable String id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		List<Inscripcion> listaInscripciones;
		switch (id.charAt(0)) {
		case 'c':
			LOGGER.info("Listando inscripciones del campeonato " + id.substring(1));
			listaInscripciones = inscripciones.buscarInscripciones(id.substring(1), null, null, jwt, user);
			model.addAttribute(Constants.PARAM_NOMBRE,
					campeonatos.buscarCampeonato(id.substring(1), jwt, user).toString());
			break;
		case 'p':
			LOGGER.info("Listando inscripciones del piloto " + id.substring(1));
			listaInscripciones = inscripciones.buscarInscripciones(null, id.substring(1), null, jwt, user);
			model.addAttribute(Constants.PARAM_NOMBRE, pilotos.buscarPilotos(id.substring(1), jwt, user).toString());
			break;
		case 'e':
			LOGGER.info("Listando inscripciones del equipo " + id.substring(1));
			listaInscripciones = inscripciones.buscarInscripciones(null, null, id.substring(1), jwt, user);
			model.addAttribute(Constants.PARAM_NOMBRE, equipos.buscarEquipos(id.substring(1), jwt, user).toString());
			break;
		default:
			LOGGER.info("Listando todas las inscripciones");
			listaInscripciones = inscripciones.buscarInscripciones(null, null, null, jwt, user);
		}
		model.addAttribute("type", id.substring(0, 1));
		model.addAttribute("listaInscripciones", listaInscripciones);
		model.addAttribute(Constants.PARAM_ID, id.substring(1));

		model.addAttribute("urlServices", "/races/inscripciones/" + id);
		return new ModelAndView("inscripciones");
	}

	@GetMapping(value = "/{type}/{id}")
	public ModelAndView formularioInscripciones(Model model, @PathVariable String type, @PathVariable String id,
			@ModelAttribute String nombre, @SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		List<Campeonato> listaCampeonatos = campeonatos.buscarCampeonatos(null, null, null, null, jwt, user);
		List<Piloto> listaPilotos = pilotos.buscarPilotos(null, null, null, null, jwt, user);
		List<Equipo> listaEquipos = equipos.buscarEquipos(null, null, null, jwt, user);
		List<Inscripcion> listaInscripciones;
		Inscripcion base = new Inscripcion();
		switch (type) {
		case "c":
			listaInscripciones = inscripciones.buscarInscripciones(id, null, null, jwt, user);
			for (Inscripcion inscripcion : listaInscripciones) {
				if (listaPilotos.contains(inscripcion.getPiloto())) {
					listaPilotos.remove(inscripcion.getPiloto());
				}
			}
			model.addAttribute(Constants.PARAM_NOMBRE, campeonatos.buscarCampeonato(id, jwt, user).toString());
			break;
		case "p":
			listaInscripciones = inscripciones.buscarInscripciones(null, id, null, jwt, user);
			for (Inscripcion inscripcion : listaInscripciones) {
				if (listaCampeonatos.contains(inscripcion.getCampeonato())) {
					listaCampeonatos.remove(inscripcion.getCampeonato());
				}
			}
			model.addAttribute(Constants.PARAM_NOMBRE, pilotos.buscarPilotos(id, jwt, user).toString());
			break;
		case "e":
			model.addAttribute(Constants.PARAM_NOMBRE, equipos.buscarEquipos(id, jwt, user).toString());
			break;
		default:
			return new ModelAndView("redirect:/races");
		}

		model.addAttribute("campeonatos", listaCampeonatos);
		model.addAttribute("pilotos", listaPilotos);
		model.addAttribute("equipos", listaEquipos);
		model.addAttribute(Constants.PARAM_ID, id);
		model.addAttribute("type", type);
		model.addAttribute("inscripcion", base);
		model.addAttribute("fullId", type + id);
		return new ModelAndView("inscripcion");
	}

	@PostMapping(value = "/{type}/{id}")
	public ModelAndView crearInscripcion(Model model, @PathVariable String type, @PathVariable Long id,
			@ModelAttribute Inscripcion inscripcion,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		LOGGER.info("Creando nueva inscripcion tipo " + type + " para id " + id);
		String redirect;
		switch (type) {
		case "c":
			redirect = "redirect:/races/inscripciones/c" + id;
			inscripcion.getCampeonato().setId(id);
			break;
		case "p":
			redirect = "redirect:/races/inscripciones/p" + id;
			inscripcion.getPiloto().setId(id);
			break;
		case "e":
			redirect = "redirect:/races/inscripciones/e" + id;
			inscripcion.getEquipo().setId(id);
			break;
		default:
			redirect = "redirect:/races";
		}
		inscripciones.crearInscripcion(inscripcion, jwt, user);
		return new ModelAndView(redirect);
	}

	@DeleteMapping(value = "/{type}/{id}")
	public ResponseEntity<Boolean> borrarInscripcion(Model model, @PathVariable String type, @PathVariable Long id,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(inscripciones.borrarInscripcion(id, jwt, user), HttpStatus.OK);
		}
	}

}
