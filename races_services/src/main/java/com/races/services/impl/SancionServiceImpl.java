package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.dto.SancionDto;
import com.races.entity.Sancion;
import com.races.exception.RacesException;
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
		newSancion = sancionRepo.save(newSancion);
		resultadoService.aplicarSancion(newSancion);
		return newSancion;
	}

	@Override
	public List<Sancion> buscarSanciones(Long id, Long idResultado, Integer puntos, Integer tiempo) {
		try {
			Sancion probe = new Sancion(id == null ? null : id,
					idResultado == null ? null : resultadoService.buscarResultado(idResultado), null,
					puntos == null ? null : puntos, tiempo == null ? null : tiempo);
			return sancionRepo.findAll(Example.of(probe));
		} catch (RacesException e) {
			LOGGER.error(e);
			return sancionRepo.findAll();
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
		Sancion sancion = buscarSancion(id);
		resultadoService.eliminarSancion(sancion);
		sancionRepo.delete(sancion);
		return true;
	}

	@Override
	public Sancion editarSancion(Long id, SancionDto sancionDto) throws RacesException {
		Sancion sancion = buscarSancion(id);
		sancion.setDescripcion(sancionDto.getDescripcion());
		sancion.setPuntos(sancionDto.getPuntos());
		resultadoService.eliminarSancion(sancion);
		sancion.setTiempo(sancionDto.getTiempo());
		sancion = sancionRepo.save(sancion);
		resultadoService.aplicarSancion(sancion);
		return sancion;
	}

}
