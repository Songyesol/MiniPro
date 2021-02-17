package co.micol.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.minipro.common.EmployeeVO;
import co.micol.minipro.common.Service;
import co.micol.minipro.member.dao.MemberDao;

public class Cursor implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		//TODO cursor.do
		MemberDao dao = new MemberDao();
		
		EmployeeVO vo = dao.getSalaryInfo(101,19100);
		request.setAttribute("vo",vo);
		return "views/main/main.jsp";
	}

}
