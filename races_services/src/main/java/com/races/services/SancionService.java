package com.races.services;

import java.util.List;

import com.races.dto.SancionDto;
import com.races.entity.Sancion;
import com.races.exception.RacesException;

/**
 * Interfaz de servicios para Sancion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface SancionService {

	/**
	 * Servicio de creacion de una nueva sancion en base a un DTO
	 * 
	 * @param sancionDto
	 * @return
	 * @throws RacesException
	 */
	Sancion crearSancion(SancionDto sancionDto) throws RacesException;

	/**
	 * Servicio de busqueda de sanciones con filtros por id, idResultado, puntos y
	 * tiempo
	 * 
	 * @param id
	 * @param idResultado
	 * @param puntos
	 * @param tiempo
	 * @return
	 */
	List<Sancion> buscarSanciones(Long id, Long idResultado, Integer puntos, Integer tiempo);

	/**
	 * Servicio de busqueda de una sancion en base a su id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	Sancion buscarSancion(Long id) throws RacesException;

	/**
	 * Servicio de borrado de una sancion en base a su id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	boolean borrarSancion(Long id) throws RacesException;

	/**
	 * Servicio de comprobacion de existencia de una sancion
	 * 
	 * @param id
	 * @return
	 */
	boolean existeSancion(Long id);

	/**
	 * Modificacion de una sancion
	 * @param id
	 * @param sancionDto
	 * @return
	 */
	Sancion editarSancion(Long id, SancionDto sancionDto) throws RacesException;

}
