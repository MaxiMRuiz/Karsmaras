package com.karts.back.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.karts.back.component.KartsConverter;
import com.karts.back.dto.PilotoDTO;
import com.karts.back.entity.Piloto;
import com.karts.back.repository.PilotoRepository;
import com.karts.back.services.PilotoService;

@Service("PilotoService")
public class PilotoServiceImpl implements PilotoService {

	@Autowired
	@Qualifier("PilotoRepository")
	PilotoRepository pilotoRepo;

	@Autowired
	@Qualifier("KartsConverter")
	KartsConverter converter;

		public Piloto crearPiloto(PilotoDTO pilotoDto) {
		Piloto piloto = converter.dto2Piloto(pilotoDto);
		piloto = pilotoRepo.save(piloto);

		return piloto;
	}

	public List<Piloto> getPiloto(String alias) {
		List<Piloto> list = new ArrayList<>();
		Optional<Piloto> piloto = pilotoRepo.findByAlias(alias);
		if (piloto.isPresent()) {
			list.add(piloto.get());
		}
		return list;
	}

	public List<Piloto> getAllPilotos() {
		return pilotoRepo.findAll();
	}
	
	public Piloto updatePiloto(String alias, PilotoDTO pilotoDto) {
		Optional<Piloto> pilotoOpt = pilotoRepo.findByAlias(alias);
		if (pilotoOpt.isPresent()) {
			Piloto piloto = converter.dto2Piloto(pilotoDto);
			piloto = pilotoRepo.save(piloto);
			return piloto;
		} else {
			return null;
		}
	}

	public boolean borrarPiloto(String alias) {
		Optional<Piloto> pilotoOpt = pilotoRepo.findByAlias(alias);
		if (pilotoOpt.isPresent()) {
			pilotoRepo.delete(pilotoOpt.get());
			return true;
		}else {
			return false;
		}
	}

}
