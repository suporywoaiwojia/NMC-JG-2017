/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : MemberService.java
 * 描述        : 
 * 作者        : lenovo
 * 日期        : 上午10:42:41
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午10:42:41  lenovo
 */
package com.menksoft.cms.manage.member.service;




import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.Member;

/**
 * @author 呼和
 */
public interface MemberService {
	/**
	 * 会员列表查询显示
	 * @param member	查询对象
	 * @param startIndex	开始页数
	 * @param count	每页显示数量
	 * @return	
	 * @throws Throwable	异常
	 */
	public PagingTools<Member> getAllMember(Member member,
			Integer startIndex, Integer count) throws Throwable;
	
	/**
	 * 根据ID查询
	 * @param id 对象ID
	 * @return
	 * @throws Throwable
	 */
	public Member getMemberById(long id)throws Throwable;
	
	/**
	 * 修改状态
	 * @param member 对象属性
	 * @throws Throwable
	 */
	public void updateMemberState(long id)throws Throwable;
	
	/**
	 * 删除会员
	 * @param id 删除对象ID
	 * @throws Throwable
	 */
	public void deleteMember(String id)throws Throwable;
	
	/**
	 * 更新会员信息
	 * @param member	更新对象属性
	 * @throws Throwable
	 */
	public void updateMember(Member member)throws Throwable;
	
	/**
	 * 前台根据登陆名获取登陆用户信息
	 * @param MemberName
	 * @throws Throwable
	 */
	public Member getMemberByWebsite(String loginid)throws Throwable;
	
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
	 * 前台用户xiug
	 * @param member
	 * @throws Throwable
	 */
	public void updateMemberByWebsite(Member member)throws Throwable;/**

	
	
	/**
	 * 手机号查询用户
	 * @return
	 * @throws Throwable
	 */
	public Member getMemberByTel(String tel)throws Throwable;
	
	
	/**
	 * 密码重置链接查询
	 * @param MemberName
	 * @throws Throwable
	 */
	public Member getMemberByPswReset(String sid)throws Throwable;
	
	
	
	
	/**
	 * 修改会员密码
	 * @param member
	 * @throws Throwable
	 */
	public Member updateMemberPsw(Member member)throws Throwable;
}
