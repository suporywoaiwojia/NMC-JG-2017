package com.menksoft.cms.manage.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.DataType;
import com.menksoft.cms.manage.webContent.entity.Language;
/**
 * 内容信息
 * @author Administrator
 *
 */
@Entity(name="r_data_content")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Content {
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
    @GenericGenerator(strategy="native",name="native")
    private long id;
    
    //名称或标题
    @Column(length=100)
    private String name;
	
    //简介
    @Column(length=300)
    private String summary;
  
    //语言
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_language_lan")
	@Index(name = "index_language_lan")
    private Language lan;
    //内容
    @Lob
    private byte[] textAreaContent;

	//类型
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_dataType_type")
	@Index(name = "index_dataType_type")
	private DataType type;


	// 状态   0关闭 1开启
	@Column(length = 1)
	private String state;		
	
	//当前审核用户
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_user_approve")
	@Index(name = "index_user_approve")
	private User approve;


	//创建内容的用户
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_user_create")
	@Index(name = "index_user_create")
	private User createUser;
	
	//作者
	@Column(length=100)
	private String author;
	
	//创建日期
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	//申请阅读的会员
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_content_readMember")
	@Index(name="index_content_readMember")
	private Member readMember;
	
	//下载内容的会员
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_content_downloadMember")
	@Index(name="index_content_downloadMember")
	private Member downloadMember;
	
	//所属栏目
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_columns_columnContents")
	@Index(name="index_columns_columnContents")
	private Columns columnContents;
	
	//审核意见
	@Column(length=400)
	private String approveOpinion;
	
	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}

	public String getApproveOpinion() {
		return approveOpinion;
	}

	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Columns getColumnContents() {
		return columnContents;
	}

	public void setColumnContents(Columns columnContents) {
		this.columnContents = columnContents;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	

	public byte[] getTextAreaContent() {
		return textAreaContent;
	}

	public void setTextAreaContent(byte[] textAreaContent) {
		this.textAreaContent = textAreaContent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}





	public Member getReadMember() {
		return readMember;
	}

	public void setReadMember(Member readMember) {
		this.readMember = readMember;
	}

	public Member getDownloadMember() {
		return downloadMember;
	}

	public void setDownloadMember(Member downloadMember) {
		this.downloadMember = downloadMember;
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

	public Language getLan() {
		return lan;
	}

	public void setLan(Language lan) {
		this.lan = lan;
	}
	
}
