package com.races.portal.services;

import com.races.portal.dto.Sesion;

public interface SesionGpService {

	Sesion buscarSesion(Long idSesion, String jwt, String user);

}
