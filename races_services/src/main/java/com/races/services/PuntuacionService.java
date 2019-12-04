package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.dto.PuntuacionDto;
import com.races.entity.Puntuacion;
import com.races.entity.Reglamento;

/**
 * Interfaz de servicios para Puntuacion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface PuntuacionService {

	/**
	 * Servicio de creacion de una puntiacion en base a un DTO
	 * 
	 * @param puntuacionDto
	 * @return
	 * @throws RacesException
	 */
	Puntuacion crearPuntuacion(PuntuacionDto puntuacionDto) throws RacesException;

	/**
	 * Servicio de busqueda de un listado de puntuaciones con filtros por id,
	 * idReglamento, posicion, puntos e idTipoSesion
	 * 
	 * @param idReglamento
	 * @param posicion
	 * @param puntos
	 * @param idTipoSesion
	 * @return
	 */
	List<Puntuacion> buscarPuntuaciones(Long id, Long idReglamento, Integer posicion, Integer puntos,
			Long idTipoSesion);

	/**
	 * Servicio de busqueda de una puntuacion por id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	Puntuacion buscarPuntuacion(Long id) throws RacesException;

	/**
	 * Servicio de actualizacion de una puntuacion por id
	 * 
	 * @param id
	 * @param puntuacionDto
	 * @return
	 * @throws RacesException
	 */
	Puntuacion actualizarPuntuacion(Long id, PuntuacionDto puntuacionDto) throws RacesException;

	/**
	 * Servicio de borrado de una puntuacion por id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	boolean borrarPuntuacion(Long id) throws RacesException;

	/**
	 * Servicio de comprobacion de existencia de una puntuacion
	 * 
	 * @param id
	 * @return
	 */
	boolean existePuntuacion(Long id);

	/**
	 * Crea todas las puntuaciones indicadas en un reglamento de forma automatica
	 * @param reglamento
	 */
	void crearPuntuacionesReglamento(Reglamento reglamento);

}
