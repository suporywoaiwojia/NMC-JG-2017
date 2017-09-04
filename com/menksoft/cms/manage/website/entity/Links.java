/**
 * 
 */
package com.menksoft.cms.manage.website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 友情链接
 * @author lenovo
 *
 */
@Entity(name="r_tab_links")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler"})
public class Links {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private long id;
	//链接名称
	@Column(length=20)
	private String name;
	//链接图片地址
	@Column(length=100)
	private String filePath;
	//链接地址
	@Column(length=100)
	private String link;
	@Column(length=300)
	private String detailed;
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
	
}
