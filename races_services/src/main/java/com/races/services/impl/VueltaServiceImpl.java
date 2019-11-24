package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.dto.VueltaDto;
import com.races.entity.Vuelta;
import com.races.repository.VueltaRepository;
import com.races.services.ResultadoService;
import com.races.services.VueltaService;

/**
 * Implementacion de la interfaz VueltaSevice
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("VueltaService")
public class VueltaServiceImpl implements VueltaService {

	@Autowired
	VueltaRepository vueltaRepo;

	@Autowired
	ResultadoService resultadoService;

	private static final Log LOGGER = LogFactory.getLog(VueltaServiceImpl.class);

	@Override
	public Vuelta crearVuelta(VueltaDto vueltaDto) throws RacesException {

		Vuelta newVuelta = new Vuelta();
		newVuelta.setnVuelta(vueltaDto.getnVuelta());
		newVuelta.setTiempo(vueltaDto.getTiempo());
		newVuelta.setResultado(resultadoService.buscarResultado(vueltaDto.getIdResultado()));
		if (vueltaRepo.findOne(Example.of(newVuelta)).isPresent()) {
			throw new RacesException("Vuelta duplicada");
		}
		return vueltaRepo.save(newVuelta);
	}

	@Override
	public List<Vuelta> buscarVueltas(Long id, Long idResultado, Integer nVuelta, Integer tiempo) {
		if (id == null && idResultado == null && nVuelta == null && tiempo == null) {
			return vueltaRepo.findAll();
		} else {
			try {
				return vueltaRepo.findAll(
						Example.of(new Vuelta(id, tiempo, nVuelta, resultadoService.buscarResultado(idResultado))));
			} catch (RacesException e) {
				LOGGER.error(e);
				return vueltaRepo.findAll();
			}
		}
	}

	@Override
	public Vuelta buscarVuelta(Long id) throws RacesException {
		Optional<Vuelta> opVuelta = vueltaRepo.findById(id);
		if (opVuelta.isPresent()) {
			return opVuelta.get();
		} else {
			throw new RacesException("Vuelta no encontrada");
		}
	}

	@Override
	public boolean existeVuelta(Long id) {
		return vueltaRepo.findById(id).isPresent();
	}

	@Override
	public boolean borrarVuelta(Long id) throws RacesException {
		vueltaRepo.delete(buscarVuelta(id));
		return true;
	}

}
