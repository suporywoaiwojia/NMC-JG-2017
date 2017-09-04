package com.menksoft.cms.manage.member.action;

import java.text.SimpleDateFormat;
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

import com.menksoft.cms.manage.member.entity.MemberPayment;
import com.menksoft.cms.manage.member.entity.MemberType;
import com.menksoft.cms.manage.member.service.MemberPaymentService;
import com.menksoft.cms.manage.member.service.MemberTypeService;
import com.menksoft.util.Const;

@Controller
public class MemberPaymentAction {
	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	MemberTypeService memberTypeService;
	@javax.annotation.Resource
	MemberPaymentService memberPaymentService;
	
	/**
	 * 后台缴费跳转
	 * @param modelMap
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="/member/payment/{id}", method=RequestMethod.GET)
	public String payment(ModelMap modelMap,@PathVariable("id") long id){
		List<MemberType> memberType=null;
		try {
			//查询会员类型 下拉
//			memberType=memberTypeService.queryMemberType("");
		} catch (Throwable e) {
			log.error("会员"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			//return "manage/errer.html";
		}
		modelMap.put("memberId", id);
		modelMap.put("memberType", memberType);
		return "manage/member/member_pay.jsp";
	}
	
	/**
	 * 新建保存
	 * @param memberTpye	保存对象
	 * @param modelMap	页面赋值对象
	 * @return	返回页面
	 */
	@RequestMapping(value="/memberPayment/save/{id}", method=RequestMethod.POST)
	 @ResponseBody
	public void saveNewPage(MemberPayment memberPayment,ModelMap modelMap,@PathVariable("id") long id) {
		try{
			//保存操作
			memberPayment.setPaymentType("后台缴费");
			memberPaymentService.saveMemberPayment(memberPayment, id);
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("会员类型"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			modelMap.put("message", e.getMessage());

			
		}
//		return payment(modelMap,id);

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
}
