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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.webContent.entity.Columns;
import com.menksoft.cms.manage.webContent.entity.ContentFile;
import com.menksoft.cms.manage.webContent.entity.Country;
import com.menksoft.cms.manage.webContent.entity.DataType;
import com.menksoft.cms.manage.webContent.entity.Language;
import com.menksoft.cms.manage.webContent.entity.PublishHouse;
/**
 * 内容信息
 * @author Administrator
 *
 */
@Entity(name="r_data_item")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Item {
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
    @GenericGenerator(strategy="native",name="native")
    private long id;
    
    //名称或标题
    @Column(length=100)
    private String name;
	//标准编号
	@Column(length=100)
	private String isbn;
	//出版次数
	@Column(length=20)
	private String frequency;
	//出版机构
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_publishHouse_pubHouse")
	@Index(name = "index_publishHouse_pubHouse")
	private PublishHouse pubHouse;
    //发布年限
	@Temporal(TemporalType.DATE)
	private Date publishDate;
    //简介或摘要
    @Column(length=300)
    private String summary;
    //作者
    @Column(length=100)
    private String author;
    //作者单位
    @Column(length=300)
    private String address;
    //语言
    @ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_language_itemLanguage")
	@Index(name = "index_language_itemLanguage")
    private Language itemLanguage;
    
    //内容
    @OneToMany(fetch = FetchType.LAZY,mappedBy="contentsItem")
    private List<ItemContent>  item;
    
    //目录
    @OneToMany(fetch = FetchType.LAZY,mappedBy="catalogItem")
    private List<Catalog>  catalog;
    
	//所属国家
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_country_itemCountry")
	@Index(name = "index_country_itemCountry")
	private Country itemCountry;
	//资料类型:1：文献，2：视屏，3：音频
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_dataType_itemType")
	@Index(name = "index_dataType_itemType")
	private DataType itemType;

    //数据来源
    @Column(length=200)
    private String source;

	// 状态   0关闭 1开启
	@Column(length = 1)
	private String state;	

	// 所属栏目标识
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.REMOVE})
	@JoinColumn(name = "parent_id")  
	private Columns parent;
	

	//当前审核用户
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@ForeignKey(name="fk_user_approve")
	@Index(name = "index_user_approve")
	private User approve;
	
	//创建用户ID
	@Column
	private Integer userId;
	
	//创建日期
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	//申请阅读的会员
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_content_readMember")
	@Index(name="index_content_readMember")
	private Member readMember;
	
	//申请下载内容的会员
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@ForeignKey(name="fk_content_downloadMember")
	@Index(name="index_content_downloadMember")
	private Member downloadMember;
	
	//需要关联附件信息
	@OneToMany(fetch=FetchType.LAZY,mappedBy="itemCFiles")
	private List<ContentFile> contentFiles;
	
	//与作品关联的说唱艺人
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name = "rapperItems_id") 
    private Rapper rapperItems;
	


	public Rapper getRapperItems() {
		return rapperItems;
	}

	public void setRapperItems(Rapper rapperItems) {
		this.rapperItems = rapperItems;
	}

	public List<ContentFile> getContentFiles() {
		return contentFiles;
	}

	public void setContentFiles(List<ContentFile> contentFiles) {
		this.contentFiles = contentFiles;
	}

	public Columns getParent() {
		return parent;
	}

	public void setParent(Columns parent) {
		this.parent = parent;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}




	public Language getItemLanguage() {
		return itemLanguage;
	}

	public void setItemLanguage(Language itemLanguage) {
		this.itemLanguage = itemLanguage;
	}

	public DataType getItemType() {
		return itemType;
	}

	public void setItemType(DataType itemType) {
		this.itemType = itemType;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Country getItemCountry() {
		return itemCountry;
	}

	public void setItemCountry(Country itemCountry) {
		this.itemCountry = itemCountry;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}



	public PublishHouse getPubHouse() {
		return pubHouse;
	}

	public void setPubHouse(PublishHouse pubHouse) {
		this.pubHouse = pubHouse;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public List<Catalog> getCatalog() {
		return catalog;
	}

	public void setCatalog(List<Catalog> catalog) {
		this.catalog = catalog;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<ItemContent> getItem() {
		return item;
	}

	public void setItem(List<ItemContent> item) {
		this.item = item;
	}
	
}
