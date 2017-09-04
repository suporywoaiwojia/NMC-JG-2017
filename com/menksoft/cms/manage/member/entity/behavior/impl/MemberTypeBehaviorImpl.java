package com.menksoft.cms.manage.member.entity.behavior.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.OrderPattern;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.MemberType;
import com.menksoft.cms.manage.member.entity.behavior.MemberTypeBehavior;

/**
 * 会员类型
 * @author lenovo
 *
 */
@Repository("membertypeBehavior")
public class MemberTypeBehaviorImpl implements MemberTypeBehavior {
	@javax.annotation.Resource
	private HibernateTemplate hibernateTemplate;
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.impl.MemberTypeBehavior#getAllMemberType(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagingTools<MemberType> getAllMemberType(Integer startIndex, Integer count) 
			throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(MemberType.class);
		return  hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging
				(criteria, startIndex, count);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberTypeBehavior#MemberTypesave(com.menksoft.cms.manage.member.entity.MemberType)
	 */
	@Override
	public void saveMemberType(MemberType memberType) throws Throwable {
		hibernateTemplate.save(memberType);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberTypeBehavior#getMemberTypeById(long)
	 */
	@Override
	public MemberType getMemberTypeById(long id) throws Throwable {
		MemberType memberType=null;
		memberType=hibernateTemplate.getEntityById(MemberType.class, id);
		return memberType;
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberTypeBehavior#updateMemberType(com.menksoft.cms.manage.member.entity.MemberType)
	 */
	@Override
	public void updateMemberType(MemberType memberType) throws Throwable {
		hibernateTemplate.update(memberType);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberTypeBehavior#deleteMemberType(com.menksoft.cms.manage.member.entity.MemberType)
	 */
	@Override
	public void deleteMemberType(String id) throws Throwable {
		hibernateTemplate.executeSql("delete from r_tab_member_type where id in("+id+")", null);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberTypeBehavior#getMemberTypeByName(java.lang.String)
	 */
	@Override
	public List<MemberType> getMemberTypeByName(String name)
			throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberType.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberTypeBehavior#getMemberTypeByNameAndId(java.lang.String, java.lang.String)
	 */
	@Override
	public List<MemberType> getMemberTypeByNameAndId(String name, long id)
			throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberType.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		detachedCriteria.add(Restrictions.ne("id", id));
		return hibernateTemplate.getEntitiesByDetachedCriteria(detachedCriteria);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberTypeBehavior#queryMemberType()
	 */
	@Override
	public List<MemberType> queryMember() throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberType.class);
		
		LinkedHashMap<String, OrderPattern> orders = new LinkedHashMap<String, OrderPattern>();
		orders.put("id", OrderPattern.asc);
		return hibernateTemplate.getEntitiesByDetachedCriteriaWithOrders(detachedCriteria, orders);
	}

}
