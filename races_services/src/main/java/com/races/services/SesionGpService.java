package com.races.services;

import java.util.Date;
import java.util.List;

import com.races.dto.SesionGpDto;
import com.races.entity.GranPremio;
import com.races.entity.SesionGP;
import com.races.exception.RacesException;

/**
 * Interfaz de servicios para Sesion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface SesionGpService {

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
	 * Crea automaticamente las sesiones de un Gran Premio
	 * 
	 * @param newGp
	 * @param date
	 */
	void crearSesionesGranPremio(GranPremio newGp, Date date);

	/**
	 * Servicio de actualizacion de la fecha de una sesion de GP
	 * @param id 
	 * 
	 * @param sesionGpDto
	 * @return
	 */
	SesionGP updateSesionGp(Long id, SesionGpDto sesionGpDto) throws RacesException;

}
