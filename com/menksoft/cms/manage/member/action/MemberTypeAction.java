/**
 * 
 */
package com.menksoft.cms.manage.member.action;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.MemberType;
import com.menksoft.cms.manage.member.service.MemberTypeService;
import com.menksoft.util.Const;

/**
 * 会员类型管理
 * @author lenovo
 *
 */
@Controller
public class MemberTypeAction {
	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	MemberTypeService membertypeService;
	
	/**
	 * 列表查询
	 * @return
	 */
	@RequestMapping(value="/manage/membertype/list/{startIndex}", method=RequestMethod.GET)
	public String getAllMemberType(ModelMap modelMap,
			@PathVariable("startIndex") Integer startIndex) {
		 
		PagingTools<MemberType> pagingTools=null;
		try {
			pagingTools=membertypeService.getAllMemberType(startIndex-1, PagingTools.PAGER_ECORD_DEFAULT);
		} catch (Throwable e) {
			log.error("会员类型"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			return "manage/errer.html";
		}
		modelMap.put("pagingTools", pagingTools);
		return "manage/member/member_type_list.jsp";
	}
	
	/**
	 * 新建保存
	 * @param memberTpye	保存对象
	 * @param modelMap	页面赋值对象
	 * @return	返回页面
	 */
	@RequestMapping(value="/manage/membertype/save", method=RequestMethod.POST)
	public  @ResponseBody String saveNewPage(MemberType memberTpye,ModelMap modelMap) {
		String msg="0";
		try{
			//保存操作
			membertypeService.saveMemberType(memberTpye);
			msg="1";
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("会员类型"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			
			return "manage/errer.jsp";
		}
		return  msg;
	}
	
	/**
	 * 新建页面跳转
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/manage/membertype/newpage", method=RequestMethod.GET)
	public String goToNewPage(ModelMap modelMap) {
		return "manage/member/member_type_new.jsp";
	}
	
	/**
	 * 编辑跳转
	 * @param modelMap	页面赋值
	 * @return
	 */
	@RequestMapping(value="/manage/membertype/editpage/{ids}", method=RequestMethod.GET)
	public  String goToEditPage(ModelMap modelMap,@PathVariable("ids") long ids) {
		MemberType memberType=null;
		try{
			//保存操作
			memberType=membertypeService.getMemberTypeById(ids);
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("会员类型"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			return "manage/errer.html";
		}
		modelMap.put("memberType", memberType);
		return "manage/member/member_type_edit.jsp";
	}
	
	/**
	 * 编辑保存
	 * @param modelMap	
	 * @param memberTpye	保存对象
	 * @return	列表页
	 */
	@RequestMapping(value="/manage/membertype/update", method=RequestMethod.POST)
	public @ResponseBody  String updateMemberType(ModelMap modelMap,MemberType memberTpye) {
		String msg="0";
		try{
			//保存操作
			membertypeService.updateMemberType(memberTpye);
			msg="1";
		}catch (Throwable e) {
			//报错失败
			log.error("会员类型"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		
		}
		return msg;
	}
	
	/**
	 * 删除操作
	 * @param modelMap	
	 * @return	列表页
	 */
	@RequestMapping(value="/manage/membertype/delet/{ids}/{pagnum}", method=RequestMethod.GET)
	public String deleteMemberType(ModelMap modelMap,@PathVariable("ids") String ids,
			@PathVariable("pagnum") Integer pagnum) {
		try{			
			//删除操作
			membertypeService.deleteMemberType(ids);
			modelMap.put("message", "删除成功");
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("会员类型信息删除失败", e);
			modelMap.put("message", "删除失败,"+Const.MESSAGE_FAIL_PRIFX+e.getMessage());
			
		}
		return getAllMemberType(modelMap,pagnum-1);
	}
	
	@RequestMapping(value="/manage/membertype/back", method=RequestMethod.POST)
	public String goBackPage(ModelMap modelMap){
		return getAllMemberType(modelMap,0);
	}
}
