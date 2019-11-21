package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.constants.Constants;
import com.races.dto.CampeonatoDto;
import com.races.entity.Campeonato;
import com.races.entity.Reglamento;
import com.races.repository.CampeonatoRepository;
import com.races.services.CampeonatoService;
import com.races.services.ReglamentoService;

/**
 * Servicios para operativas sobre campeonatos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("CampeonatoService")
public class CampeonatoServiceImpl implements CampeonatoService {

	@Autowired
	CampeonatoRepository campeoRepo;

	@Autowired
	ReglamentoService reglaService;

	public Campeonato crearCampeonato(CampeonatoDto campeonato) throws RacesException {

		Reglamento reglamento = reglaService.getReglamento(campeonato.getReglamento());

		Campeonato newCampeonato = new Campeonato();
		newCampeonato.setDescripcion(campeonato.getDescripcion());
		newCampeonato.setNombre(campeonato.getNombre());
		newCampeonato.setTemporada(campeonato.getTemporada());

		newCampeonato.setReglamento(reglamento);
		if (existsCampeonato(newCampeonato)) {
			throw new RacesException("El campeonato indicado ya existe.");
		} else {
			return campeoRepo.save(newCampeonato);
		}
	}

	/**
	 * Metodo que comprueba si el campeonato ya existe en BBDD
	 * 
	 * @param newCampeonato
	 * @return
	 */
	private boolean existsCampeonato(Campeonato campeonato) {
		Example<Campeonato> probe = Example.of(campeonato);
		return campeoRepo.findOne(probe).isPresent();
	}

	public List<Campeonato> getAllCampeonatos(Long id, String nombre, String temporada) {
		if (id == null && StringUtils.isBlank(nombre) && StringUtils.isBlank(temporada)) {
			return campeoRepo.findAll();
		} else {
			Example<Campeonato> example = Example.of(new Campeonato(id, nombre, null, temporada, null));
			return campeoRepo.findAll(example);
		}
	}

	public Campeonato getCampeonato(Long id) throws RacesException {
		Optional<Campeonato> campeonato = campeoRepo.findById(id);
		if (campeonato.isPresent()) {
			return campeonato.get();
		}
		throw new RacesException(Constants.CAMPEONATO_NO_EXISTE);
	}

	public Campeonato updateCampeonato(Long id, CampeonatoDto campeonato) throws RacesException {
		Optional<Campeonato> campOpt = campeoRepo.findById(id);
		if (campOpt.isPresent()) {
			Campeonato registro = campOpt.get();
			registro.setDescripcion(campeonato.getDescripcion());
			registro.setNombre(campeonato.getNombre());
			registro.setTemporada(campeonato.getTemporada());
			return campeoRepo.save(registro);
		} else {
			throw new RacesException(Constants.CAMPEONATO_NO_EXISTE);
		}
	}

	public boolean borrarCampeonato(Long id) throws RacesException {
		Optional<Campeonato> campOpt = campeoRepo.findById(id);
		if (campOpt.isPresent()) {
			campeoRepo.delete(campOpt.get());
			return true;
		} else {
			throw new RacesException(Constants.CAMPEONATO_NO_EXISTE);
		}
	}

	public boolean existsCampeonato(Long id) {
		return campeoRepo.findById(id).isPresent();
	}

}
