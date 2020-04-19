package com.races.portal.dto;

/**
 * 
 * @author mmr19
 *
 */
public class Password {

	private Long id;

	private String currentPass;

	private String newpass;

	/**
	 * @param id
	 * @param password
	 * @param newpass
	 */
	public Password(Long id, String currentPass, String newpass) {
		super();
		this.id = id;
		this.currentPass = currentPass;
		this.newpass = newpass;
	}

	/**
	 * 
	 */
	public Password() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the password
	 */
	public String getCurrentPass() {
		return currentPass;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String currentPass) {
		this.currentPass = currentPass;
	}

	/**
	 * @return the newpass
	 */
	public String getNewpass() {
		return newpass;
	}

	/**
	 * @param newpass the newpass to set
	 */
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
}
