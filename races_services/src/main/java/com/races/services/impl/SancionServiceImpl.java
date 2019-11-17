package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.races.dto.SancionDto;
import com.races.entity.Sancion;
import com.races.repository.SancionRepository;
import com.races.services.ResultadoService;
import com.races.services.SancionService;

@Service("SancionService")
public class SancionServiceImpl implements SancionService {

	@Autowired
	SancionRepository sancionRepo;

	@Autowired
	ResultadoService resultadoService;

	@Override
	public Sancion crearSancion(SancionDto sancionDto) {
		if (resultadoService.existsResultado(sancionDto.getIdResultado())) {
			Sancion newSancion = new Sancion();
			newSancion.setDescripcion(sancionDto.getDescripcion());
			newSancion.setPuntos(sancionDto.getPuntos());;
			newSancion.setResultado(resultadoService.getResultado(sancionDto.getIdResultado()));
			newSancion.setTiempo(sancionDto.getTiempo());
			return sancionRepo.save(newSancion);
		}
		return null;
	}

	@Override
	public List<Sancion> getAllSancion() {
		return sancionRepo.findAll();
	}

	@Override
	public Sancion getSancion(Long id) {
		Optional<Sancion> opSancion = sancionRepo.findById(id);
		if (opSancion.isPresent()) {
			return opSancion.get();
		}
		return null;
	}

	@Override
	public boolean existsSancion(Long id) {
		return sancionRepo.findById(id).isPresent();
	}

	@Override
	public boolean removeSancion(Long id) {
		if (existsSancion(id)) {
			sancionRepo.delete(getSancion(id));
		}
		return false;
	}

}
