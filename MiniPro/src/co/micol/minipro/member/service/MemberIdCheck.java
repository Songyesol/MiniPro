package co.micol.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.minipro.common.Service;
import co.micol.minipro.member.dao.MemberDao;

public class MemberIdCheck implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// TODO 아이디 중복체크 
		MemberDao dao = new MemberDao();
		String id =request.getParameter("mid");
		boolean bool = dao.isIdCheck(id);
		String message = null;
		
		if(bool) {
			message= id +"는 사용가능한 아이디입니다.";
			
		} else {
			message= id +"는 이미 사용중인 아이디입니다.";
		}			
		request.setAttribute("msg", message);
		request.setAttribute("check", bool);
		return "views/member/idCheck.jsp";
	}

}
