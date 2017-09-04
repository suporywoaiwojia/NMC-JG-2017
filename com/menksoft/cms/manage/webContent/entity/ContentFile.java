/**
 * 
 */
package com.menksoft.cms.manage.webContent.entity;

import java.util.Date;

import javax.persistence.CascadeType;
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
import com.menksoft.cms.manage.data.entity.Catalog;
import com.menksoft.cms.manage.data.entity.Item;
import com.menksoft.cms.manage.data.entity.ItemContent;


/**
 * @author lenovo
 *
 */
@Entity(name="r_tab_content_file")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer"})

public class ContentFile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private long id;
	
	@Column(length=100)
	@Index(name="index_fileName")
	private String fileName;
	@Column(length=5) //后缀名
	private String fileExc;
	
	@Column(length=100)
	private String saveName;
	//访问路径
	@Column(length=500)
	private String filePath;
	//文件存储路径
	@Column(length=500)
	private String savePath;
	//所属栏目ID

	@Column
	@Index(name="index_contentid")
	private long contentId;
	//观看 0禁止 1观看
	@Column(length=12)
	private String view;
	
	@Column(length=2)
	private String download;
	//创建时间
	@Temporal(TemporalType.DATE)
	private Date creatDate;
	//
	@Column(length=2)
	private String views;
	@Column(length=400)
	private String httpPath;
	//水印地址
	@Column(length=400)
	private String imgwaterPath;
	//文件所属类型 1项目信息 2传承人 3曲目 4出版物 5专项活动
	@Column(length=2)
	@Index(name="index_file_type")
	private String type;
	//图片拍摄地点
	@Column(length=300)
	private String createPlace;
	
	//与资料条目关联的附件
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@ForeignKey(name="fk_item_itemCFiles")
	@Index(name="index_item_itemCFiles")
	private Item itemCFiles;
	
	//与目录关联的附件
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@ForeignKey(name="fk_catalog_catalogContentFiles")
	@Index(name="index_catalog_catalogContentFiles")
	private Catalog catalogContentFiles;
	
	//与内容关联的附件
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@ForeignKey(name="fk_itemcontent_itemContentFiles")
	@Index(name="index_itemcontent_itemContentFiles")
	private ItemContent itemContentFiles;
	
	public Item getItemCFiles() {
		return itemCFiles;
	}

	public void setItemCFiles(Item itemCFiles) {
		this.itemCFiles = itemCFiles;
	}

	public ItemContent getItemContentFiles() {
		return itemContentFiles;
	}

	public void setItemContentFiles(ItemContent itemContentFiles) {
		this.itemContentFiles = itemContentFiles;
	}

	public Catalog getCatalogContentFiles() {
		return catalogContentFiles;
	}

	public void setCatalogContentFiles(Catalog catalogContentFiles) {
		this.catalogContentFiles = catalogContentFiles;
	}

	public String getCreatePlace() {
		return createPlace;
	}

	public void setCreatePlace(String createPlace) {
		this.createPlace = createPlace;
	}

	public String getFileExc() {
		return fileExc;
	}

	public void setFileExc(String fileExc) {
		this.fileExc = fileExc;
	}

	

	
	public long getId() {
		return id;
	}
	
	public String getImgwaterPath() {
		return imgwaterPath;
	}

	public void setImgwaterPath(String imgwaterPath) {
		this.imgwaterPath = imgwaterPath;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public long getContentId() {
		return contentId;
	}
	public void setContentId(long contentId) {
		this.contentId = contentId;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getDownload() {
		return download;
	}
	public void setDownload(String download) {
		this.download = download;
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	public String getHttpPath() {
		return httpPath;
	}
	public void setHttpPath(String httpPath) {
		this.httpPath = httpPath;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
