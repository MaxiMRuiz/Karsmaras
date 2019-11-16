package com.races.auth.model.entity;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entry for a Ldap Roles
 * @author Maximino Mañanes Ruiz
 */
@Entry(base = "ou=Roles", objectClasses = { "xmas-roleGroup", "top" })
public final class LdapRole {

	@Id
	@JsonIgnore
	private Name id;
	
	@Attribute(name = "cn") 
	private String role;
	
	@JsonIgnore
	@Attribute(name = "member")
	private Set<Name> members;
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role.toUpperCase(Locale.getDefault());
	}
	
	public Name getId() {
		return id;
	}

	public void setId(Name id) {
		this.id = id;
	}

	public Set<Name> getMembers() {
		return members.stream().collect(Collectors.toSet());
	}

	public void setMembers(Set<Name> members) {
		this.members = members.stream().collect(Collectors.toSet());
	}

	/**
	 * Add a new member to members list if not exists
	 * @param member
	 * @return
	 */
	public boolean addMember(Name member) {
		if (members == null) {
			members = new HashSet<>();
		}
		if (members.contains(member)) {
			return false;
		} else {
			members.add(member);
			return true;
		}
	}

	/**
	 * Remove member from the member list of current role
	 * @param member
	 * @return
	 */
	public boolean removeMember(Name member) {
		if (members != null && members.contains(member)) {
			members.remove(member);
			return true;
		}else {
			return false;
		}
	}
	
}
