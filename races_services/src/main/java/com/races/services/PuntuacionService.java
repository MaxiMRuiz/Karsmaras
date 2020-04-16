package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.dto.PuntuacionDto;
import com.races.entity.Puntuacion;
import com.races.entity.Sesion;

/**
 * Interfaz de servicios para Puntuacion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface PuntuacionService {

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
	List<Puntuacion> buscarPuntuaciones(Long id, Long idSesion, Integer posicion, Integer puntos);

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
	 * Servicio de comprobacion de existencia de una puntuacion
	 * 
	 * @param id
	 * @return
	 */
	boolean existePuntuacion(Long id);

	/**
	 * Busca las puntuaciones de un reglamento con mas de 0 puntos
	 * @param id
	 * @return
	 */
	List<Puntuacion> buscarPuntuacionesValidas(Long id);

	/**
	 * Crea las puntuaciones de la sesion
	 * @param sesion
	 */
	void crearPuntuacionesSesion(Sesion sesion);

}
