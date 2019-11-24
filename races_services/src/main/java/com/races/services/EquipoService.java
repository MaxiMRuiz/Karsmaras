package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.dto.EquipoDto;
import com.races.entity.Equipo;

/**
 * Interfaz de servicios para Equipos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface EquipoService {

	/**
	 * Servicio para la creacion de un equipo en base a un DTO
	 * 
	 * @param equipoDto
	 * @return
	 * @throws RacesException
	 */
	Equipo crearEquipo(EquipoDto equipoDto) throws RacesException;

	/**
	 * Servicio de obtencion de un listado de equipos, filtrando por id, nombre y
	 * alias
	 * 
	 * @param id
	 * @param nombre
	 * @param alias
	 * @return
	 */
	List<Equipo> buscarEquipos(Long id, String nombre, String alias);

	/**
	 * Servicio de busqueda de un equipo por id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	Equipo buscarEquipo(Long id) throws RacesException;

	/**
	 * Servicio de actualizacion de un equipo en base a su id con un DTO
	 * 
	 * @param id
	 * @param equipoDto
	 * @return
	 * @throws RacesException
	 */
	Equipo actualizarEquipo(Long id, EquipoDto equipoDto) throws RacesException;

	/**
	 * Servicio de borrado de un equipo por id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	boolean borrarEquipo(Long id) throws RacesException;

	/**
	 * Servicio de comprobacion de existencia de un equipo por id
	 * 
	 * @param id
	 * @return
	 */
	boolean existeEquipo(Long id);
}
