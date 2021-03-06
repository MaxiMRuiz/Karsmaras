package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Clasificacion;

public interface ClasificacionService {

	List<Clasificacion> clasificacionGp(Long id, String jwt, String user);

	List<Clasificacion> clasificacionCampeonato(Long id, String jwt, String user);

}
