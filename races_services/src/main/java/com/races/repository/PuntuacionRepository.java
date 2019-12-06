package com.races.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Puntuacion;

/**
 * Repositorio para la entidad Puntuacion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Repository("PuntuacionRepository")
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Serializable>{

	/**
	 * 
	 * @param id
	 * @param i
	 * @return
	 */
	List<Puntuacion> findByReglamentoIdAndPuntosGreaterThan(Long id, int i);

}
