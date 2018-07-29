package com.karsmaras.management.dto;

import java.util.List;

import com.karsmaras.management.entity.Role;
import com.karsmaras.management.entity.User;

public class UserDto {

	private String username;

	private String password;

	private int driverId;

	private int championshipId;

	private Role role;

	private List<User> friends;

	private List<User> friendOf;

	/**
	 * 
	 */
	public UserDto() {
		super();
	}

	/**
	 * @param username
	 * @param password
	 * @param driverId
	 * @param championshipId
	 * @param role
	 * @param friends
	 * @param friendOf
	 */
	public UserDto(String username, String password, int driverId, int championshipId, Role role, List<User> friends,
			List<User> friendOf) {
		super();
		this.username = username;
		this.password = password;
		this.driverId = driverId;
		this.championshipId = championshipId;
		this.role = role;
		this.friends = friends;
		this.friendOf = friendOf;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public int getChampionshipId() {
		return championshipId;
	}

	public void setChampionshipId(int championshipId) {
		this.championshipId = championshipId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<User> getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(List<User> friendOf) {
		this.friendOf = friendOf;
	}

	@Override
	public String toString() {
		return "UserDto [username=" + username + ", password=" + password + ", driverId=" + driverId
				+ ", championshipId=" + championshipId + ", role=" + role + ", friends=" + friends + ", friendOf="
				+ friendOf + "]";
	}
	
}
