package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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

	public Inscripcion crearInscripcion(InscripcionDto inscripcion) {

		if (pilotoService.existsPiloto(inscripcion.getIdPiloto())
				&& equipoService.existsEquipo(inscripcion.getIdEquipo())
				&& campeonatoService.existsCampeonato(inscripcion.getIdCampeonato())) {

			Inscripcion newInscripcion = new Inscripcion(campeonatoService.getCampeonato(inscripcion.getIdCampeonato()),
					pilotoService.getPiloto(inscripcion.getIdPiloto()),
					equipoService.getEquipo(inscripcion.getIdEquipo()));

			return inscriptionRepo.save(newInscripcion);
		}
		return null;
	}

	public boolean borrarInscripcion(InscripcionDto dto) {
		Campeonato campeonato = campeonatoService.getCampeonato(dto.getIdCampeonato());
		Piloto piloto = pilotoService.getPiloto(dto.getIdPiloto());
		Equipo equipo = equipoService.getEquipo(dto.getIdEquipo());
		Optional<Inscripcion> op = inscriptionRepo.findByCampeonatoAndPilotoAndEquipo(campeonato, piloto, equipo);
		if (op.isPresent()) {
			inscriptionRepo.delete(op.get());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Inscripcion> getAllInscripciones(Long idCampeonato, Long idPiloto, Long idEquipo) {
		if (idCampeonato == null && idPiloto == null && idEquipo == null) {
			return inscriptionRepo.findAll();
		} else {
			Inscripcion probe = new Inscripcion(campeonatoService.getCampeonato(idCampeonato),
					pilotoService.getPiloto(idPiloto), equipoService.getEquipo(idEquipo));
			return inscriptionRepo.findAll(Example.of(probe));
		}
	}

	@Override
	public Inscripcion getInscripcion(Long idCampeonato, Long idPiloto, Long idEquipo) {
		Campeonato campeonato = campeonatoService.getCampeonato(idCampeonato);
		Piloto piloto = pilotoService.getPiloto(idPiloto);
		Equipo equipo = equipoService.getEquipo(idEquipo);
		Optional<Inscripcion> op = inscriptionRepo.findByCampeonatoAndPilotoAndEquipo(campeonato, piloto, equipo);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public boolean existeInscripcion(InscripcionDto dto) {
		Campeonato campeonato = campeonatoService.getCampeonato(dto.getIdCampeonato());
		Piloto piloto = pilotoService.getPiloto(dto.getIdPiloto());
		Equipo equipo = equipoService.getEquipo(dto.getIdEquipo());
		return inscriptionRepo.findByCampeonatoAndPilotoAndEquipo(campeonato, piloto, equipo).isPresent();
	}

}
