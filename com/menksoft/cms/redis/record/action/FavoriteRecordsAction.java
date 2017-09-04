package com.menksoft.cms.redis.record.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.manage.member.service.MemberService;
import com.menksoft.cms.redis.record.service.FavoriteRecordsService;



/**
 * 会员观看记录
 * @author 呼和
 */
@Controller
public class FavoriteRecordsAction {

	@javax.annotation.Resource
	MemberService memberService;
	@javax.annotation.Resource
	FavoriteRecordsService favoriteRecordsService;
	/**
	 * 记录收藏内容
	 * @param session
	 * @param Request
	 * @return
	 */
	@RequestMapping(value = "/favoriteRecord", method = RequestMethod.POST)
	
	public @ResponseBody
	String setFavoriteRecord(HttpSession session, HttpServletRequest Request) {
		String [] favoriteRecord=Request.getParameterMap().get("FavoriteRecord");
		String f="0";
		try {
			if (session != null && session.getAttribute("loginid") != null){
				String loginid= session.getAttribute("loginid").toString() ;
				f=favoriteRecordsService.setFavoriteRecords(loginid, favoriteRecord);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return f;
	}
	
@RequestMapping(value = "/delFavoriteRecord/{id}/{index}", method = RequestMethod.POST)
	
	public @ResponseBody String delViewRecord(HttpSession session, HttpServletRequest Request, @PathVariable long id,@PathVariable int index) {
		String f="0";
		try {
			if (session != null && session.getAttribute("loginid") != null){
				f=	favoriteRecordsService.deleteFavoriteRecords(id, index);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return f;
	}
}
