package com.races.repository;

import java.io.Serializable;

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

}
