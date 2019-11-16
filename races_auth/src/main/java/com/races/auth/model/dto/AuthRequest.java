package com.races.auth.model.dto;

import java.util.Locale;

/**
 * DTO for an Authentication service
 * 
 * @author Maximino Mañanes Ruiz
 */
public class AuthRequest {

	private String user;

	private String password;

	/**
	 * Default constructor
	 */
	public AuthRequest() {
		super();
	}

	/**
	 * Constructor using fields
	 * 
	 * @param user
	 * @param password
	 */
	public AuthRequest(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setUser(String user) {
		this.user = user.toUpperCase(Locale.getDefault());
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "{\"user\":\"" + user + "\", \"password\":\"" + password + "\"}";
	}

}
