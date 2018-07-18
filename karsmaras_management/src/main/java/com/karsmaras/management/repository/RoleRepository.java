package com.karsmaras.management.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karsmaras.management.entity.Role;

@Repository("RoleRepository")
public interface RoleRepository extends JpaRepository<Role, Serializable>{

}
