package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.entity.TipoSesion;

/**
 * Interfaz de servicios para TipoSesion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface TipoSesionService {

	/**
	 * Servicio de busqueda de Tipos de Sesiones con filtros por id y descripcion
	 * 
	 * @return
	 */
	List<TipoSesion> buscarTipoSesiones(Long id, String descripcion);

	/**
	 * Servicio de busqueda de un tipo de sesion por id
	 * 
	 * @param id
	 * @return
	 */
	TipoSesion buscarTipoSesion(Long id) throws RacesException;

	/**
	 * Servicio de comprobacion de existencia de un tipo de sesion por id
	 * 
	 * @param id
	 * @return
	 */
	boolean existeTipoSesion(Long id);

}
