package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.constants.Constants;
import com.races.dto.ReglamentoDto;
import com.races.entity.Reglamento;
import com.races.repository.ReglamentoRepository;
import com.races.services.ReglamentoService;

/**
 * Servicio para operativas sobre campeonatos.
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("ReglamentoService")
public class ReglamentoServiceImpl implements ReglamentoService {

	@Autowired
	@Qualifier("ReglamentoRepository")
	ReglamentoRepository reglaRepo;

	public Reglamento crearReglamento(@NonNull Reglamento reglamento) throws RacesException {
		if (existsReglamento(reglamento)) {
			throw new RacesException("La combinacion del reglamento ya existe.");
		}
		return reglaRepo.save(reglamento);
	}

	/**
	 * Metodo que comprueba si el reglamento indicado ya existe
	 * 
	 * @param reglamento
	 * @return
	 */
	private boolean existsReglamento(@NonNull Reglamento reglamento) {
		Example<Reglamento> probe = Example.of(reglamento);
		return reglaRepo.findOne(probe).isPresent();
	}

	public List<Reglamento> getAllReglamentos() {
		return reglaRepo.findAll();
	}

	public Reglamento getReglamento(@NonNull Long id) throws RacesException {
		Optional<Reglamento> reglamento = reglaRepo.findById(id);
		if (reglamento.isPresent()) {
			return reglamento.get();
		} else {
			throw new RacesException(Constants.REGLAMENTO_NO_EXISTE);
		}
	}

	public Reglamento updateReglamento(@NonNull Long id, @NonNull ReglamentoDto reglamento) throws RacesException {
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
			throw new RacesException(Constants.REGLAMENTO_NO_EXISTE);
		}

	}

	public boolean borrarReglamento(@NonNull Long id) throws RacesException {
		Optional<Reglamento> regOpt = reglaRepo.findById(id);
		if (regOpt.isPresent()) {
			reglaRepo.delete(regOpt.get());
			return true;
		} else {
			throw new RacesException(Constants.REGLAMENTO_NO_EXISTE);
		}
	}

	public boolean existsReglamento(@NonNull Long id) {
		return reglaRepo.findById(id).isPresent();
	}
}
