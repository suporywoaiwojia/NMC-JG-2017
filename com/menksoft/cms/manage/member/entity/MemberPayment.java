/**
 * 
 */
package com.menksoft.cms.manage.member.entity;

import java.util.Date;

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

/**
 * 缴费记录
 * @author lenovo
 *
 */
@Entity(name="r_tab_member_payment")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler","contentfile"
		,"member"})
public class MemberPayment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private long id;
	//金额
	@Column(nullable=true)
	private double price=0;
	//折扣价格
	@Column(nullable=true)
	private double disprice=0;
	//缴费名称
	@Column(length=50)
	private String paymentName;
	//订单号
	@Column(length=50)
	private String orderNum;
	//支付宝交易ID
	@Column(length=65)
	private String trade_no;
	//交易状态 0失败  1成功
	@Column(length=2)
	private String status;
	//缴费日期
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	//操作方式 1站内缴费 2平台缴费
	@Column(length=20)
	private String paymentType;
	//缴费人员
	@Column(length=10)
	private String paymentUser;
	//缴费月数
	@Column(length=2)
	private String paymentMonth;
	//VIP截止日期
	@Temporal(TemporalType.DATE)
	private Date eTime;
	//会员ID
	@ManyToOne(fetch=FetchType.LAZY)
	@ForeignKey(name="fk_memberdown_member")
	@Index(name="index_memberdown_member")
	private Member member;
	//网银缴费 网银代码
	@Column(length=15)
	private String bankcode;
	//网银缴费 网银流水
	@Column(length=15)
	private String bank_seq_no;
	//购买帐号
	@Column(length=40)
	private String loginid;
	//购买素材ID
	@Column
	private long sc_id=0;
	
	public long getSc_id() {
		return sc_id;
	}
	public void setSc_id(long sc_id) {
		this.sc_id = sc_id;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getBank_seq_no() {
		return bank_seq_no;
	}
	public void setBank_seq_no(String bank_seq_no) {
		this.bank_seq_no = bank_seq_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	public double getDisprice() {
		return disprice;
	}
	public void setDisprice(double disprice) {
		this.disprice = disprice;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentMonth() {
		return paymentMonth;
	}
	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}
	public Date geteTime() {
		return eTime;
	}
	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}
	
	public String getPaymentUser() {
		return paymentUser;
	}
	public void setPaymentUser(String paymentUser) {
		this.paymentUser = paymentUser;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
