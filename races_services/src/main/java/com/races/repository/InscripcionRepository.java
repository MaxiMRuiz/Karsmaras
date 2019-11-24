package com.races.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Campeonato;
import com.races.entity.Equipo;
import com.races.entity.Inscripcion;
import com.races.entity.Piloto;

/**
 * Repositorio para la entidad Inscripcion
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Repository("InscripcionRepository")
public interface InscripcionRepository extends JpaRepository<Inscripcion, Serializable> {

	/**
	 * Busqueda de Inscripcion por campeonato, piloto y equipo
	 * 
	 * @param campeonato
	 * @param piloto
	 * @param equipo
	 * @return
	 */
	Optional<Inscripcion> findByCampeonatoAndPilotoAndEquipo(Campeonato campeonato, Piloto piloto, Equipo equipo);

}
