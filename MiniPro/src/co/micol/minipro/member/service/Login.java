package co.micol.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.minipro.common.Service;
import co.micol.minipro.member.dao.MemberDao;

public class Login implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// TODO 로그인 과정 처리
		MemberDao dao= new MemberDao();
		MemberVo vo = new MemberVo(); //값을 전달하고 주고받을 수 있는 vo객체 생성
		
		vo.setmId(request.getParameter("mId"));
		vo.setmPassword(request.getParameter("mPassword"));
		
		vo = dao.loginCheck(vo); //로그인체크하기 위한 DAO호출
		
		String viewPage = null;
		
		//간단한 로그인 체크 (세션을 호출해서 이용)
		if(vo.getmAuth() != null) {
			HttpSession session = request.getSession(); //서버가 만들어준 세션 객체 호출 
			session.setAttribute("mid", vo.getmId()); // 세션에 아이디(세션에서 사용할 이름, 세션에 실제로 담을 값)
			session.setAttribute("mauth", vo.getmAuth());//세션에 권한을 담음
			request.setAttribute("vo", vo); //vo객체를 request객체에 담기
			
			viewPage="views/member/loginSuccess.jsp";
		} else {
			viewPage="views/member/loginFail.jsp"; 
			//실패할 경우 vo객체에 담긴 내용이 없기 때문에 vo객체의 값을 호출하면 나오는 값은 없다. 
			//그래서 화면에 뿌릴땐 parameter값으로 뿌려주어야한다.
		}
		return viewPage;
	}

}
