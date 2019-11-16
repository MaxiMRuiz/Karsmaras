package com.races.auth.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.auth.entity.Sancion;

@Repository("CampeonatoRepository")
public interface SancionRepository extends JpaRepository<Sancion, Serializable>{

}
