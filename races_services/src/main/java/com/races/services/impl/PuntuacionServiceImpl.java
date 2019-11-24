package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.dto.PuntuacionDto;
import com.races.entity.Puntuacion;
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
				&& tipoSesionService.existeTipoSesion(puntuacionDto.getIdTipoSesion())) {
			Puntuacion puntuacion = new Puntuacion(reglamentoService.buscarReglamento(puntuacionDto.getIdReglamento()),
					puntuacionDto.getPosicion(), puntuacionDto.getPuntos(),
					tipoSesionService.buscarTipoSesion(puntuacionDto.getIdTipoSesion()));
			return puntuacionRepo.save(puntuacion);
		}
		return null;
	}

	@Override
	public List<Puntuacion> buscarPuntuaciones(Long id, Long idReglamento, Integer posicion, Integer puntos,
			Long idTipoSesion) {
		if ((idReglamento == null || !reglamentoService.existeReglamento(idReglamento)) && posicion == null
				&& puntos == null && (idTipoSesion == null || !tipoSesionService.existeTipoSesion(idTipoSesion))) {
			return puntuacionRepo.findAll();
		} else {
			try {
				Example<Puntuacion> example = Example
						.of(new Puntuacion(id, reglamentoService.buscarReglamento(idReglamento), posicion, puntos,
								tipoSesionService.buscarTipoSesion(idTipoSesion)));

				return puntuacionRepo.findAll(example);
			} catch (RacesException e) {
				LOGGER.error(e);
				return puntuacionRepo.findAll();
			}
		}
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

}
