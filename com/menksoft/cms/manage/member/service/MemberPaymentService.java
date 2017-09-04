/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : MemberDownloadService.java
 * 描述        : 
 * 作者        : lenovo
 * 日期        : 上午9:25:24
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午9:25:24  lenovo
 */
package com.menksoft.cms.manage.member.service;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.entity.MemberPayment;

/**
 * @author 呼和
 */
public interface MemberPaymentService {
	
	/**
	 * 缴费记录
	 * @param member	会员属性
	 * @return
	 * @throws Throwable
	 */
	public List<MemberPayment> getMemberPaymentByUser(Member member)throws Throwable;
	
	/**
	 * 存储缴费记录
	 * @param MemberPayment
	 * @throws Throwable
	 */
	public abstract void saveMemberPayment(MemberPayment MemberPayment,long id)  throws Throwable;
	
	//缴费记录带翻页
	public PagingTools<MemberPayment>  getMemberPaymentForpage(Member member,int startpage,int count)throws Throwable;
	
	/**
	 * 根据订单号查询记录
	 * @param orderNum 订单号
	 * @return
	 * @throws Throwable
	 */
	public MemberPayment getMemberPaymentByorderNum(String orderNum)throws Throwable;
	
	/**
	 * 修改订单状态
	 * @param MemberPayment
	 * @param id
	 * @throws Throwable
	 */
	public abstract void updateMemberPayment(MemberPayment MemberPayment)  throws Throwable;
	
	/**
	 * 注册支付后校验订单信息
	 * @param orderNum
	 * @param loginid
	 * @return
	 * @throws Throwable
	 */
	public MemberPayment getMemberPaymentByorderNumLoginid(String orderNum,String loginid)throws Throwable;
	
	/**
	 * 素材下载查看该素材会员是否已经付费
	 * @param loginid
	 * @param sc_id
	 * @return
	 * @throws Throwable
	 */
	public MemberPayment getMemberPayment(String loginid,long sc_id)throws Throwable;
	
	
	public MemberPayment getMemberPaymentByLoginid(String loginid)throws Throwable;
	
}
