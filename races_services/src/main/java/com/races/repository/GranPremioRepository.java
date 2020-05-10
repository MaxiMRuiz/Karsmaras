package com.races.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.GranPremio;

/**
 * Repositorio para la entidad Gran Premio
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Repository("GranPremioRepository")
public interface GranPremioRepository extends JpaRepository<GranPremio, Serializable>{

}
