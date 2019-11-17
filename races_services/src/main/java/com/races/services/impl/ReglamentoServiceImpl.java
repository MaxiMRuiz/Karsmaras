package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.races.dto.ReglamentoDTO;
import com.races.entity.Reglamento;
import com.races.repository.ReglamentoRepository;
import com.races.services.ReglamentoService;

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

	public Reglamento getReglamento(Long id) {
		Optional<Reglamento> reglamento = reglaRepo.findById(id);
		if (reglamento.isPresent()) {
			return reglamento.get();
		}
		return null;
	}

	public Reglamento updateReglamento(Long id, ReglamentoDTO reglamento) {
		Optional<Reglamento> regOpt = reglaRepo.findById(id);
		if (regOpt.isPresent()) {
			Reglamento registro = regOpt.get();
			registro.setnCarreras(reglamento.getnCarreras());
			registro.setnClasificaciones(reglamento.getnClasificaciones());
			registro.setnEntrenamientos(reglamento.getnEntrenamientos());
			registro.setnEquipos(reglamento.getnEquipos());
			registro.setnPilotos(reglamento.getnPilotos());
			return reglaRepo.save(registro);
		} else {
			return null;
		}

	}

	public boolean borrarReglamento(Long id) {
		Optional<Reglamento> regOpt = reglaRepo.findById(id);
		if (regOpt.isPresent()) {
			reglaRepo.delete(regOpt.get());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean existsReglamento(Long id) {
		return reglaRepo.findById(id).isPresent();
	}
}
