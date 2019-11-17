package com.races.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Sancion;

@Repository("SancionRepository")
public interface SancionRepository extends JpaRepository<Sancion, Serializable>{

}
