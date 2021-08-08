package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberPassUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberPassUpdateAction_execute() 호출");
				// 한글처리 
				request.setCharacterEncoding("UTF-8");
				
				// 이전페이지에서 전달된 정보를 모두 저장 : 파라미터-> 자바빈객체
				MemberBean updateInfoMemberBean = new MemberBean();
				updateInfoMemberBean.setId(request.getParameter("id"));
				updateInfoMemberBean.setPass(request.getParameter("pass"));
				
				System.out.println("M : 수정 정보 저장 완료!");
				
				MemberDAO mdao = new MemberDAO();
				int check = mdao.updatePassMember(updateInfoMemberBean);
				
				System.out.println("M : DB 처리결과 => "+check);
				
				
				// 처리결과를 리턴받아서 페이지 이동 (자바스크립트 사용 이동)
				// 수정성공 - 1
				// 비밀번호 오류 - 0
				// 아이디가 없음 - -1
				
				// 페이지응답 정보를 설정
				response.setContentType("text/html; charset=UTF-8");
				
				// 응답 정보를 출력하는 통로(IO 스트림)
				PrintWriter out = response.getWriter();
				
				if(check == 0) {
					out.print("<script>");
					out.print(" alert('오류!'); ");
					out.print(" history.back(); ");
					out.print("</script>");
					out.close();
					
					// 컨트롤러를 사용한 페이지이동을 사용하지 않기 위해서
					return null; 
				}else if(check == -1) {
					out.print("<script>");
					out.print(" alert('오류!'); ");
					out.print(" history.back(); ");
					out.print("</script>");
					out.close();
					
					return null;
				}
				
				// check == 1
				out.print("<script>");
				out.print(" alert('수정 성공!'); ");
				out.print(" location.href='./MemberLogin.me'; ");
				out.print("</script>");
				out.close();
				
		return null;
	}

}
