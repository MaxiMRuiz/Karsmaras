package com.karsmaras.backend.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karsmaras.backend.entity.Equipo;

@Repository("EquipoRepository")
public interface EquipoRepository extends JpaRepository<Equipo, Serializable>{

	Optional<Equipo> findByAlias(String alias);

}
