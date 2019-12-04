package com.races.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.races.component.RacesException;
import com.races.dto.ResultadoDto;
import com.races.entity.Campeonato;
import com.races.entity.Resultado;
import com.races.entity.Sesion;

/**
 * Interfaz de servicios para Resultado
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface ResultadoService {

	/**
	 * Servicio de creacion de un resultado en base a un dto
	 * 
	 * @param resultadoDto
	 * @return
	 * @throws RacesException
	 */
	Resultado crearResultado(ResultadoDto resultadoDto) throws RacesException;

	/**
	 * Servicio de busqueda de un listado de resultados con filtros por id,
	 * idPiloto, idSesion, numero de vueltas y tiempo
	 * 
	 * @param id
	 * @param idPiloto
	 * @param idSesion
	 * @param nVueltas
	 * @param tiempo
	 * @return
	 */
	List<Resultado> buscarResultados(Long id, Long idPiloto, Long idSesion, Integer nVueltas, Integer tiempo);

	/**
	 * Servicio de busqueda de un resultado en base a su id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	Resultado buscarResultado(Long id) throws RacesException;

	/**
	 * Servicio de borrado de un resultado por id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	boolean borrarResultado(Long id) throws RacesException;

	/**
	 * Servicio de comprobacion de existencia de un resultado por id
	 * 
	 * @param id
	 * @return
	 */
	boolean existeResultado(Long id);

	/**
	 * Crea los resultados precargados de cada gran premio creado
	 * 
	 * @param listSesion
	 * @param campeonato
	 */
	void crearResultados(List<Sesion> listSesion, Campeonato campeonato);

	/**
	 * Servicio de actualizacion de un resultado
	 * 
	 * @param id
	 * @param resultadoDto
	 * @return
	 */
	Boolean actualizarResultado(Long id, ResultadoDto resultadoDto) throws RacesException;

	void processFile(Long id, MultipartFile file);

}
