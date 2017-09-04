/**
 * 
 */
package com.menksoft.cms.manage.member.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * xiazai记录
 * @author lenovo
 *
 */
@Entity(name="r_tab_member_down")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler"})
public class MemberDown {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private long id;
	//金额
	@Column(length=10)
	private String price;

	//下载名称
	@Column(length=50)
	private String titlename;

	//下载日期
	@Temporal(TemporalType.TIMESTAMP)
	private Date downDate;
	
	//下载路径
	@Column(length=500)
	private String downurl;
	//文件名称
	@Column(length=100)
	private String filename;
	@Column
	private long contentFile_id;
	@Column
	private long content_id=0;
	//下载人员
	@Column
	private long member_id;
	

	public long getContent_id() {
		return content_id;
	}

	public void setContent_id(long content_id) {
		this.content_id = content_id;
	}

	public long getContentFile_id() {
		return contentFile_id;
	}

	public void setContentFile_id(long contentFile_id) {
		this.contentFile_id = contentFile_id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTitlename() {
		return titlename;
	}

	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}

	public Date getDownDate() {
		return downDate;
	}

	public void setDownDate(Date downDate) {
		this.downDate = downDate;
	}

	public String getDownurl() {
		return downurl;
	}

	public void setDownurl(String downurl) {
		this.downurl = downurl;
	}

	public long getMember_id() {
		return member_id;
	}

	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}
	
}
