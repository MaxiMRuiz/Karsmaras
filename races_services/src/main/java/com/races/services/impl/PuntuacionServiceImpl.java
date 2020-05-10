package com.races.services.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.races.dto.PuntuacionDto;
import com.races.entity.Puntuacion;
import com.races.entity.Sesion;
import com.races.exception.RacesException;
import com.races.repository.PuntuacionRepository;
import com.races.services.PuntuacionService;
import com.races.services.SesionService;

/**
 * Implementacion de la interfaz PuntuacionService
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("PuntuacionService")
public class PuntuacionServiceImpl implements PuntuacionService {

	@Autowired
	PuntuacionRepository puntuacionRepo;

	@Autowired
	SesionService sesionService;

	@Autowired
	Environment env;

	private static final Log LOGGER = LogFactory.getLog(PuntuacionServiceImpl.class);

	@Override
	public List<Puntuacion> buscarPuntuaciones(Long id, Long idSesion, Integer posicion, Integer puntos) {

		try {
			Example<Puntuacion> example = Example.of(new Puntuacion(id == null ? null : id,
					idSesion == null ? null : sesionService.buscarSesion(idSesion), posicion == null ? null : posicion,
					puntos == null ? null : puntos));

			return puntuacionRepo.findAll(example,
					new Sort(Sort.Direction.DESC, "sesion.id").and(new Sort(Sort.Direction.ASC, "posicion")));
		} catch (RacesException e) {
			LOGGER.error(e);
			return puntuacionRepo.findAll();
		}
	}

	@Override
	public List<Puntuacion> buscarPuntuacionesValidas(Long id) {
		return puntuacionRepo.findBySesionIdAndPuntosGreaterThan(id, 0);
	}

	@Override
	public Puntuacion buscarPuntuacion(Long id) throws RacesException {
		Optional<Puntuacion> puntuacion = puntuacionRepo.findById(id);
		if (puntuacion.isPresent()) {
			return puntuacion.get();
		} else {
			throw new RacesException("Puntuacion no encontrada");
		}
	}

	@Override
	public Puntuacion actualizarPuntuacion(Long id, PuntuacionDto puntuacionDto) throws RacesException {
		Puntuacion puntuacion = buscarPuntuacion(id);
		puntuacion.setPosicion(puntuacionDto.getPosicion());
		puntuacion.setPuntos(puntuacionDto.getPuntos());
		return puntuacionRepo.save(puntuacion);
	}

	@Override
	public boolean existePuntuacion(Long id) {
		return puntuacionRepo.findById(id).isPresent();
	}

	@Override
	public void crearPuntuacionesSesion(Sesion sesion) {
		List<Puntuacion> listaPuntuaciones = new ArrayList<>();
		for (int i = 0; i < sesion.getReglamento().getnPilotos(); i++) {
			listaPuntuaciones.add(new Puntuacion(sesion, i + 1, 0));
		}
		puntuacionRepo.saveAll(listaPuntuaciones);
	}

	@Override
	public void processFile(Long idSesion, MultipartFile file) throws RacesException {
		Sesion sesion = sesionService.buscarSesion(idSesion);
		String rootPath = env.getProperty("race.files.upload.basepath");
		File dir = new File(rootPath);
		if (!dir.exists())
			dir.mkdirs();

		File serverFile = new File(
				dir.getAbsolutePath() + File.separator + "Puntuacion" + idSesion + System.currentTimeMillis() + ".txt");
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {

			stream.write(file.getBytes());
		} catch (IOException e) {
			LOGGER.error(e);
		}

		String line;
		List<Puntuacion> listaPuntuaciones = new ArrayList<>();
		try (InputStream inputStream = file.getInputStream();
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));) {
			if ((line = bufferReader.readLine()) != null) {
				String[] posiciones = line.split(";");
				if (posiciones.length > 1) {
					for (int i = 0; i < posiciones.length; i++) {
						listaPuntuaciones.add(new Puntuacion(sesion, i + 1, Integer.parseInt(posiciones[i])));
					}
				}
			}
			LOGGER.info("Fichero procesado");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		List<Puntuacion> puntuacionesSesion = puntuacionRepo.findBySesionId(idSesion,
				new Sort(Sort.Direction.DESC, "sesion.id"));
		for (Puntuacion puntuacionOriginal : puntuacionesSesion) {
			for (Puntuacion nuevaPuntuacion : listaPuntuaciones) {
				if (puntuacionOriginal.getPosicion().equals(nuevaPuntuacion.getPosicion())) {
					puntuacionOriginal.setPuntos(nuevaPuntuacion.getPuntos());
				}
			}
		}
		puntuacionRepo.saveAll(puntuacionesSesion);
	}

}
