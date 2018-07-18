package com.karts.back.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karts.back.entity.Piloto;

@Repository("PilotoRepository")
public interface PilotoRepository extends JpaRepository<Piloto, Serializable>{

	Optional<Piloto> findByAlias(String alias);

}
