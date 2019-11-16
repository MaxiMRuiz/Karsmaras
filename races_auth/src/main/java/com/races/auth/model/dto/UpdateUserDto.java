package com.races.auth.model.dto;

import java.util.Locale;
import java.util.Map;

/**
 * Dto for create user functionality
 * 
 * @author Maximino Mañanes Ruiz
 */
public class UpdateUserDto {

	private String userCode;

	private String surname;

	private String givenName;

	private String email;

	private Map<String, Object> json;

	/**
	 * Default constructor
	 */
	public UpdateUserDto() {
		super();
	}

	/**
	 * Constructor using fields
	 * 
	 * @param userCode
	 * @param surname
	 * @param givenName
	 * @param json
	 */
	public UpdateUserDto(String userCode, String surname, String givenName, Map<String, Object> json) {
		this.userCode = userCode;
		this.surname = surname;
		this.givenName = givenName;
		this.json = json;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getSurname() {
		return surname;
	}

	public String getGivenName() {
		return givenName;
	}

	public Map<String, Object> getJson() {
		return json;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode.toUpperCase(Locale.getDefault());
	}

	public void setSurname(String surname) {
		this.surname = surname.toUpperCase(Locale.getDefault());
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName.toUpperCase(Locale.getDefault());
	}

	public void setJson(Map<String, Object> json) {
		this.json = json;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
