package com.menksoft.cms.manage.webContent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="r_tap_country")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Country {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO,generator="native")
     @GenericGenerator(strategy="native",name="native")
     private long id;
	 
	 //语言名称
	 @Column(length=100)
	 private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 
}
