package com.races.auth.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.auth.entity.TipoSesion;

@Repository("CampeonatoRepository")
public interface TipoSesionRepository extends JpaRepository<TipoSesion, Serializable>{

}
