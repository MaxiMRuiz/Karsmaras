package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.dto.GranPremioDto;
import com.races.entity.GranPremio;

public interface GranPremioService {

	GranPremio crearGranPremio(GranPremioDto gpDto) throws RacesException;

	List<GranPremio> getAllGrandesPremios(Long id, String ubicacion, Long idGp);

	GranPremio getGranPremio(Long id) throws RacesException;

	boolean borrarGranPremio(Long id) throws RacesException;

	boolean existeGranPremio(Long id);

}
