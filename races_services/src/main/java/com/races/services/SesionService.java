package com.races.services;

import java.util.List;

import com.races.dto.SesionDto;
import com.races.entity.Sesion;

public interface SesionService {

	Sesion crearSesion(SesionDto sesionDto);

	List<Sesion> getAllSesion();

	Sesion getSesion(Long id);

	boolean existsSesion(Long id);
	
	boolean removeSesion(Long id);
	
}
