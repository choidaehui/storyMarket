package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.reviewBean;
import net.board.db.reviewDAO;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : BoardWriteAction execute 호출");
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//전달정보 저장 -> BoardBean객체 생성(한번에 담기 위한 객체)
		//자바영역이므로 수동으로 저장
		reviewBean rb = new reviewBean();
		
		rb.setName(request.getParameter("name"));
		rb.setPass(request.getParameter("pass"));
		rb.setSubject(request.getParameter("subject"));
		rb.setContent(request.getParameter("content"));
		rb.setRegionDivied(request.getParameter("regionDivied"));
		rb.setRating(request.getParameter("rating"));
		
		// +IP정보 저장
		rb.setIp(request.getRemoteAddr());
		System.out.println("M : 전달된 정보 : "+ rb);
		
		//BoardDAO 객체 생성
		reviewDAO rdao = new reviewDAO();
		//글쓰기 메서드 실행
		rdao.inserBoard(rb);
		
		//페이지 이동(WriteAction -> 리스트) //주소도 화면도 바뀜 -> 리다이렉트  
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

	
	
	
}
