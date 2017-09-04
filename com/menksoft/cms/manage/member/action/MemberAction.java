/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : MemberAction.java
 * 描述        : 会员管理
 * 作者        : 呼和
 * 日期        : 上午11:02:42
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       上午11:02:42  呼和
 */
package com.menksoft.cms.manage.member.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.entity.MemberType;
import com.menksoft.cms.manage.member.service.MemberPaymentService;
import com.menksoft.cms.manage.member.service.MemberService;
import com.menksoft.cms.manage.member.service.MemberTypeService;
import com.menksoft.util.Const;
import com.menksoft.util.MD5;


/**
 * @author 呼和
 */
@Controller
public class MemberAction {
	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	MemberService memberService;
	@javax.annotation.Resource
	MemberTypeService memberTypeService;
	@javax.annotation.Resource
	MemberPaymentService memberPaymentService;


	/**
	 * 会员列表查询显示
	 * @param modelMap
	 * @param startIndex
	 * @return
	 */   
	@RequestMapping(value="/manage/member/list/{startIndex}", method=RequestMethod.GET)
	public String getAllMember(ModelMap modelMap,
			@PathVariable("startIndex") Integer startIndex,Member member) {
		PagingTools<Member> pagingTools=null;
		
		try {
			
			pagingTools=memberService.getAllMember(member,startIndex, PagingTools.PAGER_ECORD_DEFAULT);
			//查询会员类型下拉菜单
			
		} catch (Throwable e) {
			log.error("会员"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			
			return "manage/errer.html";
		}
		
	
		modelMap.put("member", member);
		modelMap.put("pagingTools", pagingTools);
		return "manage/member/member_list.jsp";
	}
	
	//
	/**
	 * 跳转到新建页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/manage/member/new",method=RequestMethod.GET)
	public String loadNewPage(ModelMap modelMap){
		PagingTools<MemberType> memberTypes = null;
		try {
			memberTypes = memberTypeService.getAllMemberType(0, 10);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("memberTypes", memberTypes);
		return "manage/member/member_new.jsp";
		
	}
	/**
	 * 查询页面跳转
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/manage/member/editpage/{id}", method=RequestMethod.GET)
	public String queryMemberById(ModelMap modelMap,@PathVariable("id") long id){
		Member member=null;
		
		List<MemberType> memberTypes = new ArrayList<MemberType>();
		try {
			
			//查询会员属性
			member=memberService.getMemberById(id);
			memberTypes = memberTypeService.queryMember();
			
		} catch (Throwable e) {
			log.error("会员"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			return "manage/errer.html";
		}
		
		modelMap.put("member", member);
		modelMap.put("memberTypes", memberTypes);
		return "manage/member/member_edit.jsp";
	}
	
	/**
	 * 编辑会员信息
	 * @param modelMap	
	 * @param member
	 * @return
	 */
	
	@RequestMapping(value="/manage/member/save", method=RequestMethod.POST)
	public @ResponseBody String saveMember(ModelMap modelMap,Member member){
		PagingTools<MemberType> memberTypes = null;
		String msg="0";
		try {
			member.setPassword(MD5.getMD5ofStr(member.getPassword()));
			memberService.saveMember(member);
			//这个角色的问题
			memberTypes = memberTypeService.getAllMemberType(0, 10);
			msg="1";
		} catch (Throwable e) {
			log.error("会员"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			return "manage/errer.html";
		}
		modelMap.put("member", member);
		modelMap.put("memberTypes", memberTypes);
		modelMap.put("msg", msg);
		return  "manage/member/member_edit.jsp";
	}
	
	/**
	 * 编辑会员信息
	 * @param modelMap	
	 * @param member
	 * @return
	 */
	
	@RequestMapping(value="/manage/member/update", method=RequestMethod.POST)
	public @ResponseBody String updateMember(ModelMap modelMap,Member member){
		
		String msg="0";
		try {
			memberService.updateMember(member);
			msg="1";
		} catch (Throwable e) {
			log.error("会员"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			return "manage/errer.html";
		}
		return  msg;
	}
	/**
	 * 删除会员信息
	 * @param modelMap
	 * @param id	删除ID 以，分隔
	 * @param pagnum	当前页数
	 * @param member	查询对象
	 * @return
	 */
	@RequestMapping(value="/manage/member/deleteMember/{ids}/{pagnum}", method=RequestMethod.GET)
	public String deleteMember(ModelMap modelMap,@PathVariable("ids") String ids,
			@PathVariable("pagnum") Integer pagnum,Member member){
		try{			
			//删除操作
			
			memberService.deleteMember(ids);
			
			modelMap.put("message", "删除成功");
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("会员信息删除失败", e);
			modelMap.put("message", "删除失败,"+Const.MESSAGE_FAIL_PRIFX+e.getMessage());
			return "manage/errer.html";
		}
		return getAllMember(modelMap,pagnum-1,member);
	}
	
	/**
	 * 页面表单提交 日期格式化
	 * @param request
	 * @param binder
	 */
	@InitBinder
    public void InitBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        // 不要删除下行注释!!!   将来"yyyy-MM-dd"将配置到properties文件中
        //SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format", request.getLocale()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 保存页面返回
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/manage/member/back", method=RequestMethod.POST)
	public String goBack(ModelMap modelMap){
		Member member=new Member();
		return getAllMember(modelMap,0,member);
	}
	
}
