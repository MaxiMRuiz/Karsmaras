package com.races.auth.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.races.auth.entity.Piloto;
import com.races.auth.repository.PilotoRepository;
import com.races.auth.services.PilotoService;

@Service("PilotoService")
public class PilotoServiceImpl implements PilotoService {

	@Autowired
	@Qualifier("PilotoRepository")
	PilotoRepository pilotoRepo;

	public Piloto crearPiloto(Piloto piloto) {
		return pilotoRepo.save(piloto);

	}

	public List<Piloto> getAllPilotos() {
		return pilotoRepo.findAll();
	}

	@Override
	public Piloto updatePiloto(String alias, Piloto pilotoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Piloto> getPiloto(String alias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean borrarPiloto(String alias) {
		// TODO Auto-generated method stub
		return false;
	}

}
