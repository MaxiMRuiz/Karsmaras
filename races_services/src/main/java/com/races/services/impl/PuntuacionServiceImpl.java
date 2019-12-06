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
import com.races.entity.Reglamento;
import com.races.entity.TipoSesion;
import com.races.repository.PuntuacionRepository;
import com.races.services.PuntuacionService;
import com.races.services.ReglamentoService;
import com.races.services.TipoSesionService;

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
	ReglamentoService reglamentoService;

	@Autowired
	TipoSesionService tipoSesionService;

	private static final Log LOGGER = LogFactory.getLog(PuntuacionServiceImpl.class);

	@Override
	public Puntuacion crearPuntuacion(PuntuacionDto puntuacionDto) throws RacesException {
		if (reglamentoService.existeReglamento(puntuacionDto.getIdReglamento())
				&& tipoSesionService.existeTipoSesion(puntuacionDto.getIdTipoSesion()) && isValid(puntuacionDto)) {
			Puntuacion puntuacion = new Puntuacion(reglamentoService.buscarReglamento(puntuacionDto.getIdReglamento()),
					puntuacionDto.getPosicion(), puntuacionDto.getPuntos(),
					tipoSesionService.buscarTipoSesion(puntuacionDto.getIdTipoSesion()));
			return puntuacionRepo.save(puntuacion);
		}
		return null;
	}

	private boolean isValid(PuntuacionDto puntuacionDto) throws RacesException {
		return !puntuacionRepo
				.findOne(Example.of(new Puntuacion(reglamentoService.buscarReglamento(puntuacionDto.getIdReglamento()),
						puntuacionDto.getPosicion(), null,
						tipoSesionService.buscarTipoSesion(puntuacionDto.getIdTipoSesion()))))
				.isPresent();
	}

	@Override
	public List<Puntuacion> buscarPuntuaciones(Long id, Long idReglamento, Integer posicion, Integer puntos,
			Long idTipoSesion) {

		try {
			Example<Puntuacion> example = Example.of(new Puntuacion(id == null ? null : id,
					idReglamento == null ? null : reglamentoService.buscarReglamento(idReglamento),
					posicion == null ? null : posicion, puntos == null ? null : puntos,
					idTipoSesion == null ? null : tipoSesionService.buscarTipoSesion(idTipoSesion)));

			return puntuacionRepo.findAll(example,
					new Sort(Sort.Direction.DESC, "tipoSesion.id").and(new Sort(Sort.Direction.ASC, "posicion")));
		} catch (RacesException e) {
			LOGGER.error(e);
			return puntuacionRepo.findAll();
		}
	}

	@Override
	public List<Puntuacion> buscarPuntuacionesValidas(Long id) {
		return puntuacionRepo.findByReglamentoIdAndPuntosGreaterThan(id,0);
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
	public boolean borrarPuntuacion(Long id) throws RacesException {
		puntuacionRepo.delete(buscarPuntuacion(id));
		return true;
	}

	@Override
	public boolean existePuntuacion(Long id) {
		return puntuacionRepo.findById(id).isPresent();
	}

	@Override
	public void crearPuntuacionesReglamento(Reglamento reglamento) {
		if (reglamento.getnEntrenamientos() > 0) {
			crearPuntuaciones(reglamento, tipoSesionService.buscarTipoSesiones(null, "Entrenamiento").get(0),
					reglamento.getnPilotos());
		}
		if (reglamento.getnClasificaciones() > 0) {
			crearPuntuaciones(reglamento, tipoSesionService.buscarTipoSesiones(null, "Clasificacion").get(0),
					reglamento.getnPilotos());
		}
		if (reglamento.getnCarreras() > 0) {
			crearPuntuaciones(reglamento, tipoSesionService.buscarTipoSesiones(null, "Carrera").get(0),
					reglamento.getnPilotos());
		}
	}

	/**
	 * Metodo para crear las puntuaciones de un reglamento
	 * 
	 * @param reglamento
	 * @param tipoSesion
	 * @param nPilotos
	 */
	private void crearPuntuaciones(Reglamento reglamento, TipoSesion tipoSesion, Integer nPilotos) {
		List<Puntuacion> listaPuntuaciones = new ArrayList<>();
		for (int i = 0; i < nPilotos; i++) {
			listaPuntuaciones.add(new Puntuacion(reglamento, i + 1, 0, tipoSesion));
		}
		puntuacionRepo.saveAll(listaPuntuaciones);
	}

}
