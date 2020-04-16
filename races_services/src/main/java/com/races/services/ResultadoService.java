package com.races.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.races.component.RacesException;
import com.races.dto.ResultadoDto;
import com.races.dto.ResultadoResponseDto;
import com.races.entity.Campeonato;
import com.races.entity.Inscripcion;
import com.races.entity.Resultado;
import com.races.entity.Sancion;
import com.races.entity.SesionGP;

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
	List<ResultadoResponseDto> buscarResultados(Long id, Long idPiloto, Long idSesion, Integer nVueltas,
			Integer tiempo);

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
	void crearResultados(List<SesionGP> listSesion, Campeonato campeonato);

	/**
	 * Servicio de actualizacion de un resultado
	 * 
	 * @param id
	 * @param resultadoDto
	 * @return
	 */
	Boolean actualizarResultado(Long id, ResultadoDto resultadoDto) throws RacesException;

	/**
	 * Procesado del fichero de vueltas
	 * 
	 * @param id
	 * @param file
	 */
	void processFile(Long id, MultipartFile file);

	/**
	 * Busqueda de listado de resultados
	 * 
	 * @param id
	 * @param idInscripcion
	 * @param idSesion
	 * @param nVueltas
	 * @param tiempo
	 * @return
	 */
	List<Resultado> buscarListaResultados(Long id, Long idInscripcion, Long idSesion, Integer nVueltas, Integer tiempo);

	/**
	 * Aplica sancion al resultado
	 * 
	 * @param newSancion
	 */
	void aplicarSancion(Sancion newSancion);

	/**
	 * Elimina la sancion del resultado
	 * 
	 * @param sancion
	 */
	void eliminarSancion(Sancion sancion);

	/**
	 * 
	 * @param idSesion
	 * @return
	 */
	List<ResultadoResponseDto> buscarResultadosValidos(SesionGP sesion);

	/**
	 * Crea los resultados para las inscricpciones de un campeonato
	 * @param newInscripcion
	 */
	void crearResultados(Inscripcion newInscripcion);

}
