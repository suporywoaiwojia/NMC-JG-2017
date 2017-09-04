/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.permission.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.menksoft.cms.application.organization.entity.User;

/**
 * 角色模型
 * @author David
 */
@Entity(name="r_tab_role")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler","users"})
public class Role {
	public static final String PRIMARY_KEY_PREFIX = "ROLE_";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="rr")
	@GenericGenerator(name="rr", strategy="assigned")
	@Column(length=32)
	private String id;

	@Column(length=20, nullable=false, unique=true)
	private String roleName;

	@Column(nullable=true)
	private int builtIn = 0;

	@ManyToMany(fetch=FetchType.LAZY, mappedBy="roles")
	@ForeignKey(name="fk_role_users")
	private List<User> users;

	@ManyToMany(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_role_resources")
	@JoinTable(name="r_tab_role_resource")
	private List<Resource> resources;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getBuiltIn() {
		return builtIn;
	}

	public void setBuiltIn(int builtIn) {
		this.builtIn = builtIn;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @return the resources
	 */
	public List<Resource> getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
}