/**
 * 
 */
package com.menksoft.cms.manage.member.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.menksoft.cms.manage.data.entity.Content;

/**
 * 网站会员
 * @author lenovo
 *
 */
@Entity(name="r_tab_member")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler","type"})
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private long id;
	//登陆ID
	@Column(length=20)
	private String loginid;
	@Column(length=20)// 昵称 或企业名称
	private String nickname;
	//密码
	@Column(length=60)
	private String password;
	//生日
	@Temporal(TemporalType.DATE)
	private Date birthday;
	//邮箱
	@Column(length=30)
	private String mail;
	//性别
	@Column(length=2)
	private String sex;
	//头像
	@Column(length=200)
	private String image;
	//电话联系方式
	@Column(length=100)
	private String tel;
	//联系地址
	@Column(length=500)
	private String address;
	//状态 0禁用  1正常
	@Column(length=2) 
	private String state;
	//注册日期
	@Temporal(TemporalType.DATE)
	private Date creatdate;
	//缴费结束时间
	@Temporal(TemporalType.DATE)
	private Date eTime;


    @ManyToOne(fetch=FetchType.LAZY,
    		cascade=CascadeType.REMOVE)
	@ForeignKey(name="fk_member_memberType_id")
	@Index(name="index_member_memberType_id")
	private MemberType role;
    
	//申请查看的内容
    @OneToMany(fetch=FetchType.LAZY,mappedBy="readMember")	
    private List<Content> contents;
    
    //申请下载的内容
    @OneToMany(fetch=FetchType.LAZY,mappedBy="downloadMember")	
    private List<Content> downloadContents;
    
    public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public List<Content> getDownloadContents() {
		return downloadContents;
	}

	public void setDownloadContents(List<Content> downloadContents) {
		this.downloadContents = downloadContents;
	}

	public MemberType getRole() {
		return role;
	}

	public void setRole(MemberType role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreatdate() {
		return creatdate;
	}

	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}






}
