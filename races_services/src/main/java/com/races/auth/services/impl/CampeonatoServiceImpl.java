package com.races.auth.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.races.auth.entity.Campeonato;
import com.races.auth.repository.CampeonatoRepository;
import com.races.auth.services.CampeonatoService;

@Service("CampeonatoService")
public class CampeonatoServiceImpl implements CampeonatoService {

	@Autowired
	@Qualifier("CampeonatoRepository")
	CampeonatoRepository campeoRepo;

	public Campeonato crearCampeonato(Campeonato campeonato) {
		return campeoRepo.save(campeonato);

	}

	public List<Campeonato> getAllCampeonatos() {
		return campeoRepo.findAll();
	}

	public List<Campeonato> getCampeonato(Integer id) {
		List<Campeonato> list = new ArrayList<>();
		Optional<Campeonato> campeonato = campeoRepo.findById(id);
		if (campeonato.isPresent()) {
			list.add(campeonato.get());
		}
		return list;
	}

	public Campeonato updateCampeonato(Integer id, Campeonato campeonato) {
		Optional<Campeonato> campOpt = campeoRepo.findById(id);
		if (campOpt.isPresent()) {
			campeonato = campeoRepo.save(campeonato);
			return campeonato;
		} else {
			return null;
		}
	}

	public boolean borrarCampeonato(Integer id) {
		Optional<Campeonato> campOpt = campeoRepo.findById(id);
		if (campOpt.isPresent()) {
			campeoRepo.delete(campOpt.get());
			return true;
		}else {
			return false;
		}
	}
	
}
