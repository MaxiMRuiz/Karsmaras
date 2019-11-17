package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.races.dto.ResultadoDto;
import com.races.entity.Resultado;
import com.races.repository.ResultadoRepository;
import com.races.services.PilotoService;
import com.races.services.ResultadoService;
import com.races.services.SesionService;

@Service("ResultadoService")
public class ResultadoServiceImpl implements ResultadoService {

	@Autowired
	ResultadoRepository resultadoRepo;

	@Autowired
	PilotoService pilotoService;

	@Autowired
	SesionService sesionService;

	@Override
	public Resultado crearResultado(ResultadoDto resultadoDto) {
		if (pilotoService.existsPiloto(resultadoDto.getIdPiloto())
				&& sesionService.existsSesion(resultadoDto.getIdSesion())) {
			Resultado newResultado = new Resultado();
			newResultado.setnVueltas(resultadoDto.getnVueltas());
			newResultado.setPiloto(pilotoService.getPiloto(resultadoDto.getIdPiloto()));
			newResultado.setPosicion(resultadoDto.getPosicion());
			newResultado.setSesion(sesionService.getSesion(resultadoDto.getIdSesion()));
			newResultado.setTiempo(resultadoDto.getTiempo());
			return resultadoRepo.save(newResultado);
		}
		return null;
	}

	@Override
	public List<Resultado> getAllResultado() {
		return resultadoRepo.findAll();
	}

	@Override
	public Resultado getResultado(Long id) {
		Optional<Resultado> op = resultadoRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public boolean existsResultado(Long id) {
		return resultadoRepo.findById(id).isPresent();
	}

	@Override
	public boolean removeResultado(Long id) {
		Optional<Resultado> op = resultadoRepo.findById(id);
		if (op.isPresent()) {
			resultadoRepo.delete(op.get());
			return true;
		}
		return false;
	}

}
