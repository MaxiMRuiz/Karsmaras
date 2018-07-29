package com.karsmaras.management.dto;

public class ChangePassDto {

	private String oldPass;
	
	private String newPass;

	/**
	 * @param oldPass
	 * @param newPass
	 */
	public ChangePassDto(String oldPass, String newPass) {
		super();
		this.oldPass = oldPass;
		this.newPass = newPass;
	}

	/**
	 * 
	 */
	public ChangePassDto() {
		super();
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
}
