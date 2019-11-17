package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.races.dto.VueltaDto;
import com.races.entity.Vuelta;
import com.races.repository.VueltaRepository;
import com.races.services.ResultadoService;
import com.races.services.VueltaService;

@Service("VueltaService")
public class VueltaServiceImpl implements VueltaService {

	@Autowired
	VueltaRepository vueltaRepo;

	@Autowired
	ResultadoService resultadoService;

	@Override
	public Vuelta crearVuelta(VueltaDto vueltaDto) {
		if (resultadoService.existsResultado(vueltaDto.getIdResultado())) {

			Vuelta newVuelta = new Vuelta();
			newVuelta.setnVuelta(vueltaDto.getnVuelta());
			newVuelta.setTiempo(vueltaDto.getTiempo());
			newVuelta.setResultado(resultadoService.getResultado(vueltaDto.getIdResultado()));
			return vueltaRepo.save(newVuelta);
		}
		return null;
	}

	@Override
	public List<Vuelta> getAllVueltas() {
		return vueltaRepo.findAll();
	}

	@Override
	public Vuelta getVuelta(Long id) {
		Optional<Vuelta> opVuelta = vueltaRepo.findById(id);
		if (opVuelta.isPresent()) {
			return opVuelta.get();
		}
		return null;
	}

	@Override
	public boolean existsVuelta(Long id) {
		return vueltaRepo.findById(id).isPresent();
	}

	@Override
	public boolean removeVuelta(Long id) {
		if (existsVuelta(id)) {
			vueltaRepo.delete(getVuelta(id));
		}
		return false;
	}

}
