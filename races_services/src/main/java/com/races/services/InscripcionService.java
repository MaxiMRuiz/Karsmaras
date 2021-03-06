package com.races.services;

import java.util.List;

import com.races.dto.InscripcionDto;
import com.races.entity.Campeonato;
import com.races.entity.Inscripcion;
import com.races.entity.Piloto;
import com.races.exception.RacesException;

/**
 * Interfaz de servicios para Inscripciones
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface InscripcionService {

	/**
	 * Servicio de creacion de una inscripcion en base a un DTO
	 * 
	 * @param inscripcionDto
	 * @return
	 * @throws RacesException
	 */
	Inscripcion crearInscripcion(InscripcionDto inscripcionDto) throws RacesException;

	/**
	 * servicio de busqueda de inscripciones con filtros por campeonato, piloto y
	 * equipo
	 * 
	 * @param idCampeonato
	 * @param idPiloto
	 * @param idEquipo
	 * @return
	 */
	List<Inscripcion> buscarInscripciones(Long idCampeonato, Long idPiloto, Long idEquipo);

	/**
	 * Servicio de busqueda de una inscripcion por idCampeonato, idPiloto e idEquipo
	 * 
	 * @param idCampeonato
	 * @param idPiloto
	 * @param idEquipo
	 * @return
	 * @throws RacesException
	 */
	Inscripcion buscarInscripcion(Long idCampeonato, Long idPiloto, Long idEquipo) throws RacesException;

	/**
	 * Servicio de borrado de una inscripcion
	 * 
	 * @param dto
	 * @return
	 * @throws RacesException
	 */
	boolean borrarInscripcion(Long id) throws RacesException;

	/**
	 * Servicio de comprobacion de existencia de una inscripcion
	 * 
	 * @param dto
	 * @return
	 */
	boolean existeInscripcion(InscripcionDto dto);

	/**
	 * Busca los pilotos de un campeonato
	 * @param campeonato
	 * @return
	 */
	List<Piloto> buscarPilotos(Campeonato campeonato);

	/**
	 * Busca la inscripcion por id
	 * @param idInscripcion
	 * @return
	 * @throws RacesException 
	 */
	Inscripcion buscarInscripcion(Long idInscripcion) throws RacesException;

}
