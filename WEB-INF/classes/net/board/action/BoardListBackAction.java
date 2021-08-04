package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardListBackAction implements Action {//BoardListBackAction

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: BoardListBackAction-execute 호출~");
		System.out.println("M: 보드리스트에서 로그아웃하고 다시 보드리스트로");
		
		//로그아웃 -> 세션초기화
		HttpSession session = request.getSession();
		
		//초기화
		session.invalidate();
		
		//다시 보드 리스트로
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('로그아웃하였습니다');");
		out.println("location.href='./BoardList.bo'");
		out.println("</script>");
		
		return null;
	}

}
