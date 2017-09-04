/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : MemberBehaviorImpl.java
 * 描述        : 会员管理数据操作
 * 作者        : 呼和
 * 日期        : 上午10:40:45
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午10:40:45  呼和
 */
package com.menksoft.cms.manage.member.entity.behavior.impl;



import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.entity.behavior.MemberBehavior;

/**
 * @author 呼和
 */
@Repository("memberBehavior")
public class MemberBehaviorImpl implements MemberBehavior {
	@javax.annotation.Resource
	private HibernateTemplate hibernateTemplate;
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberBehavior#getAllMember(org.hibernate.criterion.DetachedCriteria, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagingTools<Member> getAllMember(DetachedCriteria criteria,
			Integer startIndex, Integer count) throws Throwable {
		return  hibernateTemplate.getEntitiesByDetachedCriteriaWithPaging
				(criteria, startIndex, count);
	}
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberBehavior#getMemberById(long)
	 */
	@Override
	public Member getMemberById(long id) throws Throwable {
		
		return hibernateTemplate.getEntityById(Member.class, id);
	}
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberBehavior#updateMemberState(com.menksoft.cms.manage.member.entity.Member)
	 */
	@Override
	public void updateMember(Member member) throws Throwable {
		hibernateTemplate.update(member);
		
	}
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.behavior.MemberBehavior#deleteMember(java.util.Map)
	 */
	@Override
	public void deleteMember(String id) throws Throwable {
		hibernateTemplate.executeSql("delete from r_tab_member where id in("+id+")", null);
		
	}
	@Override
	public Member GetMember(DetachedCriteria criteria) throws Throwable {
		return hibernateTemplate.getEntityByDetachedCriteria(criteria);
	}
	
	@Override
	public void saveMember(Member member) throws Throwable {
		hibernateTemplate.save(member);
	}
	
	
	@Override
	public Member getMemberByMail(String mail) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Member.class);
		detachedCriteria.add(Restrictions.eq("mail", mail));
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}
	@Override
	public Member getMemberByTel(String tel) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Member.class);
		detachedCriteria.add(Restrictions.eq("tel", tel));
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}
	@Override
	public Member getMemberBySid(String sid) throws Throwable {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Member.class);
		detachedCriteria.add(Restrictions.eq("sid", sid));
		return hibernateTemplate.getEntityByDetachedCriteria(detachedCriteria);
	}
	

}
