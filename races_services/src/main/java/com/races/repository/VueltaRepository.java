package com.races.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Vuelta;

/**
 * Repositorio para la entidad Vuelta
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Repository("VueltaRepository")
public interface VueltaRepository extends JpaRepository<Vuelta, Serializable> {

}
