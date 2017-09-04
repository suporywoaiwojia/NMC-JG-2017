package com.menksoft.cms.manage.webContent.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Administrator
 *项目信息
 */
@Entity(name="r_tab_project")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer"})
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private long id;
	
	//标题
	@Column(length=300)
	private String name;
	//批次
	@Column(length=2)
	private String batch;
	

	//年度
	@Column(length=4)
	private String year;
	//批次属性
	@Column(length=2)
	private String leveltype;
	//项目编号
	@Column(length=50)
	private String no;
	//类型
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_column_pro_id")
	@Index(name="index_column_pro_id")
	private Columns column;
	
	//保护单位
	@Column(length=200)
	private String employer;
	//简介
	@Column(length=1000)
	private String summary;
	//状态
	@Column(length=2)
	private String state;
	//user_id
	@Column
	private long user_id;
	//创建日期
	@Temporal(TemporalType.DATE)
	private Date creatdate;
	
	//已存在语言版本
	@Column(length=50)
	private String language;
	//当前语言版本
	@Column(length=2)
	private String l_type;
	//汉语版I
	@Column
	private long ch_id;
	//审核ID
	@Column(length=50)
	private String approve_id;
	//审核ID
	@Column(length=50)
	private String approve_name;
	//审核ID
	@Column(length=4)
	private String column_no;

	public String getColumn_no() {
		return column_no;
	}
	public void setColumn_no(String column_no) {
		this.column_no = column_no;
	}
	public String getApprove_name() {
		return approve_name;
	}
	public void setApprove_name(String approve_name) {
		this.approve_name = approve_name;
	}
	public String getApprove_id() {
		return approve_id;
	}
	public void setApprove_id(String approve_id) {
		this.approve_id = approve_id;
	}
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
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLeveltype() {
		return leveltype;
	}
	public void setLeveltype(String leveltype) {
		this.leveltype = leveltype;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}

	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public Date getCreatdate() {
		return creatdate;
	}
	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getL_type() {
		return l_type;
	}
	public void setL_type(String l_type) {
		this.l_type = l_type;
	}
	public long getCh_id() {
		return ch_id;
	}
	public void setCh_id(long ch_id) {
		this.ch_id = ch_id;
	}
	public Columns getColumn() {
		return column;
	}
	public void setColumn(Columns column) {
		this.column = column;
	}
	
}
