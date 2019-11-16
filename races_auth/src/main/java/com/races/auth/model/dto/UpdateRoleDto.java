package com.races.auth.model.dto;

/**
 * Update Role Dto
 */
public class UpdateRoleDto {

	private String userCode;

	private String newRole;

	/**
	 * Default
	 */
	public UpdateRoleDto() {
		super();
	}
	
	/**
	 * @param userCode
	 * @param newRole
	 */
	public UpdateRoleDto(String userCode, String newRole) {
		super();
		this.userCode = userCode;
		this.newRole = newRole;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getNewRole() {
		return newRole;
	}

	public void setNewRole(String newRole) {
		this.newRole = newRole;
	}

	@Override
	public String toString() {
		return "{\"userCode\":\"" + userCode + "\", \"newRole\":\"" + newRole + "\"}";
	}

}
