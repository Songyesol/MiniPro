package co.micol.minipro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.minipro.common.Service;

public class MainService implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		//TODO 단순히 main.jsp를 화면에 뿌려준다 
		return "views/main/main.jsp";
	}

}
