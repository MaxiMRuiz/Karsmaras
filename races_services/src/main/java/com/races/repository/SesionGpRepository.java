package com.races.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.SesionGP;

/**
 * Repositorio para la entidad Sesion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Repository("SesionGpRepository")
public interface SesionGpRepository extends JpaRepository<SesionGP, Serializable>{

}
