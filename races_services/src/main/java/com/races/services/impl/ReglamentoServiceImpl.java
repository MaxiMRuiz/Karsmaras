package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.constants.Constants;
import com.races.dto.ReglamentoDto;
import com.races.entity.Reglamento;
import com.races.repository.ReglamentoRepository;
import com.races.services.PuntuacionService;
import com.races.services.ReglamentoService;

/**
 * Servicio para operativas sobre reglamentos.
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("ReglamentoService")
public class ReglamentoServiceImpl implements ReglamentoService {

	@Autowired
	ReglamentoRepository reglaRepo;
	
	@Autowired
	PuntuacionService puntuaciones;

	public Reglamento crearReglamento(@NonNull ReglamentoDto reglamentoDto) throws RacesException {
		Reglamento reglamento = new Reglamento();
		reglamento.setDescripcion(reglamentoDto.getDescripcion());
		reglamento.setnCarreras(reglamentoDto.getnCarreras());
		reglamento.setnClasificaciones(reglamentoDto.getnClasificaciones());
		reglamento.setnEntrenamientos(reglamentoDto.getnEntrenamientos());
		reglamento.setnEquipos(reglamentoDto.getnEquipos());
		reglamento.setnPilotos(reglamentoDto.getnPilotos());
		if (reglaRepo.findOne(Example.of(reglamento)).isPresent()) {
			throw new RacesException("La combinacion del reglamento ya existe.");
		}
		reglamento = reglaRepo.save(reglamento);
		puntuaciones.crearPuntuacionesReglamento(reglamento);
		return reglamento;
	}

	public List<Reglamento> buscarReglamentos(Long id, String descripcion, Integer nEntrenamientos, Integer nClasificaciones,
			Integer nCarreras, Integer nPilotos, Integer nEquipos) {
		return reglaRepo.findAll(
				Example.of(new Reglamento(id, descripcion, nEntrenamientos, nClasificaciones, nCarreras, nPilotos, nEquipos)));
	}

	public Reglamento buscarReglamento(@NonNull Long id) throws RacesException {
		Optional<Reglamento> reglamento = reglaRepo.findById(id);
		if (reglamento.isPresent()) {
			return reglamento.get();
		} else {
			throw new RacesException(Constants.REGLAMENTO_NO_EXISTE);
		}
	}

	public Reglamento actualizarReglamento(@NonNull Long id, @NonNull ReglamentoDto reglamento) throws RacesException {
		Reglamento registro = buscarReglamento(id);
		registro.setDescripcion(reglamento.getDescripcion());
		registro.setnCarreras(reglamento.getnCarreras());
		registro.setnClasificaciones(reglamento.getnClasificaciones());
		registro.setnEntrenamientos(reglamento.getnEntrenamientos());
		registro.setnEquipos(reglamento.getnEquipos());
		registro.setnPilotos(reglamento.getnPilotos());
		return reglaRepo.save(registro);

	}

	public boolean borrarReglamento(@NonNull Long id) throws RacesException {
		reglaRepo.delete(buscarReglamento(id));
		return true;
	}

	public boolean existeReglamento(@NonNull Long id) {
		return reglaRepo.findById(id).isPresent();
	}

}
