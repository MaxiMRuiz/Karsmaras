package com.races.auth.services;

import java.util.List;

import javax.naming.Name;

import com.races.auth.model.entity.LdapRole;

/**
 * LdapRoleServices Interface
 */
public interface LdapRoleService {

	/**
	 * Search a single role by role name
	 * 
	 * @param role
	 * @param trace
	 * @return Ldap Role
	 * @throws Exception 
	 * @throws ShivaException
	 */
	LdapRole searchRole(String role) throws Exception;

	/**
	 * Search all roles in the Ldap
	 * @param trace 
	 * 
	 * @return List of Roles
	 */
	List<LdapRole> searchAllRoles();

	/**
	 * Get list of members for the indicated role
	 * 
	 * @param role
	 * @param trace
	 * @return List of User's names
	 * @throws Exception 
	 * @throws ShivaException
	 */
	List<Name> getListUsers(String role) throws Exception;

	/**
	 * Add a new member to the indicated role
	 * 
	 * @param user
	 * @param role
	 * @param trace
	 * @return true if success; false otherwise
	 * @throws Exception 
	 * @throws ShivaException
	 */
	boolean addUser2Role(String user, String role) throws Exception;

	/**
	 * Remove a member for the indicated role
	 * 
	 * @param user
	 * @param role
	 * @param trace
	 * @return true if success; false otherwise
	 * @throws Exception 
	 * @throws ShivaException
	 */
	boolean removeUserFromRole(String user, String role) throws Exception;

	/**
	 * Change role of the indicated user
	 * 
	 * @param oldRole
	 * @param newRole
	 * @param user
	 * @param trace
	 * @return true if success; false otherwise
	 * @throws Exception 
	 * @throws ShivaException
	 */
	boolean uptateUserRole(String oldRole, String newRole, String user) throws Exception;

	/**
	 * Check if indicated role exists in the Ldap.
	 * 
	 * @param role
	 * @return true if exists or false if not exists.
	 */
	boolean existsRole(String role);

}
