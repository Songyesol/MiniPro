package co.micol.minipro.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.minipro.common.EmployeeVO;
import co.micol.minipro.common.Paging;
import co.micol.minipro.common.Service;
import co.micol.minipro.member.dao.MemberDao;

public class PasingTest implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// TODO Paging 
		MemberDao dao = new MemberDao();
		String pageNo = request.getParameter("pageNo");
		int pg = Integer.parseInt(pageNo);
		
		
		Paging paging = new Paging();
		paging.setPageNo(pg);//현재페이지 지정
		paging.setPageSize(10);//한페이지당 10건씩 보여주겠다.
		paging.setTotalCount(dao.getTotalCnt());// 총 게시물 갯수 //여기서 dao connection이 끊어지기때문에 
		System.out.println(paging);
		
		dao = new MemberDao(); //여기서 다시 dao 연결
		List<EmployeeVO> list= dao.getPagingList(pg);
		request.setAttribute("list", list);
		request.setAttribute("params", paging);
		
		return "views/main/main.jsp";
	}

}
