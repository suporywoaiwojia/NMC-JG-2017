/**
 * 项目名称: 草原文化创意资源平台
 * 文件名     : SlideshowType.java
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;


/**
 * 幻灯类型
 * @author huh
 *
 */
@Entity(name="r_tab_slideshow_type")
public class SlideshowType {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	private long id;
	//名称
	@Column(length=20, unique=true)
	private String name;
	//创建时间
	@Column
	private Date creatdate;
	
	@Lob
	private String script;
	
	/**
	 * 获取幻灯片类型Id
	 * @return 幻灯片类型Id
	 */
	public long getId() {
		return id;
	}
	/**
	 * 设置幻灯片类型Id
	 * @param id 幻灯片类型Id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * 获取幻灯片类型名称
	 * @return 幻灯片类型名称
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * 设置幻灯片类型名称
	 * @param name 幻灯片类型名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取幻灯片类型创建时间
	 * @return 幻灯片类型创建时间
	 */
	public Date getCreatdate() {
		return creatdate;
	}
	/**
	 * 设置幻灯片类型创建时间
	 * @param creatdate 幻灯片类型创建时间
	 */
	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}
	/**
	 * 获取幻灯片代码
	 * @return 幻灯片代码
	 */
	public String getScript() {
		return script;
	}
	/**
	 * 设置幻灯片代码
	 * @param script 幻灯片代码
	 */
	public void setScript(String script) {
		this.script = script;
	}
}