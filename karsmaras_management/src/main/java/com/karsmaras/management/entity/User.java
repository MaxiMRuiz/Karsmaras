package com.karsmaras.management.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.karsmaras.management.dto.UserDto;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String username;

	@JsonIgnore
	private String password;

	private int driverId;

	private int championshipId;

	@ManyToOne
	private Role role;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "friends", joinColumns = {
			@JoinColumn(name = "user") }, inverseJoinColumns = @JoinColumn(name = "friend"))
	private List<User> friends;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param driverId
	 * @param championshipId
	 * @param role
	 * @param friends
	 * @param friendOf
	 */
	public User(int id, String username, String password, int driverId, int championshipId, Role role,
			List<User> friends) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.driverId = driverId;
		this.championshipId = championshipId;
		this.role = role;
		this.friends = friends;
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
	public User(String username, String password, int driverId, int championshipId, Role role, List<User> friends) {
		super();
		this.username = username;
		this.password = password;
		this.driverId = driverId;
		this.championshipId = championshipId;
		this.role = role;
		this.friends = friends;
	}

	public User(UserDto dto) {
		super();
		this.username = dto.getUsername();
		this.password = dto.getPassword();
		this.driverId = dto.getDriverId();
		this.championshipId = dto.getChampionshipId();
		this.role = dto.getRole();
		this.friends = dto.getFriends();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void addFriend(User user) {
		if (this.friends.indexOf(user) == -1) {
			this.getFriends().add(user);
		}
	}

	public void removeFriend(User user) {
		if (this.friends.indexOf(user) != -1) {
			this.getFriends().remove(user);
		}
	}

	public User copy(User userToUpdate) {
		this.setChampionshipId(userToUpdate.getChampionshipId());
		this.setDriverId(userToUpdate.getDriverId());
		this.setRole(userToUpdate.getRole());
		this.setUsername(userToUpdate.getUsername());
		return this;
	}

}
