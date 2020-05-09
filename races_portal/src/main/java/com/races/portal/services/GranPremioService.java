package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.GranPremio;


public interface GranPremioService {

	List<GranPremio> buscarGrandesPremios(String id, String jwt, String user);

	void crearGranPremio(String id, GranPremio gp, String jwt, String user);

	Boolean borrarGP(String id, String jwt, String user);

	GranPremio buscarGranPremio(Long id, String jwt, String user);

}
