package com.menksoft.cms.manage.member.entity.behavior.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.MemberDown;
import com.menksoft.cms.manage.member.entity.behavior.MemberDownBehavior;
@Repository("memberDownBehavior")
public class MemberDownBehaviorImpl implements MemberDownBehavior {
	@javax.annotation.Resource
	private HibernateTemplate hibernateTemplate;
	@Override
	public void saveMemberDown(MemberDown memberDown) throws Throwable {
		hibernateTemplate.save(memberDown);
	}

	@Override
	public PagingTools<MemberDown> getMemberDownForpage(long memberId,
			int startpage, int count) throws Throwable {
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("id", OrderPattern.desc);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberDown.class);
		detachedCriteria.add(Restrictions.eq("member_id", memberId));
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithPagingAndOrders(detachedCriteria, startpage, count, orders);
	}

	@Override
	public List<MemberDown> getMemberDownByUser(long memberId) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberDown.class);
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("id", OrderPattern.desc);
		detachedCriteria.add(Restrictions.eq("member_id", memberId));
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(detachedCriteria, orders);
	}

	@Override
	public MemberDown getMemberdownById(long contentFileid,long memberid) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberDown.class);
		detachedCriteria.add(Restrictions.eq("contentFile_id", contentFileid));
		detachedCriteria.add(Restrictions.eq("member_id", memberid));
		
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}

}
