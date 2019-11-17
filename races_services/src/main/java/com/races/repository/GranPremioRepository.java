package com.races.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.GranPremio;

@Repository("GranPremioRepository")
public interface GranPremioRepository extends JpaRepository<GranPremio, Serializable>{

}
