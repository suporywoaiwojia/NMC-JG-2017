package com.menksoft.cms.manage.data.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.Country;

@Entity(name="r_data_rapper")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Rapper {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO,generator="native")
     @GenericGenerator(strategy="native",name="native")
     private long id;
	 //姓名
	 @Column(length=100)
	 private String name;
	 //性别
	 @Column(length=10)
	 private String sex;
	 //国家
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id") 
	 private Country country;
	 //简介
	 @Column(length=300)
	 private String summary;
	 
	 //代表作品,可以和item关联
	 @OneToMany(fetch=FetchType.LAZY,mappedBy="rapperItems")
	 private List<Item> production;
     
	//创建内容的用户
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "publishU_id")
	private User publishU;
	
	//审核状态
    @Column(length=10)
    private String state;
    
    //创建日期
    @Temporal(TemporalType.DATE)
    private Date createDate;
    //单位
    @Column(length=200)
    private String address;
    
	//头像图片名称
	@Column(length=200)
	private String filesName;
	//头像图片访问路径
	@Column(length=200)
	private String filesHttpPath;
	//头像保存路径
	@Column(length=200)
	private String filesUploadPath;
	//电话
    @Column(length=100)
    private String phone;
    
    //邮箱
    @Column(length=100)
    private String email;
	//当前审核用户
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_user_approve")
	@Index(name = "index_user_approve")
	private User approve;
	
	//当前审核用户
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_columns_column")
	@Index(name = "index_columns_column")
	private Columns column;	
	
	
	public Columns getColumn() {
		return column;
	}
	public void setColumn(Columns column) {
		this.column = column;
	}
	public User getApprove() {
		return approve;
	}
	public void setApprove(User approve) {
		this.approve = approve;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getFilesName() {
		return filesName;
	}
	public void setFilesName(String filesName) {
		this.filesName = filesName;
	}
	public String getFilesHttpPath() {
		return filesHttpPath;
	}
	public void setFilesHttpPath(String filesHttpPath) {
		this.filesHttpPath = filesHttpPath;
	}
	public String getFilesUploadPath() {
		return filesUploadPath;
	}
	public void setFilesUploadPath(String filesUploadPath) {
		this.filesUploadPath = filesUploadPath;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public User getPublishU() {
		return publishU;
	}
	public void setPublishU(User publishU) {
		this.publishU = publishU;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public List<Item> getProduction() {
		return production;
	}
	public void setProduction(List<Item> production) {
		this.production = production;
	}
	 
	 
}
