package com.races.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.races.entity.Reglamento;

@Repository("ReglamentoRepository")
public interface ReglamentoRepository extends JpaRepository<Reglamento, Serializable> {

}
