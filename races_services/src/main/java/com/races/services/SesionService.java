package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.entity.Reglamento;
import com.races.entity.Sesion;

/**
 * Interfaz de servicios para Sesion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface SesionService {

	/**
	 * Servicio de busqueda de Sesiones con filtros por id, idGp, fecha e
	 * idTipoSesion
	 * 
	 * @return
	 */
	List<Sesion> buscarSesiones(Long id, Long idReglamento, String descripcion, Long idTipoSesion);

	/**
	 * Servicio de busqueda de una sesion en base a su id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	Sesion buscarSesion(Long id) throws RacesException;

	/**
	 * Servicio de comprobacion de existencia de una sesion en base a su id
	 * 
	 * @param id
	 * @return
	 */
	boolean existeSesion(Long id);

	/**
	 * Crea las sesiones del reglamento
	 * @param reglamento
	 * @throws RacesException 
	 */
	void crearSesionesReglamento(Reglamento reglamento) throws RacesException;

}
