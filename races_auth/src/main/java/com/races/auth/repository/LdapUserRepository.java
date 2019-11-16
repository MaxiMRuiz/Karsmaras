package com.races.auth.repository;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.races.auth.model.entity.LdapUser;


/**
 * Repository for LDAP users
 * @author Maximino Mañanes Ruiz
 */
@Repository("LdapUserRepository")
public interface LdapUserRepository extends LdapRepository<LdapUser>{
}
