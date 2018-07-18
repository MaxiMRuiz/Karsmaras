package com.karts.back.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.karts.back.component.KartsConverter;
import com.karts.back.dto.ClasificacionPilotosDTO;
import com.karts.back.entity.ClasificacionPilotos;
import com.karts.back.services.ClasificacionPilotoService;

@Service("ClasificacionPilotoService")
public class ClasificacionPilotoServiceImpl implements ClasificacionPilotoService {

	@Autowired
	@Qualifier("KartsConverter")
	KartsConverter converter;

	public ClasificacionPilotos crearClasificacionPilotos(ClasificacionPilotosDTO clasiPilotosDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ClasificacionPilotos> getAllClasificacionPilotos() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ClasificacionPilotos> getClasificacionPilotos(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ClasificacionPilotos updateClasificacionPilotos(Integer id, ClasificacionPilotosDTO equipoDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void borrarClasificacionPilotos(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
