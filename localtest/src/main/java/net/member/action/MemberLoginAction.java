package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;


public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		System.out.println("M : MemberFirstLoginAction_execute() 호출");
		request.setCharacterEncoding("UTF-8");
		//전달된 ID/PW정보를 저장(파라미터)
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");				
		
		//DAO객체 생성 - 로그인 체크 메서드()
		MemberDAO mdao = new MemberDAO();
		MemberBean mb = mdao.loginMember(id, pass);
		int check = mdao.loginCheck(id, pass);
		
		System.out.println("M : 로그인 체크완료  "+check);
		System.out.println("M : 로그인 정보저장  "+mb);
		
		//결과를 가지고 이동
		
		//자바스크립트 사용
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(check==0) {//비밀번호 오류
			out.println("<script>");
			out.println("alert('비밀번호 오류!');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return null;
		}else if(check == -1) {
			out.println("<script>");
			out.println("alert('비회원 입니다!');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		//check == 1 (로그인 성공)
		
		//회원의 ID정보를 세션객체로 생성 공유
		//request 내장객체를 사용해서 session 내장객체 생성
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
        session.setAttribute("mb", mb);
		//메인페이지로 이동
		//페이지 이동 - ActionForward 객체
        ActionForward forward = new ActionForward();
		forward.setPath("./StoryMarketmain.me");
		forward.setRedirect(true);
		
		//이동정보를 리턴(컨트롤러에서 이동)
		return forward;
	}
}
