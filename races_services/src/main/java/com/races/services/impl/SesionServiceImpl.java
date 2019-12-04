package com.races.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.dto.SesionDto;
import com.races.entity.GranPremio;
import com.races.entity.Sesion;
import com.races.entity.TipoSesion;
import com.races.repository.SesionRepository;
import com.races.services.GranPremioService;
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
	GranPremioService gpService;

	@Autowired
	TipoSesionService tipoSesionService;
	
	@Autowired
	ResultadoService resultados;

	private static final Log LOGGER = LogFactory.getLog(SancionServiceImpl.class);

	@Override
	public Sesion crearSesion(SesionDto sesionDto) throws RacesException {
		Sesion newSesion = new Sesion();
		newSesion.setFecha(sesionDto.getFecha());
		newSesion.setGranPremio(gpService.buscarGranPremio(sesionDto.getIdGranPremio()));
		newSesion.setTipoSesion(tipoSesionService.buscarTipoSesion(sesionDto.getIdTipoSesion()));
		if (sesionRepo.findOne(Example.of(newSesion)).isPresent()) {
			throw new RacesException("Sesion duplicada");
		}
		return sesionRepo.save(newSesion);
	}

	@Override
	public List<Sesion> buscarSesiones(Long id, Long idGp, Date fecha, Long idTipoSesion) {

		List<Sesion> list;
		try {
			Sesion probe = new Sesion();
			probe.setId(id == null ? null : id);
			probe.setGranPremio(idGp == null ? null : gpService.buscarGranPremio(idGp));
			probe.setTipoSesion(idTipoSesion == null ? null : tipoSesionService.buscarTipoSesion(idTipoSesion));
			list = sesionRepo.findAll(Example.of(probe));
		} catch (RacesException e) {
			LOGGER.error(e);
			list = sesionRepo.findAll();
		}
		if (fecha == null) {
			return list;
		} else {
			List<Sesion> filterList = new ArrayList<>();
			for (Sesion sesion : list) {
				if (sesion.getFecha().after(fecha)) {
					filterList.add(sesion);
				}
			}
			return filterList;
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
	public boolean borrarSesion(Long id) throws RacesException {
		sesionRepo.delete(buscarSesion(id));
		return true;
	}

	@Override
	public void crearSesionesGranPremio(GranPremio newGp, Date fecha) {
		if (newGp.getCampeonato().getReglamento().getnEntrenamientos() > 0) {
			crearSesionesGp(newGp, newGp.getCampeonato().getReglamento().getnEntrenamientos(),
					tipoSesionService.buscarTipoSesiones(null, "Entrenamiento").get(0), fecha);
		}
		if (newGp.getCampeonato().getReglamento().getnClasificaciones() > 0) {
			crearSesionesGp(newGp, newGp.getCampeonato().getReglamento().getnClasificaciones(),
					tipoSesionService.buscarTipoSesiones(null, "Clasificacion").get(0), fecha);
		}
		if (newGp.getCampeonato().getReglamento().getnCarreras() > 0) {
			crearSesionesGp(newGp, newGp.getCampeonato().getReglamento().getnCarreras(),
					tipoSesionService.buscarTipoSesiones(null, "Carrera").get(0), fecha);
		}
	}

	/**
	 * Crea todas las sesiones de un Gran premio en base al reglamento asociado al
	 * campeonato
	 * 
	 * @param newGp
	 * @param nSesiones
	 * @param tipoSesion
	 */
	private void crearSesionesGp(GranPremio newGp, Integer nSesiones, TipoSesion tipoSesion, Date fecha) {
		List<Sesion> listSesion = new ArrayList<>();
		for (int i = 0; i < nSesiones; i++) {
			Sesion sesion = new Sesion();
			sesion.setFecha(new java.sql.Date(fecha.getTime()));
			sesion.setGranPremio(newGp);
			sesion.setTipoSesion(tipoSesion);
			listSesion.add(sesion);
		}
		listSesion = sesionRepo.saveAll(listSesion);
		resultados.crearResultados(listSesion, newGp.getCampeonato());
	}

}
