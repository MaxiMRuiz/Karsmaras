package com.races.auth.services;

import java.util.List;

import javax.naming.Name;

import org.springframework.ldap.InvalidAttributeValueException;

import com.races.auth.model.dto.CreateUserDto;
import com.races.auth.model.dto.ResponseUserDto;
import com.races.auth.model.dto.UpdateUserDto;
import com.races.auth.model.entity.LdapUser;


/**
 * LdapUserServices Interface
 */
public interface LdapUserService {

	/**
	 * Search a single user by his userCode
	 * 
	 * @param user
	 * @param trace
	 * @return LdapUser
	 * @throws Exception 
	 * @
	 */
	LdapUser searchUser(String user) throws Exception ;

	/**
	 * Search All users in the Ldap
	 * 
	 * @param trace
	 * 
	 * @return List of users
	 */
	List<ResponseUserDto> searchAll();

	/**
	 * Create a new user in the Ldap
	 * 
	 * @param user
	 * @param trace
	 * @return LdapUser created
	 * @throws Exception 
	 * @
	 */
	LdapUser createUser(CreateUserDto user) throws Exception ;

	/**
	 * Update user data.
	 * 
	 * @param user
	 * @param trace
	 * @return Ldap User updated
	 * @throws Exception 
	 * @
	 */
	ResponseUserDto updateUser(UpdateUserDto user) throws Exception ;

	/**
	 * Remove user from the Ldap
	 * 
	 * @param user
	 * @param trace
	 * @return true if success; false otherwise.
	 * @throws Exception 
	 * @
	 * @throws SchemaViolationException
	 */
	Boolean removeUser(String user) throws Exception;

	/**
	 * Check if indicated user/password are valid
	 * 
	 * @param user
	 * @param password
	 * @param trace
	 * @return LdapUser
	 * @throws Exception 
	 * @
	 */
	ResponseUserDto authenticate(String user, String password) throws Exception ;

	/**
	 * Check if user exists.
	 * 
	 * @param user
	 * @return true if exists, false if not exists.
	 */
	Boolean existsUser(String user);

	/**
	 * Set the user's status
	 * 
	 * @param user
	 * @param trace
	 * @return LdapUser
	 * @throws Exception 
	 * @
	 */
	ResponseUserDto setEnable(LdapUser user) throws Exception ;

	/**
	 * Update User's password
	 * 
	 * @param user
	 * @param password
	 * @param newPassword
	 * @param trace
	 * @return OK/KO
	 * @throws Exception 
	 * @
	 * @throws InvalidAttributeValueException
	 */
	String updatePassword(String user, String password, String newPassword) throws Exception ;

	/**
	 * Change role of the indicated user to the newRole
	 * 
	 * @param userCode
	 * @param newRole
	 * @param trace
	 * @return true if success; false otherwise.
	 * @throws Exception 
	 * @
	 */
	boolean updateRole(String userCode, String newRole) throws Exception ;

	/**
	 * Geneate a new temporal password and return it
	 * 
	 * @param userCode
	 * @param trace
	 * @return the temporal password for user
	 * @throws Exception 
	 * @throws InvalidAttributeValueException
	 * @
	 */
	String recoverPassword(String userCode) throws Exception;

	/**
	 * Get the relative Dn of the indicated User (omitting base path)
	 * 
	 * @param user
	 * @return Relative Dn as a Name object
	 */
	Name getUserDN(String user);

	/**
	 * Get Full Dn of the indicated User
	 * 
	 * @param user
	 * @return Full Dn as a Name Object
	 */
	Name getUserDnFull(String user);

}
