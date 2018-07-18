package com.karsmaras.backend.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.karsmaras.backend.component.KartsConverter;
import com.karsmaras.backend.dto.ReglamentoDTO;
import com.karsmaras.backend.entity.Reglamento;
import com.karsmaras.backend.repository.ReglamentoRepository;
import com.karsmaras.backend.services.ReglamentoService;

@Service("ReglamentoService")
public class ReglamentoServiceImpl implements ReglamentoService {

	@Autowired
	@Qualifier("ReglamentoRepository")
	ReglamentoRepository reglaRepo;

	@Autowired
	@Qualifier("KartsConverter")
	KartsConverter converter;

	public Reglamento crearReglamento(ReglamentoDTO reglamentoDto) {

		Reglamento reglamento = converter.dto2Reglamento(reglamentoDto);
		reglamento = reglaRepo.save(reglamento);

		return reglamento;
	}

	public List<Reglamento> getAllReglamentos() {
		return reglaRepo.findAll();
	}

	public List<Reglamento> getReglamento(Integer id) {
		List<Reglamento> list = new ArrayList<>();
		Optional<Reglamento> reglamento = reglaRepo.findById(id);
		if (reglamento.isPresent()) {
			list.add(reglamento.get());
		}
		return list;
	}

	public Reglamento updateReglamento(Integer id, ReglamentoDTO reglamentoDto) {
		Optional<Reglamento> regOpt = reglaRepo.findById(id);
		if (regOpt.isPresent()) {
			Reglamento reglamento = converter.dto2Reglamento(reglamentoDto);
			reglamento.setId(regOpt.get().getId());
			reglamento = reglaRepo.save(reglamento);
			return reglamento;
		} else {
			return null;
		}

	}
	
	public boolean borrarReglamento(Integer id) {
		Optional<Reglamento> regOpt = reglaRepo.findById(id);
		if (regOpt.isPresent()) {
			reglaRepo.delete(regOpt.get());
			return true;
		}else {
			return false;
		}
	}
}
