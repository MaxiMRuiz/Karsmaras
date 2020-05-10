package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.entity.TipoSesion;
import com.races.exception.RacesException;
import com.races.repository.TipoSesionRepository;
import com.races.services.TipoSesionService;

/**
 * Implementacion de la interfaz TipoSesionService
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("TipoSesionService")
public class TipoSesionServiceImpl implements TipoSesionService {

	@Autowired
	TipoSesionRepository tipoSesionRepo;

	@Override
	public List<TipoSesion> buscarTipoSesiones(Long id, String descripcion) {
		if (id == null && StringUtils.isBlank(descripcion)) {
			return tipoSesionRepo.findAll();
		} else {
			return tipoSesionRepo.findAll(Example.of(new TipoSesion(id, descripcion)));
		}
	}

	@Override
	public TipoSesion buscarTipoSesion(Long id) throws RacesException {
		Optional<TipoSesion> op = tipoSesionRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		} else {
			throw new RacesException("Tipo de Sesion no encontrada");
		}
	}

	@Override
	public boolean existeTipoSesion(Long id) {
		return tipoSesionRepo.findById(id).isPresent();
	}

}
