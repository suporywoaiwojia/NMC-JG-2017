package com.menksoft.cms.manage.member.service.impl;



import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.MemberType;
import com.menksoft.cms.manage.member.entity.behavior.MemberTypeBehavior;
import com.menksoft.cms.manage.member.service.MemberTypeService;
import com.menksoft.util.Const;
import com.menksoft.util.ValidateUtil;


/**
 * 会员类型管理
 * @author lenovo
 *
 */
@Service("membertypeService")
public class MemberTypeServiceImpl implements MemberTypeService  {
	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	private MemberTypeBehavior membertypeBehavior;
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.service.impl.MemberTypeService#getAllMemberType(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagingTools<MemberType> getAllMemberType(Integer startIndex, Integer count) throws 
	Throwable {
		PagingTools<MemberType> pagingtools=null;
		try {
			pagingtools =membertypeBehavior.getAllMemberType(startIndex, count);
		} catch (Throwable e) {
			log.error("会员类型"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("会员类型"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return pagingtools;
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.entity.service.MemberTypeService#saveMemberType(com.menksoft.cms.manage.member.entity.MemberType)
	 */
	@Override
	public void saveMemberType(MemberType memberType)  throws Throwable{
		try {
			memberType.setCreatdate(new Date());
			membertypeBehavior.saveMemberType(memberType);
			
		} catch (Throwable e) {
			log.error("会员类型"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
			throw new RuntimeException("会员类型"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
		}
	}
	/**
	 * 判断校验
	 * @param memberType	判断对象
	 * @return
	 */
	public String checkValueSet(MemberType memberType)  throws Throwable{
		String msg="";	//错误信息
		if(ValidateUtil.nameSpecialCharacters(memberType.getName()));
		return msg;
	}
	/**
	 * 保存查询重复数据
	 * @param name
	 * @return
	 */
	public boolean checkRepeat(MemberType memberType,String flag)  throws Throwable{
		List<MemberType> list=null;
		boolean isExist=true;
		
		if(flag.equals("insert"))
			list=membertypeBehavior.getMemberTypeByName(memberType.getName());
		if(flag.equals("update"))
			list=membertypeBehavior.getMemberTypeByNameAndId(memberType.getName(),memberType.getId());
		if(list.size()>0)
			isExist=false;			
		return isExist;
	}
	/**
	 * 根据ID查询
	 * @param id	查询ID数据
	 * @return
	 */
	@Override
	public MemberType getMemberTypeById(long id)  throws Throwable{
		MemberType memberType=null;
		try {
			memberType=membertypeBehavior.getMemberTypeById(id);
		} catch (Throwable e) {
			log.error("会员类型"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("会员类型"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return memberType;
	}

	/**
	 * 编辑保存
	 * @param memberType	保存对象
	 */
	@Override
	public void updateMemberType(MemberType memberType)  throws Throwable{
		//必填项判断
		Assert.hasText(memberType.getName(), memberType.getName()+Const.MESSAGE_FAIL_SAVE_ISNULL);
		//重复校验
		Assert.isTrue(checkRepeat(memberType,"update"), memberType.getName()+Const.MESSAGE_FAIL_EXISTED);
		
		try {
			MemberType memberTypeOld=membertypeBehavior.getMemberTypeById(memberType.getId());
			memberTypeOld.setName(memberType.getName());
			memberTypeOld.setId(memberType.getId());
			memberTypeOld.setRole(memberType.getRole());
//			memberTypeOld.setMonth(memberType.getMonth());
//			memberTypeOld.setPrice(memberType.getPrice());
//			memberTypeOld.setType(memberType.getType());
//			memberTypeOld.setDiscount(memberType.getDiscount());
//			memberTypeOld.setDisprice(memberType.getDisprice());
			
			membertypeBehavior.updateMemberType(memberTypeOld);
		} catch (Throwable e) {
			log.error("会员类型"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
			throw new RuntimeException("会员类型"+Const.MESSAGE_FAIL_SAVE_NOT_EXISTED, e);
		}
	}

	/**
	 * 根据ID删除数据
	 * @param id
	 */
	@Override
	public void deleteMemberType(String id)  throws Throwable{
		try {

		
			membertypeBehavior.deleteMemberType(id);
		} catch (Throwable e) {
			log.error("会员类型"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
			throw new RuntimeException("会员类型"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.manage.member.service.MemberTypeService#queryMemberType()
	 */
	@Override
	public List<MemberType> queryMember() throws Throwable {
		List<MemberType> memberType=null;
	
		try {
			memberType= membertypeBehavior.queryMember();
		} catch (Throwable e) {
			log.error("会员类型"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			throw new RuntimeException("会员类型"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
		}
		return memberType;
	}
	
}
