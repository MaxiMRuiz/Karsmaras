package com.races.services;

import java.util.Date;
import java.util.List;

import com.races.component.RacesException;
import com.races.dto.SesionGpDto;
import com.races.entity.GranPremio;
import com.races.entity.SesionGP;

/**
 * Interfaz de servicios para Sesion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface SesionGpService {

	/**
	 * Servicio de creacion de una Sesion en base a un dto
	 * 
	 * @param sesionGpDto
	 * @return
	 * @throws RacesException
	 */
	SesionGP crearSesion(SesionGpDto sesionGpDto) throws RacesException;

	/**
	 * Servicio de busqueda de Sesiones con filtros por id, idGp, fecha e
	 * idTipoSesion
	 * 
	 * @return
	 */
	List<SesionGP> buscarSesiones(Long id, Long idGp, Date fecha, Long idTipoSesion);

	/**
	 * Servicio de busqueda de una sesion en base a su id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	SesionGP buscarSesion(Long id) throws RacesException;

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
