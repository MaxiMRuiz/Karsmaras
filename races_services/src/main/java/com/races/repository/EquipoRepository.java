package com.races.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Equipo;

/**
 * Repositorio para la entidad Equipo
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Repository("EquipoRepository")
public interface EquipoRepository extends JpaRepository<Equipo, Serializable>{

}
