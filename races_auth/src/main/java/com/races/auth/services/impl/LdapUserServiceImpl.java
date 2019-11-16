package com.races.auth.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.directory.api.ldap.model.password.PasswordUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import com.races.auth.constants.Constants;
import com.races.auth.model.dto.CreateUserDto;
import com.races.auth.model.dto.ResponseUserDto;
import com.races.auth.model.dto.UpdateUserDto;
import com.races.auth.model.entity.LdapUser;
import com.races.auth.repository.LdapUserRepository;
import com.races.auth.services.LdapRoleService;
import com.races.auth.services.LdapUserService;

/**
 * LdapUserServices Implementation
 */
@Service("LdapUserService")
public class LdapUserServiceImpl implements LdapUserService {

	private static final Log LOGGER = LogFactory.getLog(LdapUserServiceImpl.class);

	@Autowired
	Environment env;

	@Autowired
	@Qualifier("LdapUserRepository")
	LdapUserRepository ldapUserRepository;

	@Autowired
	@Qualifier("LdapRoleService")
	LdapRoleService ldapRoleService;

	@Autowired
	LdapTemplate ldapTemplate;

	public LdapUser searchUser(final String commonName) throws Exception {
		LOGGER.info("Searching User " + commonName);
		Optional<LdapUser> opt = ldapUserRepository.findById(getUserDN(commonName));

		if (opt.isPresent()) {
			LOGGER.info("User found.");
			return opt.get();
		} else {
			LOGGER.warn("User not found.");
			throw new Exception("User not found.");
		}
	}

	public List<ResponseUserDto> searchAll() {
		List<ResponseUserDto> list = new ArrayList<>();
		LOGGER.info("Searching All Users");

		Iterable<LdapUser> iterable = ldapUserRepository.findAll();
		for (LdapUser user : iterable) {
			list.add(createUser(user));
		}
		LOGGER.info("Return Users");
		return list;
	}

	/**
	 * Create UserDto by user
	 * 
	 * @param user
	 * @return
	 */
	private ResponseUserDto createUser(LdapUser user) {
		return new ResponseUserDto(user);
	}

	public LdapUser createUser(final CreateUserDto dto) throws Exception {

		LOGGER.info("Creating new User " + dto.getUserCode());
		if (ldapRoleService.existsRole(dto.getRole())) {
			if (existsUser(dto.getUserCode())) {
				LOGGER.warn("User already exists");
			} else {
				LdapUser user = new LdapUser(dto.getUserCode(), dto.getSurname(), dto.getFirstName(),
						dto.getPassword().getBytes(), dto.getEmail(), "TRUE", dto.getRole(),
						new JSONObject().toString());
				ldapTemplate.bind(getUserDN(user.getUserCode()), null, attributes(user));

				ldapRoleService.addUser2Role(dto.getUserCode(), dto.getRole());

			}
			LOGGER.info("Return created User " + dto.getUserCode());
			return searchUser(dto.getUserCode());
		} else {
			throw new Exception("Role doesn't exists");
		}
	}

