package com.races.portal.services;

import java.util.List;

import com.races.portal.dto.Password;
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
	List<Piloto> buscarPilotos(Long id, String nombre, String apellido, String apodo, String jwt, String user);

	/**
	 * Servicio de busqueda de un piloto
	 * 
	 * @param id
	 * @return
	 */
	Piloto buscarPilotos(String id, String jwt, String user);

	/**
	 * Servicio de borrado de un piloto
	 * 
	 * @param id
	 * @return
	 */
	Boolean borrarPiloto(String id, String jwt, String user);

	/**
	 * Servicio de creacion de un piloto
	 * 
	 * @param piloto
	 */
	Boolean crearPiloto(Piloto piloto);

	/**
	 * 
	 * @param piloto
	 * @param jwt
	 * @param user
	 * @return
	 */
	Piloto editarPiloto(Piloto piloto, String jwt, String user);

	/**
	 * 
	 * @param id
	 * @param jwt
	 * @param user
	 * @return
	 */
	Boolean promocionarPiloto(String id, String jwt, String user);

	/**
	 * 
	 * @param password
	 * @param jwt
	 * @param user
	 * @return
	 */
	Boolean cambiarPassword(Password password, String jwt, String user);

}
