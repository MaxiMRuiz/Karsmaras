package com.races.auth.model.dto;

import java.util.Locale;

/**
 * Dto for create user functionality
 * 
 * @author Maximino Mañanes Ruiz
 */
public class CreateUserDto {

	private String userCode;

	private String surname;

	private String firstName;

	private String password;

	private String email;

	private String role;

	private Boolean registerSS;

	/**
	 * Default constructor
	 */
	public CreateUserDto() {
		super();
	}

	/**
	 * Constructor using fields
	 * 
	 * @param userCode
	 * @param surname
	 * @param firstName
	 * @param password
	 * @param email
	 * @param role
	 * @param registerSS
	 */
	public CreateUserDto(String userCode, String surname, String firstName, String password, String email, String role,
			Boolean registerSS) {
		this.userCode = userCode;
		this.surname = surname;
		this.firstName = firstName;
		this.password = password;
		this.email = email;
		this.role = role;
		this.registerSS = registerSS;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getSurname() {
		return surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode.toUpperCase(Locale.getDefault());
	}

	public void setSurname(String surname) {
		this.surname = surname.toUpperCase(Locale.getDefault());
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.toUpperCase(Locale.getDefault());
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Method to validate if attributes has correct values.
	 * 
	 * @return
	 */
	public boolean valid() {

		boolean isValid = true;
		isValid = this.surname != null && this.userCode != null && this.email != null;
		if (isValid) {
			isValid = this.password != null && this.password.length() >= 5;
		}
		return isValid;
	}

	public Boolean getRegisterSS() {
		return registerSS;
	}

	public void setRegisterSS(Boolean registerSS) {
		this.registerSS = registerSS;
	}

}
