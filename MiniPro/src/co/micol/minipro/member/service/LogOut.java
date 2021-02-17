package co.micol.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.minipro.common.Service;

public class LogOut implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// TODO 로그아웃
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("mid");
		request.setAttribute("mid", mid);
		
		session.invalidate(); //세션종료시킴
		
		//return "main.do";
		return "views/member/logout.jsp";
	}

}
