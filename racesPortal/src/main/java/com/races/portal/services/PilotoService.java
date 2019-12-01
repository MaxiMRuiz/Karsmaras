package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Piloto;

/**
 * Interfaz de servicios para pilotos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */

public interface PilotoService {

	/**
	 * Servicio de busqueda de pilotos
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param apodo
	 * @return
	 */
	List<Piloto> buscarPilotos(Long id, String nombre, String apellido, String apodo);

	/**
	 * Servicio de busqueda de un piloto
	 * 
	 * @param id
	 * @return
	 */
	Piloto buscarPilotos(String id);

	/**
	 * Servicio de borrado de un piloto
	 * 
	 * @param id
	 * @return
	 */
	Boolean borrarPiloto(String id);

	/**
	 * Servicio de creacion de un piloto
	 * 
	 * @param piloto
	 */
	Boolean crearPiloto(Piloto piloto);

}
