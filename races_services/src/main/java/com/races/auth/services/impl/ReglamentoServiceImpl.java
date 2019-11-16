package com.races.auth.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.races.auth.entity.Reglamento;
import com.races.auth.repository.ReglamentoRepository;
import com.races.auth.services.ReglamentoService;

@Service("ReglamentoService")
public class ReglamentoServiceImpl implements ReglamentoService {

	@Autowired
	@Qualifier("ReglamentoRepository")
	ReglamentoRepository reglaRepo;

	public Reglamento crearReglamento(Reglamento reglamento) {

		return reglaRepo.save(reglamento);

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

	public Reglamento updateReglamento(Integer id, Reglamento reglamento) {
		Optional<Reglamento> regOpt = reglaRepo.findById(id);
		if (regOpt.isPresent()) {
			return reglaRepo.save(reglamento);
		} else {
			return null;
		}

	}

	public boolean borrarReglamento(Integer id) {
		Optional<Reglamento> regOpt = reglaRepo.findById(id);
		if (regOpt.isPresent()) {
			reglaRepo.delete(regOpt.get());
			return true;
		} else {
			return false;
		}
	}
}
