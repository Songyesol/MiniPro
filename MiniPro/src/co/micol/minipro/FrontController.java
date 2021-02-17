package co.micol.minipro;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.minipro.common.Service;
import co.micol.minipro.member.service.Cursor;
import co.micol.minipro.member.service.LogOut;
import co.micol.minipro.member.service.Login;
import co.micol.minipro.member.service.LoginForm;
import co.micol.minipro.member.service.MemberIdCheck;
import co.micol.minipro.member.service.MemberJoin;
import co.micol.minipro.member.service.MemberJoinForm;
import co.micol.minipro.member.service.PasingTest;

/**
 * Servlet implementation class FrontController
 */

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Service> map = new HashMap<String, Service>();//여기서 Service는 내가 만든 Service Interface
				//String: 요청명, Service: 실행할 백단의 Command
				//어떤 요청이 오면 어떤 service가 돌아갈래? 
       
 
    public FrontController() {
        super();
    }


	public void init(ServletConfig config) throws ServletException { //여기에 새로운 요청과 서비스만 연결해주면 됌!!
		map.put("/main.do", new MainService()); //메인화면 호출
		map.put("/loginForm.do", new LoginForm()); //로그인폼 호출
		map.put("/login.do", new Login());
		map.put("/logOut.do", new LogOut());
		map.put("/memberJoinForm.do", new MemberJoinForm());//회원가입 폼
		map.put("/memberJoin.do", new MemberJoin()); //회원가입 
		map.put("/idCheck.do", new MemberIdCheck()); //아이디 중복체크
		map.put("/cursor.do", new Cursor());
		map.put("/paging.do", new PasingTest());
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서비스가 끝날때 어떻게 끝낼건지 기록
		request.setCharacterEncoding("UTF-8");
		//real path 찾기위해 요청 분석
		String contextPath = request.getContextPath(); //최상위경로 
		String uri = request.getRequestURI();
		String path = uri.substring(contextPath.length()); //실제 request사항
		
		
		Service service =map.get(path);//적절한 실행 command 찾는 것 
		String viewPage = service.run(request, response); //실행해서 결과를 돌려주는 페이지(viewPage로 경로를 받음)
		
		RequestDispatcher dispatcher= request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
