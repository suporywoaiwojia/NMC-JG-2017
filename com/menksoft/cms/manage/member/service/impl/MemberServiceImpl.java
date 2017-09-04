/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : MemberServiceImpl.java
 * 描述        : 
 * 作者        : 呼和
 * 日期        : 上午10:47:05
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午10:47:05  huhe
 */
package com.menksoft.cms.manage.member.service.impl;




import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.entity.behavior.MemberBehavior;
import com.menksoft.cms.manage.member.service.MemberService;
import com.menksoft.util.Const;
import com.menksoft.util.MD5;
import com.menksoft.util.ValidateUtil;

/**
 * @author 呼和
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	private MemberBehavior memberBehavior;

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.service.MemberService#getAllMember(com.menksoft.cms.manage.member.entity.Member, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagingTools<Member> getAllMember(Member member, Integer startIndex,
			Integer count) throws Throwable {
		PagingTools<Member> pagingtools=null;
		DetachedCriteria criteria = DetachedCriteria.forClass(Member.class);
		try{
			if(member.getLoginid()!=null&&!member.getLoginid().equals(""))
				criteria.add(Restrictions.ilike("loginid", member.getLoginid(),MatchMode.ANYWHERE));
			
			if(member.geteTime()!=null&&!member.geteTime().equals(""))
				criteria.add(Restrictions.eq("eTime", member.geteTime()));
			criteria.addOrder(Order.desc("creatdate"));
//			criteria.setFetchMode("type", FetchMode.JOIN);
			//判断状态是否为char初始值 如果不是则增加查询条件
			if(member.getState()!=null&&!member.getState().equals(""))
				criteria.add(Restrictions.eq("state", member.getState()));
			//查询会员性质
		
			pagingtools=  memberBehavior.getAllMember(criteria, startIndex, count);
		}catch(Throwable e){
			log.error("会员"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("会员"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return pagingtools;
	}
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.service.MemberService#getMemberById(long)
	 */
	@Override
	public Member getMemberById(long id) throws Throwable {
		Member member=null;
		try{
			member=memberBehavior.getMemberById(id);
			
		}catch(Throwable e){
			log.error("会员编辑查询"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("会员"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return member;
	}
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.service.MemberService#updateMemberState(long)
	 */
	@Override
	public void updateMemberState(long id) throws Throwable {
		Member member=null;
		try{
			member=memberBehavior.getMemberById(id);
			if(String.valueOf(member.getState()).equals("1")){
				member.setState("0");
			}else{
				member.setState("1");
			}
			memberBehavior.updateMember(member);
		}catch(Throwable e){
			log.error("会员编辑查询"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			throw new RuntimeException("会员"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
		
	}
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.service.MemberService#deleteMember(java.lang.String)
	 */
	@Override
	public void deleteMember(String id) throws Throwable {
		try {
			
			memberBehavior.deleteMember(id);
		} catch (Throwable e) {
			log.error("会员"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
			throw new RuntimeException("会员"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.service.MemberService#updateMember(com.menksoft.cms.manage.member.entity.Member)
	 */
	@Override
	public void updateMember(Member member) throws Throwable {
		Member memberOld=null;
	
		
		
		try {
			//获取原数据
			memberOld=memberBehavior.getMemberById(member.getId());
			memberOld.setLoginid(member.getLoginid());
			memberOld.setBirthday(member.getBirthday());
			memberOld.setSex(member.getSex());
			memberOld.setMail(member.getMail());
			if(!member.getPassword().equals(memberOld.getPassword()))
				memberOld.setPassword(MD5.getMD5ofStr(member.getPassword()));
			memberOld.setTel(member.getTel());
			memberOld.setState(member.getState());
			memberOld.setRole(member.getRole());
		
			memberOld.setNickname(member.getNickname());
			memberOld.seteTime(member.geteTime());
			memberOld.setAddress(member.getAddress());
			memberBehavior.updateMember(memberOld);
		} catch (Throwable e) {
			log.error("会员"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			throw new RuntimeException("会员类型"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
	}
	public boolean checkValue(Member member){
		boolean flag=false;
		if(!ValidateUtil.isEmail(member.getMail()))
			flag=true;
		
		return flag;
			
		
	}
	@Override
	public Member getMemberByWebsite(String loginid) throws Throwable {
		DetachedCriteria criteria = DetachedCriteria.forClass(Member.class);
		criteria.add(Restrictions.eq("loginid", loginid));
		return memberBehavior.GetMember(criteria);
	}
	
	@Override
	public void saveMember(Member member) throws Throwable {
		//必填项判断
		//Assert.hasText(member.getMail(), member.getMail()+Const.MESSAGE_FAIL_SAVE_ISNULL);
		//唯一校验判断
		//Assert.isTrue(checkRepeat(tag,"insert"), tag.getTitle()+Const.MESSAGE_FAIL_EXISTED);
		//特殊字符校验
		//Assert.isTrue(!ValidateUtil.nameSpecialCharacters(member.getMail()),Const.MESSAGE_FAIL_VALIDATE_NAMESPECIALCHARACTERS);
		try {
			member.setCreatdate(new Date());
			member.setState("1");
			if(member.getSex()==null||member.getSex().equals("")) 
				member.setSex("0");
			if(member.getMail()==null||member.getMail().equals(""))
				member.setMail(member.getLoginid());
			memberBehavior.saveMember(member);
		
		} catch (Throwable e) {
			log.error("会员信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
			throw new RuntimeException("会员信息"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
		}
	}
	@Override
	public Member getMemberByMail(String mail) throws Throwable {
		Member member = null;
		member = memberBehavior.getMemberByMail(mail);
		return member;
	}
	@Override
	public void updateMemberByWebsite(Member member) throws Throwable {
		Member memberOld=new Member();
		try {
			//获取原数据
			memberOld=getMemberById(member.getId());
			//将更新的页面内容 传值给原数据对象
			memberOld.setTel(member.getTel());
			memberOld.setBirthday(member.getBirthday());
			memberOld.setNickname(member.getNickname());
			memberOld.setSex(member.getSex());
			memberOld.setImage(member.getImage());
			
			if(!member.getPassword().equals(memberOld.getPassword()))
				memberOld.setPassword(MD5.getMD5ofStr(member.getPassword()));
			
			//memberBehavior.updateMember(memberOld);
		} catch (Throwable e) {
			log.error("会员"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			throw new RuntimeException("会员类型"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
		
	}
	
	@Override
	public Member getMemberByTel(String tel) throws Throwable {
		
		Member member = null;
		member = memberBehavior.getMemberByTel(tel);
		return member;
	}
	@Override
	public Member getMemberByPswReset(String sid) throws Throwable {
		Member member = null;
		member = memberBehavior.getMemberBySid(sid);
		return member;
	}
	
	@Override
	public Member updateMemberPsw(Member member) throws Throwable {
		Member m=new Member();
		try{
		//	m=memberBehavior.getMemberBySid(member.getSid());
			m.setPassword(MD5.getMD5ofStr(member.getPassword()));
			//m.setSid("");
			memberBehavior.updateMember(m);
		}catch(Throwable e){
			log.error("会员编辑查询"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			throw new RuntimeException("会员"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
		return m;
	}
	
	

}
