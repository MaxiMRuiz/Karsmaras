package com.races.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Piloto;

/**
 * Repositorio para la entidad Piloto
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Repository("PilotoRepository")
public interface PilotoRepository extends JpaRepository<Piloto, Serializable>{

	/**
	 * Busca un piloto por su apodo
	 * @param apodo
	 * @return
	 */
	Optional<Piloto> findByApodo(String apodo);

}
