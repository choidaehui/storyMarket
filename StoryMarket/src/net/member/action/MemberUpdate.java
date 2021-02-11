package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
System.out.println("M : MemberUpdate_execute() 호출");
		
		//로그인 세션제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		
		if(id==null) {
			//response.sendRedirect(location); (x)
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			System.out.println("M : MemberUpdate -> MemberLogin (세션x)");
			
			return forward;
		}
		
		// memberDAO 객체 생성
		// - getMember(id); 사용해서 처리 (회원정보를 가져오기)
		MemberDAO mdao = new MemberDAO();
		
		MemberBean mb = mdao.getMember(id);
		// 정보 저장 (request 객체 )
		request.setAttribute("mb", mb);
		
		// 페이지 이동 (->view 페이지.jsp)
		forward.setPath("./member/updateForm.jsp");
		forward.setRedirect(false);
		System.out.println("M : 회원 정보 저장완료 view페이지 이동");
		
		return forward;
	}

}
