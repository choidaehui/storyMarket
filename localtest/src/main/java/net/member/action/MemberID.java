package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberID implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberID_execute() 호출");
		
		ActionForward forward = new ActionForward();
		
		String email = request.getParameter("email");
		String codecheck = request.getParameter("code_check");
		String usercode = request.getParameter("code");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(!codecheck.equals(usercode)) {
			
			out.print("<script>");
			out.print(" alert('인증번호가 틀렸습니다.');");
			out.print(" location.href='./MemberInfoAction.me'; ");
			out.print("</script>");
			out.close();
			
		}
		
		// memberDAO 객체 생성
		// - getMember(id); 사용해서 처리 (회원정보를 가져오기)
		MemberDAO mdao = new MemberDAO();
		
		MemberBean mb = mdao.getIDPass(email);
		System.out.println("M : " + mb);
		// 정보 저장 (request 객체 )
		request.setAttribute("MemberBean", mb);
		
		// 페이지 이동 (->view 페이지.jsp)
		forward.setPath("./searchId.jsp");
		forward.setRedirect(false);
		System.out.println("M : 회원 정보 저장완료 view페이지 이동");
		
		return forward;
	}

}