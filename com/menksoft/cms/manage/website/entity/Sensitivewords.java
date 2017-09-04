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
 * 网站敏感词库
 * @author lenovo
 *
 */
@Entity(name="r_tab_Sensitivewords")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler"})
public class Sensitivewords {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private long id;
	//敏感词名称
	@Column(length=100, nullable=false, unique=true)
	private String name;
	
	//替换内容
	@Column(length=100, nullable=false)
	private String repStr;

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

	public String getRepStr() {
		return repStr;
	}

	public void setRepStr(String repStr) {
		this.repStr = repStr;
	}

	
}
