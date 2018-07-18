package com.karts.back.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.karts.back.dto.CampeonatoDTO;
import com.karts.back.dto.EquipoDTO;
import com.karts.back.dto.PilotoDTO;
import com.karts.back.dto.PuntosPosicionDTO;
import com.karts.back.dto.ReglamentoDTO;
import com.karts.back.dto.SancionesDTO;
import com.karts.back.entity.Campeonato;
import com.karts.back.entity.Equipo;
import com.karts.back.entity.Piloto;
import com.karts.back.entity.PuntosPosicion;
import com.karts.back.entity.Reglamento;
import com.karts.back.entity.Sancion;
import com.karts.back.repository.PilotoRepository;

@Component("KartsConverter")
public class KartsConverter {

	@Autowired
	@Qualifier("PilotoRepository")
	PilotoRepository pilotoRepo;

	public Reglamento dto2Reglamento(ReglamentoDTO dto) {

		Reglamento reglamento = new Reglamento();
		List<PuntosPosicion> puntosP = new ArrayList<>();
		List<Sancion> sancion = new ArrayList<>();
		if(!dto.getPuntosPosiciones().isEmpty()) {
			for(PuntosPosicionDTO posDto : dto.getPuntosPosiciones()) {
				puntosP.add(dto2PuntosPosicion(posDto));
			}
		}
		if(!dto.getSanciones().isEmpty()) {
			for(SancionesDTO sanDto : dto.getSanciones()) {
				sancion.add(dto2Sanciones(sanDto));
			}
		}
		reglamento.setSanciones(sancion);
		reglamento.setPuntosPosicion(puntosP);
		reglamento.setPuntosPole(dto.getPuntosPole());
		reglamento.setPuntosVR(dto.getPuntosVR());
		reglamento.setNormativaParrilla(dto.getNormativa());
		return reglamento;

	}

	public Sancion dto2Sanciones(SancionesDTO sanDto) {
		Sancion sancion = new Sancion();
		sancion.setDescripcion(sanDto.getDescripcion());
		sancion.setGravedad(sanDto.getGravedad());
		sancion.setPuntos(sanDto.getPuntos());
		sancion.setPosiciones(sanDto.getPosiciones());
		return sancion;
	}

	public PuntosPosicion dto2PuntosPosicion(PuntosPosicionDTO posDto) {
		PuntosPosicion puntosP = new PuntosPosicion();
		puntosP.setPosicion(posDto.getPosicion());
		puntosP.setPuntos(posDto.getPuntos());
		return puntosP;
	}

	public Campeonato dto2Campeonato(CampeonatoDTO campeonatoDto) {
		Campeonato campeonato = new Campeonato();
		campeonato.setNombreCampeonato(campeonatoDto.getNombreCampeonato());
		campeonato.setYear(campeonatoDto.getYear());
		campeonato.setIdReglamento(campeonatoDto.getReglamento());
		return campeonato;
	}

	public Piloto dto2Piloto(PilotoDTO pilotoDto) {
		Piloto piloto = new Piloto();
		piloto.setAlias(pilotoDto.getAlias());
		piloto.setNombre(pilotoDto.getNombre());
		return piloto;
	}

	public Equipo dto2Equipo(EquipoDTO equipoDto) {
		Equipo equipo = new Equipo();
		equipo.setAlias(equipoDto.getAlias());
		equipo.setDescripcion(equipoDto.getDescripcion());
		Optional<Piloto> pilotoOpt = pilotoRepo.findByAlias(equipoDto.getPiloto1());
		if (pilotoOpt.isPresent()) {
			equipo.setPiloto1(pilotoOpt.get());
		}
		pilotoOpt = pilotoRepo.findByAlias(equipoDto.getPiloto2());
		if (pilotoOpt.isPresent()) {
			equipo.setPiloto2(pilotoOpt.get());
		}
		return equipo;
	}

}
