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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Resultado;
import com.races.portal.services.ResultadoService;
import com.races.portal.services.SesionGpService;
import com.races.portal.services.TipoSesionService;

@Controller
@RequestMapping("/races/sesion")
public class ResultadoController {

	private static final Log LOGGER = LogFactory.getLog(ResultadoController.class);

	@Autowired
	Environment env;

	@Autowired
	ResultadoService resultados;

	@Autowired
	TipoSesionService tipoSesiones;

	@Autowired
	SesionGpService sesionesGp;

	@GetMapping(value = "/{idGp}/{idSesion}")
	public ModelAndView listaResultados(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@SessionAttribute(name = Constants.JWT_ATTR, required = true) String jwt,
			@SessionAttribute(name = Constants.USER_ATTR, required = true) String user) {
		List<Resultado> listaResultados = resultados.buscarResultados(idSesion, jwt, user);
		model.addAttribute("listaResultados", listaResultados);
		model.addAttribute(Constants.PARAM_ID, idGp);
		model.addAttribute(Constants.PARAM_ID_SESION, idSesion);
		model.addAttribute("nombre", sesionesGp.buscarSesion(idSesion, jwt, user).toString());
		return new ModelAndView("resultados");
	}

	@PostMapping(value = "/{idGp}/{idSesion}/upload")
	public ModelAndView uploadFileHandler(@PathVariable Long idGp, @PathVariable Long idSesion,
			@RequestParam("file") MultipartFile file,
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
			resultados.sendFile(serverFile, idSesion, idGp, jwt, user);
		} else {
			LOGGER.warn("Error en la carga del fichero debido a que está vacío o no tiene formato txt o csv.");
		}
		return new ModelAndView("redirect:/races/sesion/" + idGp + "/" + idSesion);
	}

}
