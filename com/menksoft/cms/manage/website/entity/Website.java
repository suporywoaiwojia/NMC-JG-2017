/**
 * 
 */
package com.menksoft.cms.manage.website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 网站管理
 * @author 
 */
@Entity(name="r_tab_website")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler"})
public class Website {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private long id;
	
	//---------------------------------------------

	//语言（未启用）
	@Column(length=50)
	private String language;
	//会员审核 1审核 0关闭（未启用）
	@Column(length=2) 
	private String member;

	//-------------------------------------------------
	@Column(length=100, nullable=false, unique=true)
	private String name;
	//简称
	@Column(length=100)
	private String abbreviation;
	
	//域名
	@Column(length=100)
	private String domainName;
	//开启静态列表页 0动态  1静态（未启用）
	@Column(length=1)
	private String listDynamics;
	//静态页路径（未启用）
	@Column(length=100)
	private String pagePath;
	
	//首页动态 0动态  1静态（未启用）
	@Column(length=1)  
	private String indexDynamics;
	//描述
	@Column(length=300)
	private String detailed;
	
	//电话
	@Column(length=100)
	private String tel;
	
	//邮箱
	@Column(length=50)
	private String mail;
	//logo路径（未启用）
	@Column(length=100)
	private String logoPath;
	//企业名称
	@Column(length=100)
	private String businessName;
	//企业地址
	@Column(length=300)
	private String businessAdd;
	//邮编
	@Column(length=6)
	private String postCode;
	//备案号
	@Column(length=100)
	private String ipc;
	//版权信息
	@Column(length=300)
	private String copyRight;
	//站点关键字（未启用）
	@Column(length=500)
	private String webKey;
	//转换工具安装地址（未启用）
		@Column(length=500)
		private String swfpath;
		//水印内容（未启用）
		@Column(length=500)
		private String watermark;
		
		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public String getMember() {
			return member;
		}

		public void setMember(String member) {
			this.member = member;
		}	
	public String getWatermark() {
			return watermark;
		}

		public void setWatermark(String watermark) {
			this.watermark = watermark;
		}

	public String getSwfpath() {
			return swfpath;
		}

		public void setSwfpath(String swfpath) {
			this.swfpath = swfpath;
		}

	public long getId() {
		return id;
	}
	
	public String getListDynamics() {
		return listDynamics;
	}

	public void setListDynamics(String listDynamics) {
		this.listDynamics = listDynamics;
	}

	public String getIndexDynamics() {
		return indexDynamics;
	}

	public void setIndexDynamics(String indexDynamics) {
		this.indexDynamics = indexDynamics;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
	public String getDetailed() {
		return detailed;
	}

	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessAdd() {
		return businessAdd;
	}
	public void setBusinessAdd(String businessAdd) {
		this.businessAdd = businessAdd;
	}

	public String getIpc() {
		return ipc;
	}
	public void setIpc(String ipc) {
		this.ipc = ipc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getCopyRight() {
		return copyRight;
	}
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
	public String getWebKey() {
		return webKey;
	}
	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}

}
