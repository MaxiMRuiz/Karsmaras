package com.races.services;

import java.util.List;

import org.jose4j.lang.JoseException;

import com.races.dto.LoginResponse;
import com.races.dto.PilotoDto;
import com.races.entity.Piloto;
import com.races.exception.RacesException;

/**
 * Interfaz de servicios para Pilotos
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface PilotoService {

	/**
	 * Servicio de creacion de un piloto en base a un DTO
	 * 
	 * @param pilotoDto
	 * @return
	 */
	Piloto crearPiloto(PilotoDto pilotoDto) throws RacesException;

	/**
	 * Servicio de busqueda de un listado de pilotos con filtros por id, nombre,
	 * apellido y apodo
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param apodo
	 * @return
	 */
	List<Piloto> buscarPilotos(Long id, String nombre, String apellido, String apodo);

	/**
	 * Servicio de busqueda de un piloto en base a su id
	 * 
	 * @param id
	 * @return
	 */
	Piloto buscarPiloto(Long id) throws RacesException;

	/**
	 * Servico de borrado de un piloto
	 * 
	 * @param id
	 * @return
	 */
	boolean borrarPiloto(Long id) throws RacesException;

	/**
	 * Servicio de comprobacion de existencia de un piloto
	 * 
	 * @param id
	 * @return
	 */
	boolean existePiloto(Long id);

	/**
	 * Busca un piloto por su alias
	 * 
	 * @param piloto
	 * @return
	 * @throws RacesException
	 */
	Piloto buscarPiloto(String apodo) throws RacesException;

	/**
	 * Valia un usuario y retorna un jwt
	 * 
	 * @param alias
	 * @param password
	 * @return
	 * @throws JoseException 
	 */
	LoginResponse authenticarUsuario(String alias, char[] password) throws RacesException, JoseException;

	/**
	 * 
	 * @param id
	 * @param pilotoDto
	 * @return
	 * @throws RacesException 
	 */
	Piloto editarPiloto(Long id, PilotoDto pilotoDto) throws RacesException;

	/**
	 * 
	 * @param id
	 * @param admin
	 * @return
	 * @throws RacesException
	 */
	Piloto setPilotoAdmin(Long id, Boolean admin) throws RacesException;

	/**
	 * 
	 * @param alias
	 * @param password
	 * @param newPassword
	 * @return
	 * @throws Exception 
	 */
	Boolean changePassword(String alias, char[] password, char[] newPassword) throws JoseException, RacesException;

}
