package com.races.services;

import java.util.Date;
import java.util.List;

import com.races.component.RacesException;
import com.races.dto.SesionDto;
import com.races.entity.GranPremio;
import com.races.entity.Sesion;

/**
 * Interfaz de servicios para Sesion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface SesionService {

	/**
	 * Servicio de creacion de una Sesion en base a un dto
	 * 
	 * @param sesionDto
	 * @return
	 * @throws RacesException
	 */
	Sesion crearSesion(SesionDto sesionDto) throws RacesException;

	/**
	 * Servicio de busqueda de Sesiones con filtros por id, idGp, fecha e
	 * idTipoSesion
	 * 
	 * @return
	 */
	List<Sesion> buscarSesiones(Long id, Long idGp, Date fecha, Long idTipoSesion);

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
	 * Servicio de borrado de una Sesion en base a su id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	boolean borrarSesion(Long id) throws RacesException;

	/**
	 * Crea automaticamente las sesiones de un Gran Premio
	 * @param newGp
	 * @param date 
	 */
	void crearSesionesGranPremio(GranPremio newGp, Date date);

}
