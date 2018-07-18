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

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String username;

	private String password;

	private int driverId;

	private int championshipId;

	@ManyToOne
	private Role role;

	@ManyToMany
	@JoinTable(name = "friends", joinColumns = {
			@JoinColumn(name = "id") }, inverseJoinColumns = @JoinColumn(name = "user"))
	private List<User> friends;

	@ManyToMany
	@JoinTable(name = "friends", joinColumns = {
			@JoinColumn(name = "id") }, inverseJoinColumns = @JoinColumn(name = "friend"))
	private List<User> friendOf;

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
			List<User> friends, List<User> friendOf) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.driverId = driverId;
		this.championshipId = championshipId;
		this.role = role;
		this.friends = friends;
		this.friendOf = friendOf;
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
	public User(String username, String password, int driverId, int championshipId, Role role, List<User> friends,
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

	public List<User> getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(List<User> friendOf) {
		this.friendOf = friendOf;
	}

}
