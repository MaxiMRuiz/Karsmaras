package com.races.services;

import java.util.List;

import com.races.dto.GranPremioDto;
import com.races.entity.GranPremio;


public interface GranPremioService {

	GranPremio crearGranPremio(GranPremioDto gpDto);

	List<GranPremio> getAllGrandesPremios(Long id, String ubicacion, Long idGp);

	GranPremio getGranPremio(Long id);

	boolean borrarGranPremio(Long id);
	
	boolean existeGranPremio(Long id);
		
}
