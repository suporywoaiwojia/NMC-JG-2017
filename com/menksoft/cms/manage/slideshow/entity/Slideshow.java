/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : Slideshow.java
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

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 幻灯片
 * @author huh
 *
 */
@Entity(name="r_tab_slideshow")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler","type"})
public class Slideshow {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	private long id;
	//名称
	@Column(length=20)
	private String name;
	//创建时间
	@Temporal(TemporalType.DATE)
	private Date creatdate;
	//类型
//	@ManyToOne(fetch=FetchType.LAZY)
//	@ForeignKey(name="fk_slideshow_type")
//	@Index(name="index_slideshow_type")
//	private SlideshowType type;
	
	//幻灯片图片
	@OneToMany(fetch=FetchType.LAZY, mappedBy="slideshow")
	private List<SlideshowFile> slideshowFiles;
	
	/**
	 * 获取幻灯片的Id
	 * @return 幻灯片Id
	 */
	public long getId() {
		return id;
	}
	/**
	 * 设置幻灯片的Id
	 * @param id 幻灯片Id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * 获取幻灯片名称
	 * @return 幻灯片名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置幻灯片名称
	 * @param name 幻灯片名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取幻灯片创建时间
	 * @return 幻灯片创建时间
	 */
	public Date getCreatdate() {
		return creatdate;
	}
	/**
	 * 设置幻灯片创建时间
	 * @param creatdate 幻灯片创建时间
	 */
	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}
	/**
	 * 获取幻灯片类型
	 * @return 幻灯片类型
	 */
//	public SlideshowType getType() {
//		return type;
//	}
	/**
	 * 设置幻灯片类型
	 * @param type 幻灯片类型
	 */
//	public void setType(SlideshowType type) {
//		this.type = type;
//	}
	
	public List<SlideshowFile> getSlideshowFiles() {
		return slideshowFiles;
	}
	public void setSlideshowFiles(List<SlideshowFile> slideshowFiles) {
		this.slideshowFiles = slideshowFiles;
	}
}