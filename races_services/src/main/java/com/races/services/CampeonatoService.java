package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.dto.CampeonatoDto;
import com.races.entity.Campeonato;

/**
 * Interfaz de servicios para Campeonatos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface CampeonatoService {

	/**
	 * Servicio de creacion de un nuevo Campeonato en base a un DTO
	 * 
	 * @param campeonatoDto
	 * @return
	 * @throws RacesException
	 */
	Campeonato crearCampeonato(CampeonatoDto campeonatoDto) throws RacesException;

	/**
	 * Servicio de busqueda de campeonatos con filtros de id, nombre y temporada
	 * 
	 * @param id
	 * @param nombre
	 * @param temporada
	 * @return
	 */
	List<Campeonato> buscarCampeonatos(Long id, String nombre, String temporada);

	/**
	 * Servicio de busqueda de un campeonato por id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	Campeonato buscarCampeonato(Long id) throws RacesException;

	/**
	 * Servicio de actualizacion de un campeonato por id, con los datos a modificar
	 * indicados en el DTO
	 * 
	 * @param id
	 * @param campeonatoDto
	 * @return
	 * @throws RacesException
	 */
	Campeonato actualizarCampeonato(Long id, CampeonatoDto campeonatoDto) throws RacesException;

	/**
	 * Servicio de borrado de un campeonato
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	boolean borrarCampeonato(Long id) throws RacesException;

	/**
	 * Servicio de comprobacion de existencia de un campeonato en la BBDD
	 * 
	 * @param idCampeonato
	 * @return
	 */
	boolean existeCampeonato(Long idCampeonato);

}
