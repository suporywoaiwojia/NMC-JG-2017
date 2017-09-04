package com.menksoft.cms.manage.member.service;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.MemberType;

public interface MemberTypeService {

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.service.impl.Menber#getAllMemberType(java.lang.Integer, java.lang.Integer)
	 */
	public abstract PagingTools<MemberType> getAllMemberType (
			Integer startIndex, Integer count)  throws Throwable;
	/**
	 * 会员类型保存
	 * @param memberType	提交对象
	 */
	public abstract void saveMemberType(MemberType memberType)  throws Throwable;
	
	/**
	 * 根据ID查询类型数据
	 * @param id	查询对象ID
	 * @throws Throwable
	 */
	public abstract MemberType getMemberTypeById(long id)  throws Throwable;
	
	/**
	 * 编辑保存
	 * @param memberType	保存对象
	 * @throws Throwable
	 */
	public abstract void updateMemberType(MemberType memberType)  throws Throwable;
	
	/**
	 * 根据ID删除数据
	 * @param id	删除数据ID
	 * @throws Throwable
	 */
	public abstract void deleteMemberType(String id)  throws Throwable;
	
	/**
	 * 会员类型下拉菜单查询
	 * String type 1查询包月缴费信息 2查询非包月缴费信息 3全部
	 * @return
	 * @throws Throwable
	 */
	public abstract List<MemberType> queryMember()  throws Throwable;
}