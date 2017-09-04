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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 资源模型
 * @author David
 */
@Entity(name="r_tab_resource")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler","type,parent,roles"})
public class Resource {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private long id;

	@Column(length=30, nullable=false)
	private String resourceName;

	@Column(length=30)
	private String displayName;

	@Column(length=150)
	private String description;

	@Column(length=150)
	private String icon;
	
	@Column(length=50)
	private String clazz;
	
	@Column(length=150)
	private String style;

	@Column(length=150, nullable=false)
	private String url;

	@Column
	private long displayOrder;

	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_resource_type")
	@Index(name="index_resource_type")
	private ResourceType type;

	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_resource_parent")
	@Index(name="index_resource_parent")
	private Resource parent;

	@Column(length=10)
	private String filterMode;

	@Column(length=200)
	private String filterRoles;

	@Column(length=1)
	private String builtin = "0";

	@ManyToMany(fetch=FetchType.LAZY, mappedBy="resources")
	@ForeignKey(name="fk_resource_roles")
	private List<Role> roles;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the displayOrder
	 */
	public long getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param displayOrder the displayOrder to set
	 */
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * @return the type
	 */
	public ResourceType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ResourceType type) {
		this.type = type;
	}

	/**
	 * @return the parent
	 */
	public Resource getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Resource parent) {
		this.parent = parent;
	}

	/**
	 * @return the filterMode
	 */
	public String getFilterMode() {
		return filterMode;
	}

	/**
	 * @param filterMode the filterMode to set
	 */
	public void setFilterMode(String filterMode) {
		this.filterMode = filterMode;
	}

	/**
	 * @return the filterRoles
	 */
	public String getFilterRoles() {
		return filterRoles;
	}

	/**
	 * @param filterRoles the filterRoles to set
	 */
	public void setFilterRoles(String filterRoles) {
		this.filterRoles = filterRoles;
	}

	/**
	 * @return the builtin
	 */
	public String getBuiltin() {
		return builtin;
	}

	/**
	 * @param builtin the builtin to set
	 */
	public void setBuiltin(String builtin) {
		this.builtin = builtin;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}