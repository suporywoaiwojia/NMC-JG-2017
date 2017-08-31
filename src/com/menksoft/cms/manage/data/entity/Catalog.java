package com.menksoft.cms.manage.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.menksoft.cms.manage.webContent.entity.ContentFile;

@Entity(name="r_data_catalog")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Catalog {
	 @Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
    @GenericGenerator(strategy="native",name="native")
    private long id;
	//目录所属条目
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@ForeignKey(name="fk_item_catalogItem")
	@Index(name="index_item_catalogItem")
	private Item catalogItem;
	
    //目录下的内容
    @OneToMany(fetch = FetchType.LAZY,mappedBy="contentsCatalog")
    private List<ItemContent>  catalogContent;
    

	//名称
	@Column(length=200)
	private String name;
	//页码范围
	@Column(length=100)
	private String pageRange;
	//封面图
	@Column(length=300)
	private String filePath;
	
	//需要关联附件信息
	@OneToMany(fetch=FetchType.LAZY,mappedBy="catalogContentFiles")
	private List<ContentFile> contentFiles;
	
	public List<ContentFile> getContentFiles() {
		return contentFiles;
	}
	public void setContentFiles(List<ContentFile> contentFiles) {
		this.contentFiles = contentFiles;
	}
	public List<ItemContent> getCatalogContent() {
		return catalogContent;
	}
	public void setCatalogContent(List<ItemContent> catalogContent) {
		this.catalogContent = catalogContent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPageRange() {
		return pageRange;
	}
	public void setPageRange(String pageRange) {
		this.pageRange = pageRange;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Item getCatalogItem() {
		return catalogItem;
	}
	public void setCatalogItem(Item catalogItem) {
		this.catalogItem = catalogItem;
	}

	

}
