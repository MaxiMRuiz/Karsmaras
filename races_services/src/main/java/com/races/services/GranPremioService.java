package com.races.services;

import java.util.List;

import com.races.dto.GranPremioDto;
import com.races.dto.GranPremioSesionesDto;
import com.races.entity.GranPremio;
import com.races.exception.RacesException;

/**
 * Interfaz de servicios para Grandes Premios
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface GranPremioService {

	/**
	 * Servicio de creacion de un GP en base a un DTO
	 * 
	 * @param gpDto
	 * @return
	 * @throws RacesException
	 */
	GranPremio crearGranPremio(GranPremioDto gpDto) throws RacesException;

	/**
	 * Servicio de busqueda de grandes premios con filtros por id, ubicacion y
	 * campeonato
	 * 
	 * @param id
	 * @param ubicacion
	 * @param idCampeonato
	 * @return
	 */
	List<GranPremioSesionesDto> buscarGrandesPremios(Long id, String ubicacion, Long idCampeonato);

	/**
	 * Servicio de busqueda de un gran premio por id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	GranPremio buscarGranPremio(Long id) throws RacesException;

	/**
	 * Servicio de borrado de un gran premio por id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	boolean borrarGranPremio(Long id) throws RacesException;

	/**
	 * Servicio de comprobacion de existencia de un GP por id
	 * 
	 * @param id
	 * @return
	 */
	boolean existeGranPremio(Long id);

}
