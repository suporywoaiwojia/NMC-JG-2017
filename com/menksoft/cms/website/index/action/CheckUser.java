package com.menksoft.cms.website.index.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.service.MemberService;

public class CheckUser extends HttpServlet {
	@javax.annotation.Resource
	MemberService memberService;
	private static final long serialVersionUID = -9038308848332609445L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=GBK");
		response.setHeader("Cache-Control", "no-cache");
		String username = request.getParameter("username");
		Member member = new Member();
		String msg = "";
		try {
			memberService.getMemberByMail(username);
			if (!member.getMail().equals("") && member.getMail().equals(username)) {
				msg = "用户名" + username + "已经存在";
			} else {
				msg = "恭喜你," + username + "可以使用";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		response.getWriter().println("<?xml version='1.0' encoding='GB2312'?>");
		response.getWriter().println("<root>");
		response.getWriter().println("<content>");
		response.getWriter().print(msg);
		response.getWriter().println("</content>");
		response.getWriter().println("</root>");
		response.getWriter().close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
