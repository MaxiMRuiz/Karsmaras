package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.dto.SancionDto;
import com.races.entity.Sancion;
import com.races.repository.SancionRepository;
import com.races.services.ResultadoService;
import com.races.services.SancionService;

/**
 * Implementacion de la interfaz SancionService
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("SancionService")
public class SancionServiceImpl implements SancionService {

	@Autowired
	SancionRepository sancionRepo;

	@Autowired
	ResultadoService resultadoService;

	private static final Log LOGGER = LogFactory.getLog(SancionServiceImpl.class);

	@Override
	public Sancion crearSancion(SancionDto sancionDto) throws RacesException {
		Sancion newSancion = new Sancion();
		newSancion.setDescripcion(sancionDto.getDescripcion());
		newSancion.setPuntos(sancionDto.getPuntos());
		newSancion.setResultado(resultadoService.buscarResultado(sancionDto.getIdResultado()));
		newSancion.setTiempo(sancionDto.getTiempo());
		if (sancionRepo.findOne(Example.of(newSancion)).isPresent()) {
			throw new RacesException("Sancion duplicada");
		}
		return sancionRepo.save(newSancion);
	}

	@Override
	public List<Sancion> buscarSanciones(Long id, Long idResultado, Integer puntos, Integer tiempo) {
		if (id == null && idResultado == null && puntos == null && tiempo == null) {
			return sancionRepo.findAll();
		} else {
			try {
				Sancion probe = new Sancion(id, resultadoService.buscarResultado(idResultado), null, puntos, tiempo);
				return sancionRepo.findAll(Example.of(probe));
			} catch (RacesException e) {
				LOGGER.error(e);
				return sancionRepo.findAll();
			}

		}

	}

	@Override
	public Sancion buscarSancion(Long id) throws RacesException {
		Optional<Sancion> opSancion = sancionRepo.findById(id);
		if (opSancion.isPresent()) {
			return opSancion.get();
		} else {
			throw new RacesException("Sancion no existe");
		}
	}

	@Override
	public boolean existeSancion(Long id) {
		return sancionRepo.findById(id).isPresent();
	}

	@Override
	public boolean borrarSancion(Long id) throws RacesException {
		sancionRepo.delete(buscarSancion(id));
		return true;
	}

}
