package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.dto.EquipoDto;
import com.races.entity.Equipo;
import com.races.repository.EquipoRepository;
import com.races.services.EquipoService;

@Service("EquipoService")
public class EquipoServiceImpl implements EquipoService {

	@Autowired
	@Qualifier("EquipoRepository")
	EquipoRepository equipoRepo;

	public Equipo crearEquipo(EquipoDto equipoDto) {
		Equipo equipo = new Equipo(equipoDto);
		return equipoRepo.save(equipo);

	}

	@Override
	public Equipo updateEquipo(Long id, EquipoDto equipoDto) {
		Optional<Equipo> optEquipo = equipoRepo.findById(id);
		if (optEquipo.isPresent()) {
			Equipo equipo = optEquipo.get();
			equipo.setNombre(equipoDto.getNombre());
			equipo.setAlias(equipoDto.getAlias());
			return equipoRepo.save(equipo);
		}
		return null;
	}

	@Override
	public boolean borrarEquipo(Long id) {

		if (existsEquipo(id)) {
			equipoRepo.delete(getEquipo(id));
		}
		return false;
	}

	@Override
	public Equipo getEquipo(Long id) {
		Optional<Equipo> opEquipo = equipoRepo.findById(id);
		if (opEquipo.isPresent()) {
			return opEquipo.get();
		}
		return null;
	}

	@Override
	public List<Equipo> getAllEquipos(Long id, String nombre, String alias) {
		if (id == null && StringUtils.isBlank(nombre) && StringUtils.isBlank(alias)) {
			return equipoRepo.findAll();
		} else {
			Example<Equipo> example = Example.of(new Equipo(id, nombre, alias));
			return equipoRepo.findAll(example);
		}
	}

	@Override
	public boolean existsEquipo(Long id) {
		return (getEquipo(id) != null);
	}

}
