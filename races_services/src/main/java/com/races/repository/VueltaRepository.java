package com.races.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Resultado;
import com.races.entity.Vuelta;

/**
 * Repositorio para la entidad Vuelta
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Repository("VueltaRepository")
public interface VueltaRepository extends JpaRepository<Vuelta, Serializable> {

	/**
	 * 
	 * @param resultado
	 * @return
	 */
	List<Vuelta> findByResultadoOrderByTiempoAsc(Resultado resultado);

}
