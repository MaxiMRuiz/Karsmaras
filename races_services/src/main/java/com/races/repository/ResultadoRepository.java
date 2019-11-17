package com.races.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Resultado;

@Repository("ResultadoRepository")
public interface ResultadoRepository extends JpaRepository<Resultado, Serializable>{

}
