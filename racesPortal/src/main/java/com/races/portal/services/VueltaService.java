package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Vuelta;

public interface VueltaService {

	List<Vuelta> buscarVueltas(Long idResultado, String jwt, String user);

}
