/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : MemberDownloadServiceImpl.java
 * 描述        : 
 * 作者        : 呼和
 * 日期        : 上午9:26:54
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午9:26:54  呼和
 */
package com.menksoft.cms.manage.member.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.entity.MemberPayment;
import com.menksoft.cms.manage.member.entity.behavior.MemberPaymentBehavior;
import com.menksoft.cms.manage.member.service.MemberPaymentService;
import com.menksoft.cms.manage.member.service.MemberService;
import com.menksoft.util.Const;

/**
 * @author 呼和
 */
@Service("memberPaymentService")
public class MemberPaymentServiceImpl implements MemberPaymentService {
	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	private MemberPaymentBehavior memberPaymentBehavior;
	@javax.annotation.Resource
	MemberService memberService;
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.service.MemberDownloadService#getMemberDownloadByUser(com.menksoft.cms.manage.member.entity.Member)
	 */
	@Override
	public List<MemberPayment> getMemberPaymentByUser(Member member)
			throws Throwable {
		List<MemberPayment> list=null;
	
		try{
			list=memberPaymentBehavior.getMemberPaymentByUser(member);
		}catch(Throwable e){
			log.error("会员资源"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("会员资源"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return list;
	}

	@Override
	public void saveMemberPayment(MemberPayment memberPayment,long id) throws Throwable {
		try {
			memberPayment.setPaymentDate(new Date());
			
			
			
			Member member=memberService.getMemberById(id);
			memberPayment.setMember(member);
			 Calendar rightNow = Calendar.getInstance();
			 Date dt1=null;
			 if(id!=0){
				 if(memberPayment.getPaymentMonth()!=null&&!memberPayment.getPaymentMonth().equals("")){
					if(member.geteTime()==null){
						 rightNow.add(Calendar.MONTH,Integer.valueOf(memberPayment.getPaymentMonth()));
						 dt1=rightNow.getTime();
					}else{
						rightNow.setTime(member.geteTime());
						 rightNow.add(Calendar.MONTH,Integer.valueOf(memberPayment.getPaymentMonth()));
						 dt1=rightNow.getTime();
					}
				}
			 }else{
				
				 rightNow.add(Calendar.MONTH,Integer.valueOf(memberPayment.getPaymentMonth()));
				 dt1=rightNow.getTime();
			 }
			//前台保存状态为失败。需要支付宝确认后才更改为成功 后台保存状态直接为成功 ，此处为后台保存做工作
			if(memberPayment.getStatus().equals("1")){
				UserDetails userDatails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				 member.seteTime(dt1);
				 memberPayment.setPaymentUser(userDatails.getUsername());
				 memberService.updateMember(member);
			}
			 memberPayment.seteTime(dt1);
			 memberPaymentBehavior.saveMemberPayment(memberPayment);
			
		} catch (Throwable e) {
			log.error("会员类型"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
			throw new RuntimeException("会员类型"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
		}
	}

	@Override
	public PagingTools<MemberPayment> getMemberPaymentForpage(Member member,int startpage,int count)
			throws Throwable {
		
		return memberPaymentBehavior.getMemberPaymentForpage(member, startpage, count);
	}

	@Override
	public MemberPayment getMemberPaymentByorderNum(String orderNum)
			throws Throwable {
		return memberPaymentBehavior.getMemberPaymentByorderNum(orderNum);
	}

	@Override
	public void updateMemberPayment(MemberPayment memberPayment)
			throws Throwable {
		if(memberPayment.getLoginid()==null){
			Member member=memberService.getMemberById(memberPayment.getMember().getId());
			if(memberPayment.geteTime()!=null&&!memberPayment.geteTime().equals("")){
				member.seteTime(memberPayment.geteTime());
			}
			
			 memberService.updateMember(member);
		}
		 memberPaymentBehavior.updateMemberPayment(memberPayment);
		
	}

	@Override
	public MemberPayment getMemberPaymentByorderNumLoginid(String orderNum,
			String loginid) throws Throwable {
		return memberPaymentBehavior.getMemberPaymentByorderNumLoginid(orderNum, loginid);
	}

	@Override
	public MemberPayment getMemberPayment(String loginid, long sc_id)
			throws Throwable {
		return memberPaymentBehavior.getMemberPayment(loginid, sc_id);
	}

	@Override
	public MemberPayment getMemberPaymentByLoginid(String loginid)
			throws Throwable {
		return memberPaymentBehavior.getMemberPaymentByLoginid(loginid);
	}

}
