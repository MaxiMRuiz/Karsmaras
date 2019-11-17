package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.races.dto.SesionDto;
import com.races.entity.Sesion;
import com.races.repository.SesionRepository;
import com.races.services.GranPremioService;
import com.races.services.SesionService;
import com.races.services.TipoSesionService;

@Service("SesionService")
public class SesionServiceImpl implements SesionService {

	@Autowired
	SesionRepository sesionRepo;

	@Autowired
	GranPremioService gpService;

	@Autowired
	TipoSesionService tipoSesionService;

	@Override
	public Sesion crearSesion(SesionDto sesionDto) {
		if (gpService.existeGranPremio(sesionDto.getIdGranPremio())) {

			Sesion newSesion = new Sesion();
			newSesion.setFecha(sesionDto.getFecha());
			newSesion.setGranPremio(gpService.getGranPremio(sesionDto.getIdGranPremio()));
			newSesion.setTipoSesion(tipoSesionService.getTipoSesion(sesionDto.getIdTipoSesion()));
			return sesionRepo.save(newSesion);
		}
		return null;
	}

	@Override
	public List<Sesion> getAllSesion() {
		return sesionRepo.findAll();
	}

	@Override
	public Sesion getSesion(Long id) {
		Optional<Sesion> opSesion = sesionRepo.findById(id);
		if (opSesion.isPresent()) {
			return opSesion.get();
		}
		return null;
	}

	@Override
	public boolean existsSesion(Long id) {
		return sesionRepo.findById(id).isPresent();
	}

	@Override
	public boolean removeSesion(Long id) {
		if (existsSesion(id)) {
			sesionRepo.delete(getSesion(id));
		}
		return false;
	}

}
