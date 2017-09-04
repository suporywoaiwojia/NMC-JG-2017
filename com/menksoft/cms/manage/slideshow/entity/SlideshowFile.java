/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : SlideshowFile.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012/11/13
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012/11/13    王弘               新建文件
 */
package com.menksoft.cms.manage.slideshow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 幻灯图片
 * @author lenovo
 *
 */
@Entity(name="r_tab_slideshow_file")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler","slideshow"})
public class SlideshowFile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	private long id;
	//文件名称
	@Column(length=100)
	private String filename;
	//文件路径
	@Column(length=200)
	private String filepath;
	//网络路径
	@Column(length=200)
	private String httppath;
	//图片高
	@Column(length=12)
	private int height;
	//图片宽
	@Column(length=12)
	private int width;
	//图片链接
	@Column(length=100)
	private String link;
	//幻灯ID
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_slideshow")
	@Index(name="index_slideshow")
	private Slideshow slideshow;
	//图片描述
	@Column(length=300)
	private String detailed;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getHttppath() {
		return httppath;
	}
	public void setHttppath(String httppath) {
		this.httppath = httppath;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Slideshow getSlideshow() {
		return slideshow;
	}
	public void setSlideshow(Slideshow slideshow) {
		this.slideshow = slideshow;
	}
	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
}