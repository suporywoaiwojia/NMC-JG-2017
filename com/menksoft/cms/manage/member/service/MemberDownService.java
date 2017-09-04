package com.menksoft.cms.manage.member.service;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.MemberDown;

public interface MemberDownService {
	/**
	 * 保存记录
	 * @param memberDown
	 * @throws Throwable
	 */
	public void saveMemberDown(MemberDown memberDown)throws Throwable;
	
	/**
	 * 下载记录查询
	 * @param memberId 会员id
	 * @param startpage 开始页
	 * @param count 每页记录数
	 * @return
	 * @throws Throwable
	 */
	public PagingTools<MemberDown>  getMemberDownForpage(long memberId,int startpage,int count)throws Throwable; 
	
	/**
	 * 下载记录查询
	 * @param memberId 会员ID
	 * @return
	 * @throws Throwable
	 */
	public List<MemberDown> getMemberDownByUser(long memberId)throws Throwable;
	
	
	/**
	 * 根据ID查询
	 * @param contentFileid
	 * @return
	 * @throws Throwable
	 */
	public MemberDown getMemberdownById(long contentFileid,long memberid)throws Throwable;
}
