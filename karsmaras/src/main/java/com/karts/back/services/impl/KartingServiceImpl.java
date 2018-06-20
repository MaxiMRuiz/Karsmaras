package com.karts.back.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.karts.back.dto.ReglamentoDTO;
import com.karts.back.entity.Reglamento;
import com.karts.back.repository.ReglamentoRepository;
import com.karts.back.services.KartingService;

@Service("KartingService")
public class KartingServiceImpl implements KartingService {

	@Autowired
	@Qualifier("ReglamentoRepository")
	ReglamentoRepository reglaRepo;

	public Reglamento crearReglamento(ReglamentoDTO reglamentoDto) {

		Reglamento reglamento = new Reglamento(reglamentoDto.getPuntos1(), reglamentoDto.getPuntos2(),
				reglamentoDto.getPuntos3(), reglamentoDto.getPuntos4(), reglamentoDto.getPuntos5(), reglamentoDto.getPuntos6(),
				reglamentoDto.getPuntos7(), reglamentoDto.getPuntos8(), reglamentoDto.getPuntos9(), reglamentoDto.getPuntos10(),
				reglamentoDto.getPuntos11(), reglamentoDto.getPuntos12(), reglamentoDto.getPuntos13(), reglamentoDto.getPuntos14(),
				reglamentoDto.getPuntos15(), reglamentoDto.getPuntosPole(), reglamentoDto.getNormativa());
		reglamento = reglaRepo.save(reglamento);

		return reglamento;
	}

	public List<Reglamento> getAllReglamentos() {
		return reglaRepo.findAll();
	}

	public List<Reglamento> getReglamento(Integer id) {
		List<Reglamento> list = new ArrayList<>();
		Optional<Reglamento> reglamento = reglaRepo.findById(id);
		if(reglamento.isPresent()) {
			list.add(reglamento.get());
		}
		return list;
	}

	public Reglamento updateReglamento(Integer id, ReglamentoDTO reglamentoDto) {
		Optional<Reglamento> regOpt = reglaRepo.findById(id);
		if(regOpt.isPresent()) {
			Reglamento reglamento = regOpt.get();
			reglamento.setPuntos1(reglamentoDto.getPuntos1());
			reglamento.setPuntos2(reglamentoDto.getPuntos2());
			reglamento.setPuntos3(reglamentoDto.getPuntos3());
			reglamento.setPuntos4(reglamentoDto.getPuntos4());
			reglamento.setPuntos5(reglamentoDto.getPuntos5());
			reglamento.setPuntos6(reglamentoDto.getPuntos6());
			reglamento.setPuntos7(reglamentoDto.getPuntos7());
			reglamento.setPuntos8(reglamentoDto.getPuntos8());
			reglamento.setPuntos9(reglamentoDto.getPuntos9());
			reglamento.setPuntos10(reglamentoDto.getPuntos10());
			reglamento.setPuntos11(reglamentoDto.getPuntos11());
			reglamento.setPuntos12(reglamentoDto.getPuntos12());
			reglamento.setPuntos13(reglamentoDto.getPuntos13());
			reglamento.setPuntos14(reglamentoDto.getPuntos14());
			reglamento.setPuntos15(reglamentoDto.getPuntos15());
			reglamento.setPuntosPole(reglamentoDto.getPuntosPole());
			reglamento.setNormativaParrilla(reglamentoDto.getNormativa());
			reglamento = reglaRepo.save(reglamento);
			return reglamento;
		}
		else {
			return null;
		}
		
	}

}
