/**
 * 
 */
package com.menksoft.cms.manage.member.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 会员类型
 * @author lenovo
 *
 */
@Entity(name="r_tab_member_type")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler"})
public class MemberType {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private long id;
	//名称
	@Column(length=20)
	private String name;
	//权限类型
	@Column(length=2)
	private String role;
	//创建时间
	@Temporal(TemporalType.DATE)
	private Date creatdate;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getCreatdate() {
		return creatdate;
	}
	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}

	
	
}
