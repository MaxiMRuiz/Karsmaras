package com.races.services;

import java.util.List;

import com.races.entity.TipoSesion;

public interface TipoSesionService {

	List<TipoSesion> getAllReglamentos();

	TipoSesion getTipoSesion(Long id);

	boolean existsTipoSesion(Long id);
	
}
