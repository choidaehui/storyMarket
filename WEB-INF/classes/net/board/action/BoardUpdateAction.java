package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.board.db.reviewBean;
import net.board.db.reviewDAO;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : BoardUpdateAction_execute 실행!");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//전달된 정보저장(num, name, pass, subject, content , pageNum)//pageNum만 GET방식으로 주소줄을 통해 넘어옴
		//=> 수정할 정보 -> BoardBean객채를 생성하여 저장
		reviewBean rb = new reviewBean();//파라미터 정보를 받아서 보드빈 객체에 저장하자.
		String pageNum = request.getParameter("pageNum");
		
		rb.setNum(Integer.parseInt(request.getParameter("num")));
		rb.setName(request.getParameter("name"));
		rb.setSubject(request.getParameter("subject"));
		rb.setContent(request.getParameter("content"));
		rb.setPass(request.getParameter("pass"));
		rb.setRegionDivied(request.getParameter("regionDivied"));
		System.out.println(rb);		
		
		//DAO객체 생성 -> 수정메서드=updateeBoard(bb);
		reviewDAO rdao = new reviewDAO();
		int result = rdao.updateBoard(rb); //반환된 정보
		
		//수정결과에 따라서 페이지 이동
		//JS사용한 페이지 이동(컨트롤러 이동 X)
		
		//페이지 응답을 설정
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(result == 0) {
			out.print("<script>");
			out.print(" alert('비밀번호 오류!'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			
			return null; //컨트롤러로 이동하지 않음
			
		}else if(result == -1) {
			out.print("<script>");
			out.print(" alert('글이 없음!'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			
			return null; //컨트롤러로 이동하지 않음
		}
		
		//result == 1
		out.print("<script>");
		out.print(" alert('수정성공!'); ");
		out.print(" location.href='./BoardList.bo?pageNum="+pageNum+"'; ");
		out.print("</script>");
		out.close();
		
		return null;
	}

}