	public ResponseUserDto updateUser(final UpdateUserDto user) throws Exception {
		LOGGER.info("Update User " + user.getUserCode());

		// Modificar para obtener los datos previos del usuario, y comprobar si no es
		// nulo dicho usuario.
		searchUser(user.getUserCode());
		List<ModificationItem> list = new ArrayList<>();
		if (user.getEmail() != null && !user.getEmail().isEmpty()) {
			LOGGER.info("Adding Email to the list of modifiable attributes.");
			Attribute attEmail = new BasicAttribute(Constants.ATT_EMAIL, user.getEmail());
			ModificationItem itemEmail = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attEmail);
			list.add(itemEmail);
		}
		if (user.getGivenName() != null && !user.getGivenName().isEmpty()) {
			LOGGER.info("Adding GivenName to the list of modifiable attributes.");
			Attribute attGivenName = new BasicAttribute(Constants.ATT_GIVENNAME, user.getGivenName());
			ModificationItem itemGivenName = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attGivenName);
			list.add(itemGivenName);
		}
		if (user.getSurname() != null && !user.getSurname().isEmpty()) {
			LOGGER.info("Adding Surname to the list of modifiable attributes.");
			Attribute attSurname = new BasicAttribute(Constants.ATT_SN, user.getSurname());
			ModificationItem itemSurname = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attSurname);
			list.add(itemSurname);
		}
		if (user.getJson() != null && !user.getJson().isEmpty()) {
			LOGGER.info("Adding Json to the list of modifiable attributes.");
			JSONObject json = new JSONObject(user.getJson());
			Attribute attJSON = new BasicAttribute("xmas-json", json.toString());
			ModificationItem itemJson = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attJSON);
			list.add(itemJson);
		}
		/**
		 * Anadir nuevo campo xmas-json, que realice un merge de los datos antiguos con
		 * los nuevos si existen
		 * 
		 * Hacer un recorrido de Clave-Valor para obtener todos los datos(Metodo
		 * utilities para headers)
		 */
		LOGGER.info("Updating User Attributes.");
		ldapTemplate.modifyAttributes(getUserDN(user.getUserCode()), list.toArray(new ModificationItem[list.size()]));
		LOGGER.info("Return Updated User " + user.getUserCode());
		return new ResponseUserDto(searchUser(user.getUserCode()));

	}

	public Boolean removeUser(final String commonName) throws Exception {

		LdapUser user = searchUser(commonName);

		boolean result = true;

		LOGGER.info("Remove User From Role " + user.getRole());
		result = ldapRoleService.removeUserFromRole(commonName, user.getRole());

		if (result) {
			LOGGER.info("Remove User " + user.getUserCode());
			ldapTemplate.unbind(getUserDN(commonName));
		}
		LOGGER.info(Boolean.toString(result));
		return result;
	}

	public ResponseUserDto authenticate(final String user, final String password) throws Exception {

		LdapUser ldapUser = searchUser(user);

		boolean result = false;

		LOGGER.info("Check Credentials for user " + user);

		if (ldapUser.getEnable().equals("TRUE")) {
			result = PasswordUtil.compareCredentials(password.getBytes(), ldapUser.getPassword());
		}
		if (result) {
			LOGGER.info("Valid Credentials: return Ok");
			return new ResponseUserDto(ldapUser);
		} else {
			throw new Exception("Invalid Credentials");
		}
	}

	public ResponseUserDto setEnable(final LdapUser user) throws Exception {

		LOGGER.info("Update State of User " + user.getUserCode());
		if (existsUser(user.getUserCode()) && (user.getEnable().equals(Constants.ENABLE_TRUE)
				|| user.getEnable().equals(Constants.ENABLE_FALSE))) {
			Attribute attEnable = new BasicAttribute(Constants.ATT_ENABLED, user.getEnable());
			ModificationItem itemEnable = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attEnable);
			LOGGER.info("Updating user status to " + user.getEnable());
			ldapTemplate.modifyAttributes(getUserDN(user.getUserCode()), new ModificationItem[] { itemEnable });
			LOGGER.info("Return Updated User");
			return new ResponseUserDto(searchUser(user.getUserCode()));
		} else {
			throw new Exception("User Not Found");
		}
	}

	public Boolean existsUser(final String user) {
		return ldapUserRepository.findById(getUserDN(user)).isPresent();
	}

	public String updatePassword(final String user, final String password, final String newPassword) throws Exception {

		try {
			LOGGER.info("Check authentication.");
			authenticate(user, password);

			LOGGER.info("Updating password.");
			ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute(Constants.ATT_USERPASS, newPassword));

			ldapTemplate.modifyAttributes(getUserDN(user), new ModificationItem[] { item });
			LOGGER.info(Constants.UPDATE_PASS_MSG);
			return Constants.UPDATE_PASS_MSG;
		} catch (NamingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception(Constants.UPDATE_PASS_ERR_MSG);
		}

	}

	public boolean updateRole(final String userCode, final String newRole) throws Exception {
		LdapUser user = searchUser(userCode);
		String oldRole = user.getRole();
		LOGGER.info("Update Role of User");
		if (ldapRoleService.existsRole(newRole)) {
			ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute(Constants.RACES_ROLE, newRole));
			LOGGER.info("Updating Role of user " + userCode + " to " + newRole);
			ldapTemplate.modifyAttributes(getUserDN(userCode), new ModificationItem[] { item });
			LOGGER.info("Updated");
			return ldapRoleService.uptateUserRole(oldRole, newRole, userCode);

		} else {
			throw new Exception("Invalid Role");
		}
	}

	public String recoverPassword(final String userCode) throws Exception {

		LOGGER.info("Generating a new Temporal password for " + userCode);
		String randomPass = randomGenerator(15);

		ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
				new BasicAttribute(Constants.ATT_USERPASS, randomPass));

		ldapTemplate.modifyAttributes(getUserDN(userCode), new ModificationItem[] { item });

		LOGGER.info("Return temporal password");
		return randomPass;
	}

	private String randomGenerator(int length) {
		char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'0' };
		StringBuilder cadena = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			cadena.append(chars[random.nextInt(chars.length) % chars.length]);
		}
		return cadena.toString();
	}

	public Name getUserDN(String user) {
		return LdapNameBuilder.newInstance(env.getProperty(Constants.LDAP_USER_BASE)).add(Constants.COMMON_NAME, user)
				.build();
	}

	public Name getUserDnFull(String user) {
		return LdapNameBuilder
				.newInstance(
						env.getProperty(Constants.LDAP_USER_BASE) + "," + env.getProperty(Constants.PARTITION_SUFFIX))
				.add(Constants.COMMON_NAME, user).build();
	}

	/**
	 * Create an Attribute object with the objectClass list of LdapUser entity.
	 * 
	 * @return Attribute Object
	 */
	private Attribute classAttr() {
		Attribute objectClass = new BasicAttribute(Constants.OBJECTCLASS);
		objectClass.add(Constants.OBJ_INETORGPERSON);
		objectClass.add(Constants.OBJ_ORG_PERSON);
		objectClass.add(Constants.OBJ_PERSON);
		objectClass.add(Constants.OBJ_TOP);
		return objectClass;
	}

	/**
	 * Generate an Attributes Object with all attributes that can be added/modified
	 * on the companies.
	 * 
	 * @param user LdapUser data
	 * @return Attributes
	 */
	private Attributes attributes(LdapUser user) {
		Attributes usersAttributes = new BasicAttributes();
		usersAttributes.put(classAttr());
		usersAttributes.put(Constants.COMMON_NAME, user.getUserCode());
		usersAttributes.put(Constants.ATT_EMAIL, user.getEmail());
		usersAttributes.put(Constants.ATT_SN, user.getSurname());
		usersAttributes.put(Constants.ATT_ENABLED, user.getEnable());
		usersAttributes.put(Constants.ATT_GIVENNAME, user.getGivenName());
		usersAttributes.put(Constants.ATT_USERPASS, user.getPassword());
		return usersAttributes;
	}

}
