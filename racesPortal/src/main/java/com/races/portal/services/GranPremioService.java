package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.GranPremio;


public interface GranPremioService {

	List<GranPremio> buscarGrandesPremios(String id);

	void crearGranPremio(String id, GranPremio gp);

	Boolean borrarGP(String id);

	GranPremio buscarGranPremio(Long id);

}
