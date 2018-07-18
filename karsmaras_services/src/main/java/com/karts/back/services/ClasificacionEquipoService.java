package com.karts.back.services;

import java.util.List;

import com.karts.back.dto.ClasificacionEquiposDTO;
import com.karts.back.entity.ClasificacionEquipos;

public interface ClasificacionEquipoService {

	ClasificacionEquipos crearClasificacionEquipos(ClasificacionEquiposDTO clasiEquiposDto);

	List<ClasificacionEquipos> getAllClasificacionEquipos();

	List<ClasificacionEquipos> getClasificacionEquipos(Integer id);

	ClasificacionEquipos updateClasificacionEquipos(Integer id, ClasificacionEquiposDTO clasiEquiposDto);

	void borrarClasificacionEquipos(Integer id);
	
}
