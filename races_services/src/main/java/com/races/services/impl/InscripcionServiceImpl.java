package com.races.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
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
import com.races.services.ResultadoService;

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
	ResultadoService resultadoService;

	@Autowired
	EquipoService equipoService;

	private static final Log LOGGER = LogFactory.getLog(InscripcionServiceImpl.class);

	private static final String INSCRIPCION_NOT_FOUND = "Inscripcion no encontrada";

	public Inscripcion crearInscripcion(InscripcionDto inscripcion) throws RacesException {

		Campeonato campeonato = campeonatoService.buscarCampeonato(inscripcion.getIdCampeonato());
		Piloto piloto = pilotoService.buscarPiloto(inscripcion.getIdPiloto());
		Equipo equipo = equipoService.buscarEquipo(inscripcion.getIdEquipo());
		if (buscarInscripciones(campeonato.getId(), null, null).size() < campeonato.getReglamento().getnPilotos()) {
			Inscripcion newInscripcion = new Inscripcion(campeonato, piloto, null);

			if (inscriptionRepo.findOne(Example.of(newInscripcion)).isPresent()) {
				throw new RacesException("Inscripcion duplicada");
			}
			newInscripcion.setEquipo(equipo);
			if (buscarInscripciones(campeonato.getId(), null, equipo.getId()).isEmpty() && inscriptionRepo
					.countDisctinctEquipoByCampeonato(campeonato).equals(campeonato.getReglamento().getnEquipos())) {
				throw new RacesException("Máximo de equipos por reglamento alcanzado");
			}
			newInscripcion = inscriptionRepo.save(newInscripcion);
			resultadoService.crearResultados(newInscripcion);
			return newInscripcion;
		} else {
			throw new RacesException("Máximo de inscripciones por reglamento alcanzado");
		}
	}

	public boolean borrarInscripcion(Long id) throws RacesException {
		Optional<Inscripcion> op = inscriptionRepo.findById(id);
		if (op.isPresent()) {
			LOGGER.info("Borrando la Inscripcion : C" + op.get().getCampeonato().getId() + " - E"
					+ op.get().getEquipo().getId() + " - P" + op.get().getPiloto().getId());
			inscriptionRepo.delete(op.get());
			return true;
		} else {
			throw new RacesException(INSCRIPCION_NOT_FOUND);
		}
	}

	@Override
	public List<Inscripcion> buscarInscripciones(Long idCampeonato, Long idPiloto, Long idEquipo) {

		try {
			Inscripcion probe = new Inscripcion(
					idCampeonato == null ? null : campeonatoService.buscarCampeonato(idCampeonato),
					idPiloto == null ? null : pilotoService.buscarPiloto(idPiloto),
					idEquipo == null ? null : equipoService.buscarEquipo(idEquipo));
			return inscriptionRepo.findAll(Example.of(probe), new Sort(Sort.Direction.ASC, "equipo.id"));
		} catch (RacesException e) {
			LOGGER.error(e);
			return inscriptionRepo.findAll();
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
			throw new RacesException(INSCRIPCION_NOT_FOUND);
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

	@Override
	public List<Piloto> buscarPilotos(Campeonato campeonato) {
		List<Inscripcion> listaInscripciones = inscriptionRepo.findDistinctPilotoByCampeonato(campeonato);
		List<Piloto> listaPilotos = new ArrayList<>();
		for (Inscripcion inscripcion : listaInscripciones) {
			listaPilotos.add(inscripcion.getPiloto());
		}
		return listaPilotos;
	}

	@Override
	public Inscripcion buscarInscripcion(Long idInscripcion) throws RacesException {
		Optional<Inscripcion> op = inscriptionRepo.findById(idInscripcion);
		if (op.isPresent()) {
			return op.get();
		} else {
			throw new RacesException(INSCRIPCION_NOT_FOUND);
		}
	}

}
