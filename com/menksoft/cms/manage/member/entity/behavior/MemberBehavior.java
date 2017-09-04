/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : MemberBehavior.java
 * 描述        : 会员管理数据操作层
 * 作者        : 呼和
 * 日期        : 上午10:30:53
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1   上午10:30:53 lenovo
 *    2   2013-01-23   bm
 */
package com.menksoft.cms.manage.member.entity.behavior;

import org.hibernate.criterion.DetachedCriteria;
import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.Member;

/**
 * @author 呼和
 */
public interface MemberBehavior {
	/**
	 * 列表查询操作
	 * @param criteria	查询条件
	 * @param startIndex	开始页
	 * @param count	每页显示数量
	 * @return	
	 * @throws Throwable	异常
	 */
	public PagingTools<Member> getAllMember(DetachedCriteria criteria,
			Integer startIndex, Integer count) throws Throwable;
	
	/**
	 * 根据ID查询数据
	 * @param id	对象ID
	 * @return	对象属性
	 * @throws Throwable
	 */
	public Member getMemberById(long id)throws Throwable;
	
	/**
	 * 更新会员信息
	 * @param member	会员对象
	 * @throws Throwable
	 */
	public void updateMember(Member member)throws Throwable;
	
	/**
	 * 会员删除
	 * @param id	 删除对象
	 * @throws Throwable
	 */
	public void deleteMember(String id)throws Throwable;
	
	/**
	 * 根据查询条件获取会员信息
	 * @return
	 * @throws Throwable
	 */
	public Member GetMember(DetachedCriteria criteria)throws Throwable;
	
	/**
	 * 新增会员信息
	 * @return
	 * @throws Throwable
	 */
	public abstract void saveMember(Member member) throws Throwable;
	
	/**
	 * 查询会员注册用户名是否存在
	 * @return
	 * @throws Throwable
	 */
	public Member getMemberByMail(String mail)throws Throwable;
	
	/**
	 * 查询会员注册用户名是否存在
	 * @return
	 * @throws Throwable
	 */
	public Member getMemberByTel(String tel)throws Throwable;
	
	/**
	 * 密码重置查询
	 * @return
	 * @throws Throwable
	 */
	public Member getMemberBySid(String sid)throws Throwable;
}
