package com.races.portal.dto;

/**
 * 
 * @author mmr19
 *
 */
public class LoginDto {

	private String user;

	private String password;

	/**
	 * @param user
	 * @param password
	 */
	public LoginDto(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	/**
	 * 
	 */
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
