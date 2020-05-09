package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Equipo;

/**
 * Interfaz de servicios para equipos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface EquipoService {

	/**
	 * Servicio de busqueda de Equipos
	 * 
	 * @param id
	 * @param nombre
	 * @param apodo
	 * @return
	 */
	List<Equipo> buscarEquipos(Long id, String nombre, String apodo, String jwt, String user);

	/**
	 * Servicio de busqueda de un Equipo
	 * 
	 * @param id
	 * @return
	 */
	Equipo buscarEquipos(String id, String jwt, String user);

	/**
	 * Servicio de borrado de un Equipo
	 * 
	 * @param id
	 * @return
	 */
	Boolean borrarEquipo(String id, String jwt, String user);

	/**
	 * Servicio de edicion de un Equipo
	 * 
	 * @param Equipo
	 */
	Boolean editarEquipo(Equipo equipo, String jwt, String user);

	/**
	 * Servicio de creacion de un Equipo
	 * 
	 * @param Equipo
	 */
	Boolean crearEquipo(Equipo equipo, String jwt, String user);

}
