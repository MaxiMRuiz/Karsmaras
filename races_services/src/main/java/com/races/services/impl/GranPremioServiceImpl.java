package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.dto.GranPremioDto;
import com.races.entity.GranPremio;
import com.races.repository.GranPremioRepository;
import com.races.services.CampeonatoService;
import com.races.services.GranPremioService;

@Service("GranPremioService")
public class GranPremioServiceImpl implements GranPremioService {

	@Autowired
	GranPremioRepository gpRepo;

	@Autowired
	CampeonatoService campeonatoService;

	@Override
	public GranPremio crearGranPremio(GranPremioDto gpDto) {
		if (campeonatoService.existsCampeonato(gpDto.getIdCampeonato())) {

			GranPremio newGp = new GranPremio();
			newGp.setUbicacion(gpDto.getUbicacion());
			newGp.setCampeonato(campeonatoService.getCampeonato(gpDto.getIdCampeonato()));

			return gpRepo.save(newGp);
		}
		return null;
	}

	@Override
	public List<GranPremio> getAllGrandesPremios(Long id, String ubicacion, Long idCampeonato) {
		if (id == null && StringUtils.isBlank(ubicacion) && idCampeonato == null) {
			return gpRepo.findAll();
		} else {
			Example<GranPremio> example = Example
					.of(new GranPremio(id, campeonatoService.getCampeonato(idCampeonato), ubicacion));
			return gpRepo.findAll(example);
		}
	}

	@Override
	public GranPremio getGranPremio(Long id) {
		Optional<GranPremio> campeonato = gpRepo.findById(id);
		if (campeonato.isPresent()) {
			return campeonato.get();
		}
		return null;
	}

	@Override
	public boolean borrarGranPremio(Long id) {
		Optional<GranPremio> gpOpt = gpRepo.findById(id);
		if (gpOpt.isPresent()) {
			gpRepo.delete(gpOpt.get());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean existeGranPremio(Long id) {
		return gpRepo.findById(id).isPresent();
	}

}
