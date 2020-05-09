package com.races.portal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Puntuacion;
import com.races.portal.services.PuntuacionService;
import com.races.portal.services.TipoSesionService;

@Controller
@RequestMapping("/races/puntuaciones")
public class PuntuacionController {

	private static final Log LOGGER = LogFactory.getLog(PuntuacionController.class);

	@Autowired
	Environment env;

	@Autowired
	PuntuacionService puntuaciones;

	@Autowired
	TipoSesionService tipoSesiones;

	@GetMapping(value = "/{idSesion}")
	public ModelAndView listaPuntuaciones(Model model, @PathVariable String idSesion,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		List<Puntuacion> listaPuntuaciones = puntuaciones.buscarPuntuaciones(idSesion, jwt, user);
		model.addAttribute("listaPuntuaciones", listaPuntuaciones);
		model.addAttribute(Constants.PARAM_ID, idSesion);
		return new ModelAndView("puntuaciones");
	}

	@GetMapping(value = "/{id}/{obj}")
	public ModelAndView formularioPuntuaciones(Model model, @PathVariable String id, @PathVariable String obj,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		Puntuacion puntuacion;
		if ("new".equals(obj)) {
			puntuacion = new Puntuacion();
		} else {
			puntuacion = puntuaciones.buscarPuntuacion(id, obj, jwt, user);
		}
		model.addAttribute("tipoSesiones", tipoSesiones.buscarTiposSesiones(jwt, user));
		model.addAttribute(Constants.PARAM_ID, id);
		model.addAttribute("puntuacion", puntuacion);
		return new ModelAndView("puntuacion");
	}

	@DeleteMapping(value = "/{id}/{obj}")
	public ResponseEntity<Boolean> borrarPuntuacion(Model model, @PathVariable String id, @PathVariable String obj,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		if (null == id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(puntuaciones.borrarPuntuacion(obj, jwt, user), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/{reglamento}")
	public ModelAndView postFormularioPuntuacion(Model model, @PathVariable Long reglamento,
			@ModelAttribute Puntuacion puntuacion,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		if (puntuacion.getId() != null) {
			puntuaciones.editarPuntuacion(reglamento, puntuacion, jwt, user);
		} else {
			puntuaciones.crearPuntuacion(reglamento, puntuacion, jwt, user);
		}
		return new ModelAndView("redirect:/races/puntuaciones/" + reglamento);
	}

	@PostMapping(value = "/{idSesion}/upload")
	public ModelAndView uploadPuntuacionesHandler(@PathVariable Long idSesion, @RequestParam("file") MultipartFile file,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {

		if (!file.isEmpty() && FilenameUtils.getExtension(file.getOriginalFilename()).equals("txt")) {

			// Creating the directory to store file
			String rootPath = env.getProperty("files.upload.basepath");
			File dir = new File(rootPath + File.separator);
			if (!dir.exists())
				dir.mkdirs();

			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
			try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));) {

				stream.write(file.getBytes());

			} catch (Exception e) {
				LOGGER.error("Fallo en la carga del fichero => " + e.getMessage());
			}
			puntuaciones.sendFile(serverFile, idSesion, jwt, user);
		} else {
			LOGGER.warn("Error en la carga del fichero debido a que está vacío o no tiene formato txt o csv.");
		}
		return new ModelAndView("redirect:/races/puntuaciones/" + idSesion);
	}
}
