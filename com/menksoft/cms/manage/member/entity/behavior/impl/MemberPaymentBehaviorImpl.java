/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : MemberDownloadBehaviorImpl.java
 * 描述        : 会员资源管理数据操作
 * 作者        : 呼和
 * 日期        : 下午5:32:42
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午5:32:42  呼和
 */
package com.menksoft.cms.manage.member.entity.behavior.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.entity.MemberPayment;
import com.menksoft.cms.manage.member.entity.behavior.MemberPaymentBehavior;

/**
 * @author 呼和
 */
@Repository("memberPaymentBehavior")
public class MemberPaymentBehaviorImpl implements MemberPaymentBehavior {
	@javax.annotation.Resource
	private HibernateTemplate hibernateTemplate;
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberDownloadBehavior#getMemberDownloadByUser(com.menksoft.cms.manage.member.entity.Member)
	 */
	@Override
	public List<MemberPayment> getMemberPaymentByUser(Member member)
			throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberPayment.class);
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("id", OrderPattern.desc);
		detachedCriteria.add(Restrictions.eq("member", member));
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(detachedCriteria, orders);
	}

	@Override
	public void saveMemberPayment(MemberPayment memberPayment) throws Throwable {
		hibernateTemplate.save(memberPayment);
		
	}

	@Override
	public PagingTools<MemberPayment> getMemberPaymentForpage(Member member,int startpage,int count)
			throws Throwable {
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("id", OrderPattern.desc);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberPayment.class);
		detachedCriteria.add(Restrictions.eq("member", member));
		return	hibernateTemplate.getEntitiesByDetachedCriteriaWithPagingAndOrders(detachedCriteria, startpage, count, orders);
	}

	@Override
	public MemberPayment getMemberPaymentByorderNum(String orderNum)
			throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberPayment.class);
		detachedCriteria.add(Restrictions.eq("orderNum", orderNum));
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}

	@Override
	public void updateMemberPayment(MemberPayment MemberPayment)
			throws Throwable {
		hibernateTemplate.update(MemberPayment);
		
	}

	@Override
	public MemberPayment getMemberPaymentByorderNumLoginid(String orderNum,
			String loginid) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberPayment.class);
		detachedCriteria.add(Restrictions.eq("orderNum", orderNum));
		detachedCriteria.add(Restrictions.eq("loginid", loginid));
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}

	@Override
	public MemberPayment getMemberPayment(String loginid, long sc_id)
			throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberPayment.class);
		detachedCriteria.add(Restrictions.eq("sc_id", sc_id));
		detachedCriteria.add(Restrictions.eq("loginid", loginid));
		detachedCriteria.add(Restrictions.eq("status", "1"));
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}

	@Override
	public MemberPayment getMemberPaymentByLoginid(String loginid)
			throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberPayment.class);
		detachedCriteria.add(Restrictions.isNull("member"));
		detachedCriteria.add(Restrictions.eq("loginid", loginid));
		detachedCriteria.add(Restrictions.eq("status", "1"));
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}

}
