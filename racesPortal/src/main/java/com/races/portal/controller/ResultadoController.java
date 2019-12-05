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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.races.portal.constants.Constants;
import com.races.portal.dto.Resultado;
import com.races.portal.services.ResultadoService;
import com.races.portal.services.SesionService;
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
	SesionService sesiones;

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

	@GetMapping(value = "/{idGp}/{idSesion}")
	public ModelAndView listaResultados(Model model, @PathVariable Long idGp, @PathVariable Long idSesion) {
		List<Resultado> listaResultados = resultados.buscarResultados(idSesion);
		model.addAttribute("listaResultados", listaResultados);
		model.addAttribute(Constants.PARAM_ID, idGp);
		model.addAttribute(Constants.PARAM_ID_SESION, idSesion);
		model.addAttribute("nombre", sesiones.buscarSesion(idSesion).toString());
		return new ModelAndView("resultados");
	}

	@GetMapping(value = "/{idGp}/{idSesion}/{id}")
	public ModelAndView formularioResultados(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@PathVariable Long id) {

		Resultado resultado = resultados.buscarResultado(id);
		model.addAttribute("resultado", resultado);
		model.addAttribute(Constants.PARAM_ID, idGp);
		model.addAttribute(Constants.PARAM_ID_SESION, idSesion);
		model.addAttribute("nombre", resultado.getPiloto().toString());
		model.addAttribute("sesion", resultado.getSesion().toString());
		return new ModelAndView("resultado");
	}

	@PostMapping(value = "/{idGp}/{idSesion}")
	public ModelAndView postFormularioPuntuacion(Model model, @PathVariable Long idGp, @PathVariable Long idSesion,
			@ModelAttribute Resultado resultado) {
		resultados.editarResultado(resultado);
		return new ModelAndView("redirect:/races/sesion/" + idGp + "/" + idSesion);
	}

	@PostMapping(value = "/{idGp}/{idSesion}/upload")
	public ModelAndView uploadFileHandler(@PathVariable Long idGp, @PathVariable Long idSesion,
			@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty() && FilenameUtils.getExtension(file.getOriginalFilename()).equals("txt")) {

			// Creating the directory to store file
			String rootPath = env.getProperty("files.upload.basepath");
			File dir = new File(rootPath + File.separator + "tmpFiles");
			if (!dir.exists())
				dir.mkdirs();

			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath() + File.separator + "uploadFile.txt");
			try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));) {

				stream.write(file.getBytes());

			} catch (Exception e) {
				LOGGER.error("Fallo en la carga del fichero => " + e.getMessage());
			}
			resultados.sendFile(serverFile, idSesion, idGp);
		} else {
			LOGGER.warn("Error en la carga del fichero debido a que está vacío o no tiene formato txt o csv.");
		}
		return new ModelAndView("redirect:/races/sesion/" + idGp + "/" + idSesion);
	}

}
