package com.races.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Sesion;

/**
 * Repositorio para la entidad Sesion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Repository("SesionRepository")
public interface SesionRepository extends JpaRepository<Sesion, Serializable>{

}
