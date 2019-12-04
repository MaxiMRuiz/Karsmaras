package com.races.services;

import java.util.List;

import com.races.component.RacesException;
import com.races.dto.ReglamentoDto;
import com.races.entity.Reglamento;

/**
 * Interfaz de servicios para Reglamento
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
public interface ReglamentoService {

	/**
	 * Método que crea un nuevo reglamento si no existe en la BBDD.
	 * 
	 * @param reglamentoDto con los campos necesarios para la creacion de un nuevo
	 *                      reglamento
	 * @return Reglamento creado
	 * @throws RacesException si ya existe el reglamento
	 */
	Reglamento crearReglamento(ReglamentoDto reglamentoDto) throws RacesException;

	/**
	 * Método de recuperacion de todos los reglamentos de la BBDD.
	 * 
	 * @param id
	 * @param nEntrenamientos
	 * @param nClasificaciones
	 * @param nCarreras
	 * @param nPilotos
	 * @param nEquipos
	 * @return Lista de reglamentos. Si no existe ninguno, la lista será vacía.
	 */
	List<Reglamento> buscarReglamentos(Long id, String descripcion, Integer nEntrenamientos, Integer nClasificaciones, Integer nCarreras,
			Integer nPilotos, Integer nEquipos);

	/**
	 * Busqueda de un reglamento por Id
	 * 
	 * @param id
	 * @return Reglamento asociado al id indicado
	 * @throws RacesException Si el id indicado no existe en BBDD
	 */
	Reglamento buscarReglamento(Long id) throws RacesException;

	/**
	 * Método para actualizar un reglamento
	 * 
	 * @param id
	 * @param reglamentoDto
	 * @return
	 * @throws RacesException Si el id indicado no existe en BBDD
	 */
	Reglamento actualizarReglamento(Long id, ReglamentoDto reglamentoDto) throws RacesException;

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
	boolean existeReglamento(Long id);

}
