package com.races.services;

import java.util.List;

import com.races.dto.VueltaDto;
import com.races.entity.Vuelta;

public interface VueltaService {

	Vuelta crearVuelta(VueltaDto vueltaDto);

	List<Vuelta> getAllVueltas();

	Vuelta getVuelta(Long id);

	boolean existsVuelta(Long id);
	
	boolean removeVuelta(Long id);
	
}
