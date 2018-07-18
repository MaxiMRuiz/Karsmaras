package com.karts.back.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karts.back.entity.Reglamento;
@Repository("ReglamentoRepository")
public interface ReglamentoRepository extends JpaRepository<Reglamento, Serializable>{
	
}
