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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.webContent.entity.ContentFile;

@Entity(name="r_data_item_content")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class ItemContent {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO,generator="native")
     @GenericGenerator(strategy="native",name="native")
     private long id;
	 
     //资料所属目录
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@ForeignKey(name="fk_catalog_contentsCatalog")
	@Index(name="index_catalog_contentsCatalog")
	private Catalog contentsCatalog;
	
	//资料所属条目
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@ForeignKey(name="fk_item_contentsItem")
	@Index(name="index_item_contentsItem")
	private Item contentsItem;
   
	@OneToMany(fetch=FetchType.LAZY,mappedBy="itemContentFiles")
	private List<ContentFile> contentFiles;

	//书页名称
	@Column(length=100)
	private String name;
	
	//页码
	@Column(length=100)
	private String page;
	
	//转换内容
	@Lob
	private String content;
	//附件地址
	@Column(length=300)
	private String filePath;
	
	//状态
	@Column(length=10)
	private String  state;
	
	//创建日期
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	//备注
	@Column(length=300)
	private String remask;
	
	//当前审核用户
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_user_itConApprove")
	@Index(name = "index_user_itConApprove")
	private User itConApprove;


	//创建内容的用户
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_user_itConCreateUser")
	@Index(name = "index_user_itConCreateUser")
	private User itConCreateUser;

	
	public List<ContentFile> getContentFiles() {
		return contentFiles;
	}

	public void setContentFiles(List<ContentFile> contentFiles) {
		this.contentFiles = contentFiles;
	}

	public User getItConApprove() {
		return itConApprove;
	}

	public void setItConApprove(User itConApprove) {
		this.itConApprove = itConApprove;
	}

	public User getItConCreateUser() {
		return itConCreateUser;
	}

	public void setItConCreateUser(User itConCreateUser) {
		this.itConCreateUser = itConCreateUser;
	}

	public void setContentsCatalog(Catalog contentsCatalog) {
		this.contentsCatalog = contentsCatalog;
	}

	public Catalog getContentsCatalog() {
		return contentsCatalog;
	}

	public void setsContentCatalog(Catalog contentsCatalog) {
		this.contentsCatalog = contentsCatalog;
	}

	public Item getContentsItem() {
		return contentsItem;
	}

	public void setContentsItem(Item contentsItem) {
		this.contentsItem = contentsItem;
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemask() {
		return remask;
	}

	public void setRemask(String remask) {
		this.remask = remask;
	}

	
	
	
}
