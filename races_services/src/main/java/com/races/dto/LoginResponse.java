package com.races.dto;

/**
 * 
 * @author mmr19
 *
 */
public class LoginResponse {

	private String jwt;
	
	private Boolean admin;

	/**
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}

	/**
	 * @param jwt the jwt to set
	 */
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	/**
	 * @return the admin
	 */
	public Boolean getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	/**
	 * @param jwt
	 * @param admin
	 */
	public LoginResponse(String jwt, Boolean admin) {
		super();
		this.jwt = jwt;
		this.admin = admin;
	}

	/**
	 * 
	 */
	public LoginResponse() {
		super();
	}
	
}
