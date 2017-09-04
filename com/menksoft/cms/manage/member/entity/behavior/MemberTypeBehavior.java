package com.menksoft.cms.manage.member.entity.behavior;

import java.util.List;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.MemberType;

public interface MemberTypeBehavior {

	/**
	 * 查询会员类型列表
	 * @param startIndex	开始页
	 * @param count	显示数量
	 * @return
	 * @throws Throwable
	 */
	public abstract PagingTools<MemberType> getAllMemberType(
			Integer startIndex, Integer count) throws Throwable;
	
	/**
	 * 会员类型保存
	 * @param memberType	提交对象
	 * @throws Throwable
	 */
	public abstract void saveMemberType(MemberType memberType) throws Throwable;
	
	/**
	 * 根据ID查询类型数据
	 * @param id	查询对象ID
	 * @throws Throwable
	 */
	public abstract MemberType getMemberTypeById(long id) throws Throwable;
	
	/**
	 * 编辑保存
	 * @param memberType	保存对象
	 * @throws Throwable
	 */
	public abstract void updateMemberType(MemberType memberType) throws Throwable;
	
	/**
	 * 根据ID删除数据,批量删除
	 * @param id	删除数据ID
	 * @throws Throwable
	 */
	public abstract void deleteMemberType(String id) throws Throwable;
	
	/**
	 * 根据名称判断是否存在重复数据
	 * @param memberType	查询对象
	 * @return	查询结果
	 * @throws Throwable
	 */
	public abstract List<MemberType>  getMemberTypeByName(String name) throws Throwable;
	
	/**
	 * 根据ID，名称判断重复数据
	 * @param name 名称
	 * @param id	ID
	 * @return
	 * @throws Throwable
	 */
	public abstract List<MemberType>  getMemberTypeByNameAndId(String name,long id) throws Throwable;
	
	/**
	 * 下拉菜单查询会员类型
	 * @return
	 * @throws Throwable
	 */
	public abstract List<MemberType>  queryMember() throws Throwable;
}