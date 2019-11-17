package com.races.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.races.dto.PuntuacionDto;
import com.races.entity.Puntuacion;
import com.races.repository.PuntuacionRepository;
import com.races.services.PuntuacionService;
import com.races.services.ReglamentoService;
import com.races.services.TipoSesionService;

@Service("PuntuacionService")
public class PuntuacionServiceImpl implements PuntuacionService {

	@Autowired
	PuntuacionRepository puntuacionRepo;

	@Autowired
	ReglamentoService reglamentoService;

	@Autowired
	TipoSesionService tipoSesionService;

	@Override
	public Puntuacion crearPuntuacion(PuntuacionDto puntuacionDto) {
		if (reglamentoService.existsReglamento(puntuacionDto.getIdReglamento())
				&& tipoSesionService.existsTipoSesion(puntuacionDto.getIdTipoSesion())) {
			Puntuacion puntuacion = new Puntuacion(reglamentoService.getReglamento(puntuacionDto.getIdReglamento()),
					puntuacionDto.getPosicion(), puntuacionDto.getPuntos(),
					tipoSesionService.getTipoSesion(puntuacionDto.getIdTipoSesion()));
			return puntuacionRepo.save(puntuacion);
		}
		return null;
	}

	@Override
	public List<Puntuacion> getAllPuntuaciones(Long idReglamento, Integer posicion, Integer puntos, Long idTipoSesion) {
		if ((idReglamento == null || !reglamentoService.existsReglamento(idReglamento)) && posicion == null
				&& puntos == null && (idTipoSesion == null || !tipoSesionService.existsTipoSesion(idTipoSesion))) {
			return puntuacionRepo.findAll();
		} else {
			Example<Puntuacion> example = Example.of(new Puntuacion(reglamentoService.getReglamento(idReglamento),
					posicion, puntos, tipoSesionService.getTipoSesion(idTipoSesion)));
			return puntuacionRepo.findAll(example);
		}
	}

	@Override
	public Puntuacion getPuntuacion(Long id) {
		Optional<Puntuacion> puntuacion = puntuacionRepo.findById(id);
		if (puntuacion.isPresent()) {
			return puntuacion.get();
		}
		return null;
	}

	@Override
	public Puntuacion updatePuntuacion(Long id, PuntuacionDto puntuacionDto) {
		if (existsPuntuacion(id)) {
			Puntuacion puntuacion = getPuntuacion(id);
			puntuacion.setPosicion(puntuacionDto.getPosicion());
			puntuacion.setPuntos(puntuacionDto.getPuntos());
			return puntuacionRepo.save(puntuacion);
		}
		return null;
	}

	@Override
	public boolean borrarPuntuacion(Long id) {
		Optional<Puntuacion> puntuacion = puntuacionRepo.findById(id);
		if (puntuacion.isPresent()) {
			puntuacionRepo.delete(puntuacion.get());
			return true;
		}
		return false;
	}

	@Override
	public boolean existsPuntuacion(Long id) {
		return puntuacionRepo.findById(id).isPresent();
	}

}
