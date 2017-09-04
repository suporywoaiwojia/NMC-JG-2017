package com.menksoft.cms.manage.webContent.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="r_tab_center")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "parent" })
public class Center  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column
	private long id;

	// 标题或者名称
	@Column(length = 30)
	private String title;
	
	

	// 内容
	@Column(length = 300)
	private String content;
	
	// 所属栏目标识
	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_center_parent")
	@Index(name = "index_center_parent")
	private Columns parent;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Columns getParent() {
		return parent;
	}

	public void setParent(Columns parent) {
		this.parent = parent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
