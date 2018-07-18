package com.karts.back.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karts.back.entity.Campeonato;

@Repository("CampeonatoRepository")
public interface CampeonatoRepository extends JpaRepository<Campeonato, Serializable>{

}
