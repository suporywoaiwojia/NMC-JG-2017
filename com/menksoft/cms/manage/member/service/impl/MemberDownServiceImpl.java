package com.menksoft.cms.manage.member.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.MemberDown;
import com.menksoft.cms.manage.member.entity.behavior.MemberDownBehavior;
import com.menksoft.cms.manage.member.service.MemberDownService;
@Service("memberDownService")
public class MemberDownServiceImpl implements MemberDownService {
	@javax.annotation.Resource
	private MemberDownBehavior memberDownBehavior;
	@Override
	public void saveMemberDown(MemberDown memberDown) throws Throwable {
		Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dateString = formatter.format(currentTime);
		memberDown.setDownDate(formatter.parse(dateString));
		memberDownBehavior.saveMemberDown(memberDown);
	}

	@Override
	public PagingTools<MemberDown> getMemberDownForpage(long memberId,
			int startpage, int count) throws Throwable {
		return memberDownBehavior.getMemberDownForpage(memberId, startpage, count);
	}

	@Override
	public List<MemberDown> getMemberDownByUser(long memberId) throws Throwable {
		return memberDownBehavior.getMemberDownByUser(memberId);
	}

	@Override
	public MemberDown getMemberdownById(long contentFileid,long memberid) throws Throwable {
		return memberDownBehavior.getMemberdownById(contentFileid,memberid);
	}

}
