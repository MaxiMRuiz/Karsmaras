package com.races.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Resultado;

/**
 * Repositorio para la entidad Resultado
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Repository("ResultadoRepository")
public interface ResultadoRepository extends JpaRepository<Resultado, Serializable>{

	List<Resultado> findBySesionIdAndNVueltasGreaterThan(Long idSesion, int i, Sort sort);

}
