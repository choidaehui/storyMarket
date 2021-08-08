package net.member.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberDeleteAction_execute() 호출");
		
		// 로그인 세션제어
		HttpSession session = request.getSession();
		String id =(String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		ServletContext context = request.getServletContext();
		String imagePath = context.getRealPath("memberimg");
		System.out.println("파일이 저장되는 실제 경로 :"+imagePath);
		
		// DB객체 생성 -> 해당 정보에 맞는 회원 탈퇴(deleteMember(id, imagePath))
		// -> 처리 결과를 리턴 받아서 사용
		MemberDAO mdao = new MemberDAO();
		int check = mdao.deleteMember(id, imagePath);
		
		// 리턴 결과에 따른 페이지 이동(자바 스크립트)
		// check =1(삭제성공), check =-1(오류)
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(check  == -1) {
			out.print("<script>");
			out.print("  alert('오류!'); ");
			out.print("  history.back(); ");
			out.print("</script>");
			out.close();
			return null;
		}
		
		// 회원의 로그인정보 제거
		session.invalidate();
		
		// 삭제 성공
		out.print("<script>");
		out.print("  alert('회원 탈퇴!!'); ");
		out.print("  location.href='./Main.me'; ");
		out.print("</script>");
		out.close();
		
		return null;
	}

}
