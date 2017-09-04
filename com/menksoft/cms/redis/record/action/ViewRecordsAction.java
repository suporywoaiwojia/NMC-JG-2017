package com.menksoft.cms.redis.record.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.manage.member.service.MemberService;
import com.menksoft.cms.redis.record.service.ViewRecordsService;




/**
 * 会员观看记录
 * @author 呼和
 */
@Controller
public class ViewRecordsAction {

	@javax.annotation.Resource
	MemberService memberService;
	@javax.annotation.Resource
	ViewRecordsService viewRecordsService;
	/**
	 * 记录浏览内容
	 * @param session
	 * @param Request
	 * @return
	 */
	@RequestMapping(value = "/ViewRecord", method = RequestMethod.POST)
	
	public @ResponseBody
	void getViewRecord(HttpSession session, HttpServletRequest Request) {
		String [] viewRecord=Request.getParameterMap().get("viewReacord");
		try {
			if (session != null && session.getAttribute("loginid") != null){
				String loginid= session.getAttribute("loginid").toString() ;
				viewRecordsService.setViewRecords(loginid, viewRecord);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delViewRecord/{id}/{index}", method = RequestMethod.POST)
	
	public @ResponseBody String delViewRecord(HttpSession session, HttpServletRequest Request, @PathVariable long id,@PathVariable int index) {
		String f="0";
		try {
			if (session != null && session.getAttribute("loginid") != null){
				f=	viewRecordsService.deleteViewRecords(id, index);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return f;
	}
}
