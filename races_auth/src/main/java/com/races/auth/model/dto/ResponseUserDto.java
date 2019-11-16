package com.races.auth.model.dto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import com.races.auth.model.entity.LdapUser;


/**
 * Dto for create user functionality
 * 
 * @author Maximino Mañanes Ruiz
 */
public class ResponseUserDto {

	private String userCode;

	private String surname;

	private String givenName;

	private String email;

	private String enable;

	private String role;

	private Map<String, Object> json;

	/**
	 * Default constructor
	 */
	public ResponseUserDto() {
		super();
	}

	/**
	 * 
	 * @param userCode
	 * @param surname
	 * @param givenName
	 * @param email
	 * @param enable
	 * @param role
	 * @param json
	 */
	public ResponseUserDto(String userCode, String surname, String givenName, String email, String enable, String role,
			JSONObject json) {
		super();
		this.userCode = userCode;
		this.surname = surname;
		this.givenName = givenName;
		this.email = email;
		this.enable = enable;
		this.role = role;
		this.json = json2Map(json);
	}
	
	/**
	 * create a dto object by LdapUser object
	 * @param user
	 */
	public ResponseUserDto(LdapUser user) {
		this.userCode = user.getUserCode();
		this.surname = user.getSurname();
		this.givenName = user.getGivenName();
		this.email = user.getEmail();
		this.enable = user.getEnable();
		this.role = user.getRole();
		this.json = json2Map(new JSONObject(user.getJson()));
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Map<String,Object> getJson() {
		return json;
	}

	public void setJson(Map<String,Object> json) {
		this.json = json;
	}

	/**
	 * Transforms json to map object
	 * @param json
	 * @return
	 */
	private Map<String, Object> json2Map(JSONObject json) {
		Map<String,Object> map = new HashMap<>();
		Iterator<String> iterator = json.keys();
		String key;
		while(iterator.hasNext()) {
			key = iterator.next();
			map.put(key, json.get(key));
		}
		return map;
	}

}
