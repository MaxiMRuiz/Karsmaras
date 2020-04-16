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
import com.races.dto.SesionGpDto;
import com.races.entity.GranPremio;
import com.races.entity.Sesion;
import com.races.entity.SesionGP;
import com.races.repository.SesionGpRepository;
import com.races.services.GranPremioService;
import com.races.services.ResultadoService;
import com.races.services.SesionGpService;
import com.races.services.SesionService;

/**
 * Implementacion de la interfaz SesionSevice
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("SesionGpService")
public class SesionGpServiceImpl implements SesionGpService {

	@Autowired
	SesionGpRepository sesionGpRepo;

	@Autowired
	GranPremioService gpService;

	@Autowired
	SesionService sesionService;
	
	@Autowired
	ResultadoService resultados;

	private static final Log LOGGER = LogFactory.getLog(SancionServiceImpl.class);

	@Override
	public SesionGP crearSesion(SesionGpDto sesionGpDto) throws RacesException {
		SesionGP newSesion = new SesionGP();
		newSesion.setFecha(sesionGpDto.getFecha());
		newSesion.setGranPremio(gpService.buscarGranPremio(sesionGpDto.getIdGranPremio()));
		newSesion.setSesion(sesionService.buscarSesion(sesionGpDto.getIdSesion()));
		if (sesionGpRepo.findOne(Example.of(newSesion)).isPresent()) {
			throw new RacesException("Sesion duplicada");
		}
		return sesionGpRepo.save(newSesion);
	}

	@Override
	public List<SesionGP> buscarSesiones(Long id, Long idGp, Date fecha, Long idSesion) {

		List<SesionGP> list;
		try {
			SesionGP probe = new SesionGP();
			probe.setId(id == null ? null : id);
			probe.setGranPremio(idGp == null ? null : gpService.buscarGranPremio(idGp));
			probe.setSesion(idSesion == null ? null : sesionService.buscarSesion(idSesion));
			list = sesionGpRepo.findAll(Example.of(probe));
		} catch (RacesException e) {
			LOGGER.error(e);
			list = sesionGpRepo.findAll();
		}
		if (fecha == null) {
			return list;
		} else {
			List<SesionGP> filterList = new ArrayList<>();
			for (SesionGP sesion : list) {
				if (sesion.getFecha().after(fecha)) {
					filterList.add(sesion);
				}
			}
			return filterList;
		}

	}

	@Override
	public SesionGP buscarSesion(Long id) throws RacesException {
		Optional<SesionGP> opSesion = sesionGpRepo.findById(id);
		if (opSesion.isPresent()) {
			return opSesion.get();
		} else {
			throw new RacesException("Sesion no existe");
		}
	}

	@Override
	public boolean existeSesion(Long id) {
		return sesionGpRepo.findById(id).isPresent();
	}

	@Override
	public boolean borrarSesion(Long id) throws RacesException {
		sesionGpRepo.delete(buscarSesion(id));
		return true;
	}

	@Override
	public void crearSesionesGranPremio(GranPremio newGp, Date fecha) {
		List<SesionGP> listSesionGp = new ArrayList<>();
		List<Sesion> listSesiones = sesionService.buscarSesiones(null, newGp.getCampeonato().getReglamento().getId(), null, null);
		for (Sesion sesion : listSesiones) {
			SesionGP sesionGp = new SesionGP();
			sesionGp.setFecha(new java.sql.Date(fecha.getTime()));
			sesionGp.setGranPremio(newGp);
			sesionGp.setSesion(sesion);
			listSesionGp.add(sesionGp);
		}
		listSesionGp = sesionGpRepo.saveAll(listSesionGp);
		resultados.crearResultados(listSesionGp, newGp.getCampeonato());
		
	}

}
