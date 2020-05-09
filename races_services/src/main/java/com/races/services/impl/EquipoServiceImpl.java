package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.dto.EquipoDto;
import com.races.entity.Equipo;
import com.races.exception.RacesException;
import com.races.repository.EquipoRepository;
import com.races.services.EquipoService;

/**
 * Implementacion de la interfaz EquipoService
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("EquipoService")
public class EquipoServiceImpl implements EquipoService {

	@Autowired
	EquipoRepository equipoRepo;

	public Equipo crearEquipo(EquipoDto equipoDto) throws RacesException {

		Equipo equipo = new Equipo(equipoDto);
		if (equipoRepo.findOne(Example.of(equipo)).isPresent()) {
			throw new RacesException("El equipo ya existe");
		}
		return equipoRepo.save(equipo);
	}

	@Override
	public Equipo actualizarEquipo(Long id, EquipoDto equipoDto) throws RacesException {
		Optional<Equipo> optEquipo = equipoRepo.findById(id);
		if (optEquipo.isPresent()) {
			Equipo equipo = optEquipo.get();
			equipo.setNombre(equipoDto.getNombre());
			equipo.setAlias(equipoDto.getAlias());
			return equipoRepo.save(equipo);
		}
		throw new RacesException("El equipo no existe");
	}

	@Override
	public boolean borrarEquipo(Long id) throws RacesException {

		equipoRepo.delete(buscarEquipo(id));
		return true;
	}

	@Override
	public Equipo buscarEquipo(Long id) throws RacesException {
		Optional<Equipo> opEquipo = equipoRepo.findById(id);
		if (opEquipo.isPresent()) {
			return opEquipo.get();
		} else {
			throw new RacesException("Equipo no encontrado");
		}
	}

	@Override
	public List<Equipo> buscarEquipos(Long id, String nombre, String alias) {
		if (id == null && StringUtils.isBlank(nombre) && StringUtils.isBlank(alias)) {
			return equipoRepo.findAll();
		} else {
			Example<Equipo> example = Example.of(new Equipo(id, nombre, alias));
			return equipoRepo.findAll(example);
		}
	}

	@Override
	public boolean existeEquipo(Long id) {
		return equipoRepo.findById(id).isPresent();
	}

}
