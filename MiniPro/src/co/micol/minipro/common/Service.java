package co.micol.minipro.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {
	public String run(HttpServletRequest request, HttpServletResponse response);
	//String 타입으로 받는 이유 = view page 경로를 받기 위해서
}
