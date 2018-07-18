package com.karsmaras.backend.services;

import java.util.List;

import com.karsmaras.backend.dto.ClasificacionEquiposDTO;
import com.karsmaras.backend.entity.ClasificacionEquipos;

public interface ClasificacionEquipoService {

	ClasificacionEquipos crearClasificacionEquipos(ClasificacionEquiposDTO clasiEquiposDto);

	List<ClasificacionEquipos> getAllClasificacionEquipos();

	List<ClasificacionEquipos> getClasificacionEquipos(Integer id);

	ClasificacionEquipos updateClasificacionEquipos(Integer id, ClasificacionEquiposDTO clasiEquiposDto);

	void borrarClasificacionEquipos(Integer id);
	
}
