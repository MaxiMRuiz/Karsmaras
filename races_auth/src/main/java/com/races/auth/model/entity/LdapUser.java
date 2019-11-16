package com.races.auth.model.entity;

import java.util.Arrays;
import java.util.Locale;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Attribute.Type;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entry for a Ldap Users
 * 
 * @author Maximino Mañanes Ruiz
 */
@Entry(base = "ou=Users", objectClasses = { "inetOrgPerson", "organizationalPerson", "person", "top", "xmas-user" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class LdapUser {

	@Id
	@JsonIgnore
	private Name id;

	@Attribute(name = "cn")
	private String userCode;

	@Attribute(name = "sn")
	private String surname;

	@Attribute(name = "givenName")
	private String givenName;

	@JsonIgnore
	@Attribute(name = "userPassword", type = Type.BINARY)
	private byte[] password;

	@Attribute(name = "email")
	private String email;

	@Attribute(name = "xmas-enabled")
	private String enable;

	@Attribute(name = "xmas-role")
	private String role;

	@Attribute(name = "xmas-json", type = Type.STRING)
	private String json;

	/**
	 * Default constructor
	 */
	public LdapUser() {
		super();
	}

	/**
	 * Constructor using fields
	 * 
	 * @param userCode
	 * @param surname
	 * @param givenName
	 * @param password
	 * @param email
	 * @param enable
	 * @param role
	 * @param json
	 */
	public LdapUser(String userCode, String surname, String givenName, byte[] password, String email, String enable,
			String role, String json) {
		super();
		this.userCode = userCode;
		this.surname = surname;
		this.givenName = givenName;
		this.password = password.clone();
		this.email = email;
		this.enable = enable;
		this.role = role;
		this.json = json;
	}

	/**
	 * @return the user
	 */
	public String getUserCode() {

		return userCode;
	}

	/**
	 * @param userCode the user to set
	 */
	public void setUserCode(String userCode) {

		this.userCode = userCode.toUpperCase(Locale.getDefault());
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {

		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {

		this.surname = surname.toUpperCase(Locale.getDefault());
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {

		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {

		this.givenName = givenName.toUpperCase(Locale.getDefault());
	}

	/**
	 * @return the password
	 */
	public byte[] getPassword() {

		return password.clone();
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(byte[] password) {

		this.password = password.clone();
	}

	/**
	 * @return the email
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {

		this.email = email.toUpperCase(Locale.getDefault());
	}

	/**
	 * @return the status
	 */
	public String getEnable() {

		return enable;
	}

	/**
	 * @param enable the status to set
	 */
	public void setEnable(String enable) {

		this.enable = enable.toUpperCase(Locale.getDefault());
	}

	public Name getDn() {
		return id;
	}

	public void setDn(Name domainName) {
		this.id = domainName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJson() {
		if (null == json) {
			return "{}";
		} else {
			return json;
		}
	}

	/**
	 * ToString
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{\"dn\":\"");
		if (id == null) {
			stringBuilder.append("null");
		} else {
			stringBuilder.append(id.toString());
		}
		stringBuilder.append("\", \"userCode\":\"" + userCode + "\", \"surname\":\"" + surname + "\", \"givenName\":\""
				+ givenName + "\", \"password\":\"");
		if (password == null) {
			stringBuilder.append("null");
		} else {
			stringBuilder.append(Arrays.toString(password));
		}
		stringBuilder.append("\", \"email\":\"" + getEmail() + "\", \"enable\":\"" + getEnable() + "\", \"role\":\""
				+ getRole() + "\", \"json\":" + getJson() + "}");
		return stringBuilder.toString();
	}
}
