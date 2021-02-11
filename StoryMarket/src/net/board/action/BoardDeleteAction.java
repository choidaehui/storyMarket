package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.reviewDAO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : BoardDeleteAction-execute 실행");
		
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//deleteForm --->전달된 파라미터 정보 저장(pageNum, num, pass)
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		
		//DAO 객체 생성  - 삭제를 위한 객체 생성하기(deleteBoard(num, pass))
		reviewDAO rdao = new reviewDAO();
		int result = rdao.deleteBoard(num, pass);

		//처리결과를 리턴받아서 페이지 이동(update동작 참조)
		//JS사용 페이지 이동
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		
		if(result== 0) {
			out.print("<script>");
			out.print(" alert('비밀번호 오류!'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			return null;
		}else if(result == -1) {
			out.print("<script>");
			out.print(" alert('글번호 오류!'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			return null;
			
		}
		//result == 1
		out.print("<script>");
		out.print(" alert('글삭제 완료!'); ");
		out.print(" location.href='./BoardList.bo?pageNum="+pageNum+"'; ");
		out.print("</script>");
		
		
		return null;
	}

}
