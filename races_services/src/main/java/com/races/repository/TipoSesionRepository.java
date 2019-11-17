package com.races.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.TipoSesion;

@Repository("TipoSesionRepository")
public interface TipoSesionRepository extends JpaRepository<TipoSesion, Serializable>{

}
