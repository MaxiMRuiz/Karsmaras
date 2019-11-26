package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.dto.PilotoDto;
import com.races.entity.Piloto;
import com.races.repository.PilotoRepository;
import com.races.services.PilotoService;

/**
 * Implementacion de la interfaz PilotoService
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("PilotoService")
public class PilotoServiceImpl implements PilotoService {

	@Autowired
	@Qualifier("PilotoRepository")
	PilotoRepository pilotoRepo;

	public Piloto crearPiloto(PilotoDto pilotoDto) throws RacesException {
		Piloto piloto = new Piloto(pilotoDto);
		if (pilotoRepo.findOne(Example.of(piloto)).isPresent()) {
			throw new RacesException("Piloto duplicado");
		}
		return pilotoRepo.save(piloto);

	}

	@Override
	public Piloto actualizarPiloto(Long id, PilotoDto pilotoDto) throws RacesException {
		Optional<Piloto> optPiloto = pilotoRepo.findById(id);
		if (optPiloto.isPresent()) {
			Piloto piloto = optPiloto.get();
			piloto.setNombre(pilotoDto.getNombre());
			piloto.setApellido(pilotoDto.getApellido());
			piloto.setApodo(pilotoDto.getApodo());
			return pilotoRepo.save(piloto);
		} else {
			throw new RacesException("Piloto no encontrado");
		}
	}

	@Override
	public boolean borrarPiloto(Long id) throws RacesException {

		pilotoRepo.delete(buscarPiloto(id));
		return true;
	}

	@Override
	public Piloto buscarPiloto(Long id) throws RacesException {
		Optional<Piloto> opPiloto = pilotoRepo.findById(id);
		if (opPiloto.isPresent()) {
			return opPiloto.get();
		} else {
			throw new RacesException("Piloto no encontrado");
		}
	}

	@Override
	public List<Piloto> buscarPilotos(Long id, String nombre, String apellido, String apodo) {
		if (id == null && StringUtils.isBlank(nombre) && StringUtils.isBlank(apellido) && StringUtils.isBlank(apodo)) {
			return pilotoRepo.findAll();
		} else {
			Example<Piloto> example = Example.of(new Piloto(id, nombre, apellido, apodo));
			return pilotoRepo.findAll(example);
		}
	}

	@Override
	public boolean existePiloto(Long id) {
		return pilotoRepo.findById(id).isPresent();
	}

}