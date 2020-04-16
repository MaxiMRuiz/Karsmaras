package com.races.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.dto.PuntuacionDto;
import com.races.entity.Puntuacion;
import com.races.entity.Sesion;
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
		crearPuntuaciones(sesion, sesion.getReglamento().getnPilotos());
	}

	/**
	 * Metodo para crear las puntuaciones de un reglamento
	 * 
	 * @param reglamento
	 * @param tipoSesion
	 * @param nPilotos
	 */
	private void crearPuntuaciones(Sesion sesion, Integer nPilotos) {
		List<Puntuacion> listaPuntuaciones = new ArrayList<>();
		for (int i = 0; i < nPilotos; i++) {
			listaPuntuaciones.add(new Puntuacion(sesion, i + 1, 0));
		}
		puntuacionRepo.saveAll(listaPuntuaciones);
	}

}
