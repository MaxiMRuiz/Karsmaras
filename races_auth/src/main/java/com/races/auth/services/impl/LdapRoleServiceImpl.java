package com.races.auth.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.Name;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import com.races.auth.constants.Constants;
import com.races.auth.model.entity.LdapRole;
import com.races.auth.repository.LdapRoleRepository;
import com.races.auth.services.LdapRoleService;
import com.races.auth.services.LdapUserService;


/**
 * LdapRoleServices implementation
 */
@Service("LdapRoleService")
public class LdapRoleServiceImpl implements LdapRoleService {

	private static final Log LOGGER = LogFactory.getLog(LdapRoleServiceImpl.class);

	@Autowired
	@Qualifier("LdapRoleRepository")
	LdapRoleRepository ldapRoleRepository;

	@Autowired
	@Qualifier("LdapUserService")
	LdapUserService ldapUserService;

	@Autowired
	Environment env;

	@Autowired
	private LdapTemplate ldapTemplate;

	public LdapRole searchRole(String role) throws Exception  {
		LOGGER.info("Searching Role " + role);
		Optional<LdapRole> opt = ldapRoleRepository.findById(getRoleDn(role));
		if (opt.isPresent()) {
			LOGGER.debug("Role found.");
			return opt.get();
		} else {
			LOGGER.error("Role not found");
			throw new Exception("Role not found");
		}

	}

	public List<LdapRole> searchAllRoles() {
		List<LdapRole> list = new ArrayList<>();
		LOGGER.info("Searching All Roles.");
		Iterable<LdapRole> iterable = ldapRoleRepository.findAll();
		for (LdapRole role : iterable) {
			list.add(role);
		}
		LOGGER.info("Return All Roles.");
		return list;
	}

	public List<Name> getListUsers(String role) throws Exception  {
		List<Name> listDn = new ArrayList<>();
		LOGGER.info("Searching Role " + role);
		LdapRole ldapRole = searchRole(role);
		LOGGER.info("Get Role members");
		for (Name domainName : ldapRole.getMembers()) {
			listDn.add(domainName.getSuffix(domainName.size() - 2));
		}
		LOGGER.info("Return members");
		return listDn;
	}

	public boolean addUser2Role(String userCode, String roleCn) throws Exception  {

		LOGGER.info("Add User " + userCode + " to Role " + roleCn);
		if (ldapUserService.existsUser(userCode)) {

			Name userDn = ldapUserService.getUserDnFull(userCode);

			LdapRole role = searchRole(roleCn);
			boolean result = role.addMember(userDn);
			if (result) {
				LOGGER.info("Adding user to Role members list");
				ldapTemplate.update(role);
			} else {
				LOGGER.warn("User was already on the members list");
			}
			LOGGER.info( Boolean.toString(result));
			return result;
		} else {
			LOGGER.warn(Constants.USER_DOESNT_EXISTS);
		}
		LOGGER.debug("return FALSE");
		return false;
	}

	public boolean removeUserFromRole(String userCode, String roleCn) throws Exception  {

		LOGGER.info("Remove User " + userCode + " from Role " + roleCn);
		if (ldapUserService.existsUser(userCode)) {

			Name userDn = ldapUserService.getUserDnFull(userCode);

			LdapRole role = searchRole(roleCn);
			boolean result = role.removeMember(userDn);
			if (result) {
				LOGGER.info("Removing user from Role members list");
				ldapTemplate.update(role);
			} else {
				LOGGER.warn("User not found in the members list");
			}
			LOGGER.info(Boolean.toString(result));
			return result;
		} else {
			LOGGER.warn(Constants.USER_DOESNT_EXISTS);
		}
		LOGGER.debug("return FALSE");
		return false;
	}

	public boolean uptateUserRole(String oldRole, String newRole, String user) throws Exception  {

		LOGGER.info("Updating User role");
		boolean result = removeUserFromRole(user, oldRole);
		if (result) {
			result = addUser2Role(user, newRole);
		}
		LOGGER.info(Boolean.toString(result));
		return result;

	}

	public boolean existsRole(String role) {
		return ldapRoleRepository.findById(getRoleDn(role)).isPresent();
	}

	/**
	 * Generate a relative Dn for the role
	 * 
	 * @param role
	 * @return Name relative
	 */
	private Name getRoleDn(String role) {
		return LdapNameBuilder.newInstance(env.getProperty(Constants.LDAP_ROLE_BASE))
				.add(Constants.COMMON_NAME, role).build();
	}

}
