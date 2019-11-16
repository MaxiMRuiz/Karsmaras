package com.races.auth.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.races.auth.entity.Equipo;
import com.races.auth.repository.EquipoRepository;
import com.races.auth.services.EquipoService;

@Service("EquipoService")
public class EquipoServiceImpl implements EquipoService {

	@Autowired
	@Qualifier("EquipoRepository")
	EquipoRepository equipoRepo;

	public Equipo crearEquipo(Equipo equipo) {
		return equipoRepo.save(equipo);
	}

	public List<Equipo> getAllEquipos() {
		return equipoRepo.findAll();
	}

	@Override
	public List<Equipo> getEquipo(String alias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Equipo updateEquipo(String alias, Equipo equipoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean borrarEquipo(String alias) {
		// TODO Auto-generated method stub
		return false;
	}

}
