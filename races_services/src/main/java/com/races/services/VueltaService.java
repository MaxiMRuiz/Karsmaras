package com.races.services;

import java.util.List;

import com.races.dto.FileUploadDto;
import com.races.entity.Resultado;
import com.races.entity.SesionGP;
import com.races.entity.Vuelta;
import com.races.exception.RacesException;

/**
 * Interfaz de servicios para Vueltas
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface VueltaService {

	/**
	 * Servicio de busqueda de un listado de vueltas con filtros por id,
	 * idResultado, numero de vuelta y tiempo
	 * 
	 * @param id
	 * @param idResultado
	 * @param nVuelta
	 * @param tiempo
	 * @return
	 */
	List<Vuelta> buscarVueltas(Long id, Long idResultado, Integer nVuelta, Integer tiempo);

	/**
	 * Servicio de busqueda de una vuelta en base a su id
	 * 
	 * @param id
	 * @return
	 * @throws RacesException
	 */
	Vuelta buscarVuelta(Long id) throws RacesException;

	/**
	 * Servicio de comprobacion de existencia de una vuelta en base a su id
	 * 
	 * @param id
	 * @return
	 */
	boolean existeVuelta(Long id);

	/**
	 * Carga las vueltas desde un fichero
	 * 
	 * @param listLines
	 * @return
	 * @throws RacesException
	 */
	void cargarVueltas(List<FileUploadDto> listLines, SesionGP sesionGp) throws RacesException;

	/**
	 * Busqueda de la Vuelta Rapida de un piloto en una sesion
	 * 
	 * @param resultado
	 * @return
	 */
	Vuelta buscarVueltaRapida(Resultado resultado);

}
