package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.races.entity.TipoSesion;
import com.races.repository.TipoSesionRepository;
import com.races.services.TipoSesionService;

@Service("TipoSesionService")
public class TipoSesionServiceImpl implements TipoSesionService {

	@Autowired
	TipoSesionRepository tipoSesionRepo;

	@Override
	public List<TipoSesion> getAllReglamentos() {
		return tipoSesionRepo.findAll();
	}

	@Override
	public TipoSesion getTipoSesion(Long id) {
		Optional<TipoSesion> op = tipoSesionRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public boolean existsTipoSesion(Long id) {
		return tipoSesionRepo.findById(id).isPresent();
	}

}
