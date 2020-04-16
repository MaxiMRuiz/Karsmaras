package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.constants.Constants;
import com.races.entity.Reglamento;
import com.races.entity.Sesion;
import com.races.entity.TipoSesion;
import com.races.repository.SesionRepository;
import com.races.services.PuntuacionService;
import com.races.services.ReglamentoService;
import com.races.services.ResultadoService;
import com.races.services.SesionService;
import com.races.services.TipoSesionService;

/**
 * Implementacion de la interfaz SesionSevice
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("SesionService")
public class SesionServiceImpl implements SesionService {

	@Autowired
	SesionRepository sesionRepo;

	@Autowired
	ReglamentoService reglamentoService;

	@Autowired
	TipoSesionService tipoSesionService;

	@Autowired
	ResultadoService resultados;

	@Autowired
	PuntuacionService puntuaciones;

	private static final Log LOGGER = LogFactory.getLog(SancionServiceImpl.class);

	@Override
	public List<Sesion> buscarSesiones(Long id, Long idReglamento, String descripcion, Long idTipoSesion) {

		try {
			Sesion probe = new Sesion();
			probe.setId(id == null ? null : id);
			probe.setReglamento(idReglamento == null ? null : reglamentoService.buscarReglamento(idReglamento));
			probe.setTipoSesion(idTipoSesion == null ? null : tipoSesionService.buscarTipoSesion(idTipoSesion));
			return sesionRepo.findAll(Example.of(probe));
		} catch (RacesException e) {
			LOGGER.error(e);
			return sesionRepo.findAll();
		}

	}

	@Override
	public Sesion buscarSesion(Long id) throws RacesException {
		Optional<Sesion> opSesion = sesionRepo.findById(id);
		if (opSesion.isPresent()) {
			return opSesion.get();
		} else {
			throw new RacesException("Sesion no existe");
		}
	}

	@Override
	public boolean existeSesion(Long id) {
		return sesionRepo.findById(id).isPresent();
	}

	@Override
	public void crearSesionesReglamento(Reglamento reglamento) throws RacesException {
		for (int i = 0; i < reglamento.getnEntrenamientos(); i++) {
			Sesion sesion = new Sesion();
			TipoSesion tipoSesion = tipoSesionService.buscarTipoSesion(Constants.ENTRENAMIENTO_ID);
			sesion.setDescripcion(
					tipoSesion.getDescripcion() + (reglamento.getnEntrenamientos() > 1 ? " " + (i + 1) : ""));
			sesion.setReglamento(reglamento);
			sesion.setTipoSesion(tipoSesion);
			crearSesion(sesion, reglamento);
		}
		for (int i = 0; i < reglamento.getnClasificaciones(); i++) {
			Sesion sesion = new Sesion();
			TipoSesion tipoSesion = tipoSesionService.buscarTipoSesion(Constants.CLASIFICACION_ID);
			sesion.setDescripcion(
					tipoSesion.getDescripcion() + (reglamento.getnClasificaciones() > 1 ? " " + (i + 1) : ""));
			sesion.setReglamento(reglamento);
			sesion.setTipoSesion(tipoSesion);
			crearSesion(sesion, reglamento);
		}
		for (int i = 0; i < reglamento.getnCarreras(); i++) {
			Sesion sesion = new Sesion();
			TipoSesion tipoSesion = tipoSesionService.buscarTipoSesion(Constants.CARRERA_ID);
			sesion.setDescripcion(tipoSesion.getDescripcion() + (reglamento.getnCarreras() > 1 ? " " + (i + 1) : ""));
			sesion.setReglamento(reglamento);
			sesion.setTipoSesion(tipoSesion);
			crearSesion(sesion, reglamento);
		}
	}

	private void crearSesion(Sesion sesion, Reglamento reglamento) {
		Sesion savedSesion = sesionRepo.save(sesion);
		puntuaciones.crearPuntuacionesSesion(savedSesion);
	}

}
