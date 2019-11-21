package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.dto.ReglamentoDto;
import com.races.entity.Reglamento;

public interface ReglamentoService {

	/**
	 * Método que crea un nuevo reglamento si no existe en la BBDD.
	 * 
	 * @param reglamentoDto con los campos necesarios para la creacion de un nuevo
	 *                      reglamento
	 * @return Reglamento creado
	 * @throws RacesException si ya existe el reglamento
	 */
	Reglamento crearReglamento(Reglamento reglamentoDto) throws RacesException;

	/**
	 * Método de recuperacion de todos los reglamentos de la BBDD.
	 * 
	 * @return Lista de reglamentos. Si no existe ninguno, la lista será vacía.
	 */
	List<Reglamento> getAllReglamentos();

	/**
	 * Busqueda de un reglamento por Id
	 * 
	 * @param id
	 * @return Reglamento asociado al id indicado
	 * @throws RacesException Si el id indicado no existe en BBDD
	 */
	Reglamento getReglamento(Long id) throws RacesException;

	/**
	 * Método para actualizar un reglamento
	 * 
	 * @param id
	 * @param reglamentoDto
	 * @return
	 * @throws RacesException Si el id indicado no existe en BBDD
	 */
	Reglamento updateReglamento(Long id, ReglamentoDto reglamentoDto) throws RacesException;

	/**
	 * Metodo para eliminar el reglamento con el id indicado.
	 * 
	 * @param id
	 * @return
	 * @throws RacesException Si el id indicado no existe en BBDD
	 */
	boolean borrarReglamento(Long id) throws RacesException;

	/**
	 * Metodo que devuelve si el Reglamento indicado existe en la BBDD
	 * 
	 * @param id
	 * @return
	 */
	boolean existsReglamento(Long id);

}
