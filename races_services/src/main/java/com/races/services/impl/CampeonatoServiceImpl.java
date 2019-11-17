package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.dto.CampeonatoDto;
import com.races.entity.Campeonato;
import com.races.repository.CampeonatoRepository;
import com.races.services.CampeonatoService;
import com.races.services.ReglamentoService;

@Service("CampeonatoService")
public class CampeonatoServiceImpl implements CampeonatoService {

	@Autowired
	CampeonatoRepository campeoRepo;

	@Autowired
	ReglamentoService reglaService;

	public Campeonato crearCampeonato(CampeonatoDto campeonato) {

		if (reglaService.existsReglamento(campeonato.getReglamento())) {

			Campeonato newCampeonato = new Campeonato();
			newCampeonato.setDescripcion(campeonato.getDescripcion());
			newCampeonato.setNombre(campeonato.getNombre());
			newCampeonato.setTemporada(campeonato.getTemporada());

			newCampeonato.setReglamento(reglaService.getReglamento(campeonato.getReglamento()));

			return campeoRepo.save(newCampeonato);
		}
		return null;
	}

	public List<Campeonato> getAllCampeonatos(Long id, String nombre, String temporada) {
		if (id == null && StringUtils.isBlank(nombre) && StringUtils.isBlank(temporada)) {
			return campeoRepo.findAll();
		}else {
			Example<Campeonato> example = Example.of(new Campeonato(id,nombre,null,temporada,null));
			return campeoRepo.findAll(example);
		}
	}

	public Campeonato getCampeonato(Long id) {
		Optional<Campeonato> campeonato = campeoRepo.findById(id);
		if (campeonato.isPresent()) {
			return campeonato.get();
		}
		return null;
	}

	public Campeonato updateCampeonato(Long id, CampeonatoDto campeonato) {
		Optional<Campeonato> campOpt = campeoRepo.findById(id);
		if (campOpt.isPresent()) {
			Campeonato registro = campOpt.get();
			registro.setDescripcion(campeonato.getDescripcion());
			registro.setNombre(campeonato.getNombre());
			registro.setTemporada(campeonato.getTemporada());
			return campeoRepo.save(registro);
		} else {
			return null;
		}
	}

	public boolean borrarCampeonato(Long id) {
		Optional<Campeonato> campOpt = campeoRepo.findById(id);
		if (campOpt.isPresent()) {
			campeoRepo.delete(campOpt.get());
			return true;
		} else {
			return false;
		}
	}

	public boolean existsCampeonato(Long id) {
		return campeoRepo.findById(id).isPresent();
	}

}
