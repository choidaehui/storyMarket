package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberInfoAction_execute() 호출");
		
		//로그인 체크(세션)
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		
		if(id==null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		//MemberDAO객체 생성
		MemberDAO mdao = new MemberDAO();
		
		MemberBean mb = mdao.getMember(id);
		
		System.out.println("M : " + mb);
		//회원정보 저장 -> 뷰 페이지(jsp)까지 전달
		//request 영역에 저장
		request.setAttribute("MemberBean", mb);
		
		// /MemberInfo.me -> ./member/info.jsp (forward방식)
		//페이지 이동
		forward.setPath("./member/info.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	
}
