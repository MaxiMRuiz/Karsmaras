package com.races.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Campeonato;
import com.races.entity.Equipo;
import com.races.entity.Inscripcion;
import com.races.entity.Piloto;

@Repository("InscripcionRepository")
public interface InscripcionRepository extends JpaRepository<Inscripcion, Serializable>{

	Optional<Inscripcion> findByCampeonatoAndPilotoAndEquipo(Campeonato campeonato, Piloto piloto, Equipo equipo);

}
