package com.races.auth.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.auth.entity.GranPremio;

@Repository("CampeonatoRepository")
public interface GranPremioRepository extends JpaRepository<GranPremio, Serializable>{

}
