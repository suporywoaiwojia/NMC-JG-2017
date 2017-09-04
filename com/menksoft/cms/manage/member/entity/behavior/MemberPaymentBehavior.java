/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : MemberDownloadBehavior.java
 * 描述        : 用户资源下载
 * 作者        : 呼和
 * 日期        : 下午5:28:19
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午5:28:19  呼和
 */
package com.menksoft.cms.manage.member.entity.behavior;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.entity.MemberPayment;
/**
 * @author 呼和
 */
public interface MemberPaymentBehavior {
	/**
	 * 根据用户查询用户资源
	 * @param member	用户对象
	 * @return
	 * @throws Throwable
	 */
	public List<MemberPayment> getMemberPaymentByUser(Member member) throws Throwable ;
	
	/**
	 * 缴费记录存储
	 * @param MemberPayment
	 * @throws Throwable
	 */
	public abstract void saveMemberPayment(MemberPayment MemberPayment) throws Throwable;
	
	//缴费记录带翻页
		public PagingTools<MemberPayment>  getMemberPaymentForpage(Member member,int startpage,int count)throws Throwable;
		
		/**
		 * 根据订单号查询记录
		 * @param orderNum
		 * @return
		 * @throws Throwable
		 */
		public MemberPayment getMemberPaymentByorderNum(String orderNum)throws Throwable; 
		
		/**
		 * 修改订单状态
		 * @param MemberPayment
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
		
		public MemberPayment getMemberPayment(String loginid,long sc_id)throws Throwable;
		
		public MemberPayment getMemberPaymentByLoginid(String loginid)throws Throwable;
}
