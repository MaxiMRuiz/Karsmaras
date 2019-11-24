package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.component.RacesException;
import com.races.dto.InscripcionDto;
import com.races.entity.Campeonato;
import com.races.entity.Equipo;
import com.races.entity.Inscripcion;
import com.races.entity.Piloto;
import com.races.repository.InscripcionRepository;
import com.races.services.CampeonatoService;
import com.races.services.EquipoService;
import com.races.services.InscripcionService;
import com.races.services.PilotoService;

/**
 * Implementacion de la interfaz InscripcionService
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Service("InscripcionService")
public class InscripcionServiceImpl implements InscripcionService {

	@Autowired
	InscripcionRepository inscriptionRepo;

	@Autowired
	PilotoService pilotoService;

	@Autowired
	CampeonatoService campeonatoService;

	@Autowired
	EquipoService equipoService;

	private static final Log LOGGER = LogFactory.getLog(InscripcionServiceImpl.class);

	public Inscripcion crearInscripcion(InscripcionDto inscripcion) throws RacesException {

		if (pilotoService.existePiloto(inscripcion.getIdPiloto())
				&& equipoService.existeEquipo(inscripcion.getIdEquipo())
				&& campeonatoService.existeCampeonato(inscripcion.getIdCampeonato())) {

			Inscripcion newInscripcion = new Inscripcion(
					campeonatoService.buscarCampeonato(inscripcion.getIdCampeonato()),
					pilotoService.buscarPiloto(inscripcion.getIdPiloto()),
					equipoService.buscarEquipo(inscripcion.getIdEquipo()));

			if (inscriptionRepo.findOne(Example.of(newInscripcion)).isPresent()) {
				throw new RacesException("Inscripcion duplicada");
			}
			return inscriptionRepo.save(newInscripcion);
		} else {
			throw new RacesException("Datos de la Inscripcion no válidos");
		}
	}

	public boolean borrarInscripcion(InscripcionDto dto) throws RacesException {
		Campeonato campeonato = campeonatoService.buscarCampeonato(dto.getIdCampeonato());
		Piloto piloto = pilotoService.buscarPiloto(dto.getIdPiloto());
		Equipo equipo = equipoService.buscarEquipo(dto.getIdEquipo());
		Optional<Inscripcion> op = inscriptionRepo.findByCampeonatoAndPilotoAndEquipo(campeonato, piloto, equipo);
		if (op.isPresent()) {
			inscriptionRepo.delete(op.get());
			return true;
		} else {
			throw new RacesException("Inscripcion no encontrada");
		}
	}

	@Override
	public List<Inscripcion> buscarInscripciones(Long idCampeonato, Long idPiloto, Long idEquipo) {
		if (idCampeonato == null && idPiloto == null && idEquipo == null) {
			return inscriptionRepo.findAll();
		} else {
			try {
				Inscripcion probe = new Inscripcion(campeonatoService.buscarCampeonato(idCampeonato),
						pilotoService.buscarPiloto(idPiloto), equipoService.buscarEquipo(idEquipo));
				return inscriptionRepo.findAll(Example.of(probe));
			} catch (RacesException e) {
				LOGGER.error(e);
				return inscriptionRepo.findAll();
			}

		}
	}

	@Override
	public Inscripcion buscarInscripcion(Long idCampeonato, Long idPiloto, Long idEquipo) throws RacesException {
		Campeonato campeonato = campeonatoService.buscarCampeonato(idCampeonato);
		Piloto piloto = pilotoService.buscarPiloto(idPiloto);
		Equipo equipo = equipoService.buscarEquipo(idEquipo);
		Optional<Inscripcion> op = inscriptionRepo.findByCampeonatoAndPilotoAndEquipo(campeonato, piloto, equipo);
		if (op.isPresent()) {
			return op.get();
		} else {
			throw new RacesException("Inscripcion no encontrada");
		}
	}

	@Override
	public boolean existeInscripcion(InscripcionDto dto) {
		try {
			Campeonato campeonato = campeonatoService.buscarCampeonato(dto.getIdCampeonato());
			Piloto piloto = pilotoService.buscarPiloto(dto.getIdPiloto());
			Equipo equipo = equipoService.buscarEquipo(dto.getIdEquipo());
			return inscriptionRepo.findByCampeonatoAndPilotoAndEquipo(campeonato, piloto, equipo).isPresent();
		} catch (RacesException e) {
			LOGGER.error(e);
			return false;
		}
	}

}
