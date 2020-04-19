package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Sesion;

public interface SesionService {

	List<Sesion> buscarSesiones(String idReglamento, String jwt, String user);

}
