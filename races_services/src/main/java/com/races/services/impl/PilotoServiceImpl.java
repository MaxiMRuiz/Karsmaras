package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.dto.PilotoDto;
import com.races.entity.Piloto;
import com.races.repository.PilotoRepository;
import com.races.services.PilotoService;

@Service("PilotoService")
public class PilotoServiceImpl implements PilotoService {

	@Autowired
	@Qualifier("PilotoRepository")
	PilotoRepository pilotoRepo;

	public Piloto crearPiloto(PilotoDto pilotoDto) {
		Piloto piloto = new Piloto(pilotoDto);
		return pilotoRepo.save(piloto);

	}

	@Override
	public Piloto updatePiloto(Long id, PilotoDto pilotoDto) {
		Optional<Piloto> optPiloto = pilotoRepo.findById(id);
		if (optPiloto.isPresent()) {
			Piloto piloto = optPiloto.get();
			piloto.setNombre(pilotoDto.getNombre());
			piloto.setApellido(pilotoDto.getApellido());
			piloto.setApodo(pilotoDto.getApodo());
			return pilotoRepo.save(piloto);
		}
		return null;
	}

	@Override
	public boolean borrarPiloto(Long id) {

		if (existsPiloto(id)) {
			pilotoRepo.delete(getPiloto(id));
		}
		return false;
	}

	@Override
	public Piloto getPiloto(Long id) {
		Optional<Piloto> opPiloto = pilotoRepo.findById(id);
		if (opPiloto.isPresent()) {
			return opPiloto.get();
		}
		return null;
	}

	@Override
	public List<Piloto> getAllPilotos(Long id, String nombre, String apellido, String apodo) {
		if (id == null && StringUtils.isBlank(nombre) && StringUtils.isBlank(apellido) && StringUtils.isBlank(apodo)) {
			return pilotoRepo.findAll();
		} else {
			Example<Piloto> example = Example.of(new Piloto(id, nombre, apellido, apodo));
			return pilotoRepo.findAll(example);
		}
	}

	@Override
	public boolean existsPiloto(Long id) {
		return (getPiloto(id) != null);
	}

}
