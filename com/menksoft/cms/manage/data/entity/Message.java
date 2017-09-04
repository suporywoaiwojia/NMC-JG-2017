package com.menksoft.cms.manage.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.webContent.entity.Columns;

@Entity(name="r_data_message")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Message {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO,generator="native")
    @GenericGenerator(strategy="native",name="native")
    private long id;
	 
	//标题
	 @Column(length=100)
	 private String title;
	//留言内容
	 @Column(length=400)
	 private String content;
	//留言人姓名
	 @Column(length=100)
	 private String name;
	//留言人联系电话
	 @Column(length=40)
	 private String phone;
	//联系邮箱
	 @Column(length=40)
	 private String email;
	 //所属栏目
	 @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name="column_id")
	 private Columns column;
	 
	//审核状态
    @Column(length=10)
    private String state;
    //创建日期
    @Temporal(TemporalType.DATE)
    private Date createDate;
    
	//当前审核用户
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="approve_id")
	private User approve;
	
	//审核意见
	@Column(length=300)
	private String opinion;
	
	
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public User getApprove() {
		return approve;
	}
	public void setApprove(User approve) {
		this.approve = approve;
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
	public Columns getColumn() {
		return column;
	}
	public void setColumn(Columns column) {
		this.column = column;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	 
}
