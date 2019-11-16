package com.races.auth.repository;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.races.auth.model.entity.LdapRole;


/**
 * Repository for Ldap Roles
 * @author Maximino Mañanes Ruiz
 */
@Repository("LdapRoleRepository")
public interface LdapRoleRepository extends LdapRepository<LdapRole>{

}
