package com.races.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Puntuacion;

@Repository("PuntuacionRepository")
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Serializable>{

}
