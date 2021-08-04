package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberLogoutAction_execute() 호출");
		
		//로그아웃 처리 -> 세션초기화
		HttpSession session = request.getSession();
		//초기화
		session.invalidate();
		
		//페이지 이동(main 페이지로 이동)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('로그아웃!');");
		out.println("location.href = './Main.me'");
		out.println("</script>");
		
		out.close();
		
		return null;
	}
}
